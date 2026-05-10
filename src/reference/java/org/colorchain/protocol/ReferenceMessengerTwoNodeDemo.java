package org.colorchain.protocol;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class ReferenceMessengerTwoNodeDemo {
    private static final long FIXED_TIMESTAMP_MILLIS = 1700000000000L;
    private static final String MESSAGE_CONTENT =
            "Reference messenger payload for local two-node delivery verification.";

    private ReferenceMessengerTwoNodeDemo() {
    }

    public static void main(String[] args) {
        ReferenceNode nodeA = new ReferenceNode("NODE_A");
        ReferenceNode nodeB = new ReferenceNode("NODE_B");

        System.out.println("COLORCHAIN_REFERENCE_MESSENGER_TWO_NODE_DEMO");
        System.out.println("NODE_A_START messages=" + nodeA.messageCount()
                + " acks=" + nodeA.ackCount()
                + " claims=" + nodeA.claimCount());
        System.out.println("NODE_B_START messages=" + nodeB.messageCount()
                + " acks=" + nodeB.ackCount()
                + " claims=" + nodeB.claimCount());

        // This is a standalone reference demo. It is not production messenger code.
        // It is not the Android implementation and it is not a real network transport.
        // It does not implement production E2E encryption and it does not mint or reward LBU.
        // It demonstrates application-layer messenger semantics above a ColorChain-style protocol core.
        MessageEnvelope envelope = nodeA.createEnvelope(
                "env-001",
                nodeB.getNodeName(),
                FIXED_TIMESTAMP_MILLIS,
                MESSAGE_CONTENT);
        System.out.println("NODE_A_CREATE_ENVELOPE type=MSG_ENVELOPE messageHash="
                + envelope.getMessageHash()
                + " stored=" + nodeA.hasMessage(envelope.getMessageHash()));
        System.out.println("NODE_A_SEND type=MSG_ENVELOPE messageHash=" + envelope.getMessageHash());

        EnvelopeReceiveResult receiveResult = nodeB.receiveEnvelope(envelope);
        System.out.println("NODE_B_RECEIVE type=MSG_ENVELOPE messageHash="
                + envelope.getMessageHash()
                + " sameHash=" + receiveResult.isSameHash()
                + " signatureValid=" + receiveResult.isSignatureValid()
                + " stored=" + receiveResult.isStored());

        if (!receiveResult.isAccepted()) {
            System.out.println("RESULT=FAIL");
            throw new IllegalStateException("Reference messenger envelope verification failed");
        }

        DeliveryAck ack = nodeB.createAck(
                "ack-001",
                nodeA.getNodeName(),
                FIXED_TIMESTAMP_MILLIS + 1L,
                envelope.getMessageHash());
        System.out.println("NODE_B_SEND type=MSG_ACK messageHash="
                + ack.getMessageHash()
                + " ackHash=" + ack.getAckHash());

        AckReceiveResult ackResult = nodeA.receiveAck(ack);
        System.out.println("NODE_A_RECEIVE type=MSG_ACK messageHash="
                + ack.getMessageHash()
                + " ackMatches=" + ackResult.isAckMatches()
                + " delivered=" + ackResult.isDelivered());

        if (!ackResult.isDelivered()) {
            System.out.println("RESULT=FAIL");
            throw new IllegalStateException("Reference messenger ACK verification failed");
        }

        MessageProofClaim claim = nodeA.createNonMintingProofClaim(
                "claim-001",
                envelope.getMessageHash(),
                ack.getAckHash());
        System.out.println("NODE_A_CREATE_PROOF_CLAIM messageHash="
                + claim.getMessageHash()
                + " claimHash=" + claim.getClaimHash()
                + " minting=" + claim.isMintingEnabled()
                + " spendable=" + claim.isSpendable());

        MessageEnvelope tamperedEnvelope = envelope.withTamperedContent(
                "Tampered reference messenger payload.");
        EnvelopeReceiveResult tamperedResult = nodeB.receiveEnvelope(tamperedEnvelope);
        boolean tamperedRejected = !tamperedResult.isAccepted();
        System.out.println("NODE_B_TAMPERED_ENVELOPE_CHECK sameHash="
                + tamperedResult.isSameHash()
                + " signatureValid=" + tamperedResult.isSignatureValid()
                + " rejected=" + tamperedRejected);

        if (!tamperedRejected || tamperedResult.isSameHash() || tamperedResult.isSignatureValid()) {
            System.out.println("RESULT=FAIL");
            throw new IllegalStateException("Reference messenger tampered-envelope check failed");
        }

        DeliveryAck wrongAck = DeliveryAck.create(
                "ack-wrong-001",
                nodeB.getNodeName(),
                nodeA.getNodeName(),
                FIXED_TIMESTAMP_MILLIS + 2L,
                CanonicalMessageHash.sha256Hex("COLORCHAIN_REFERENCE_UNKNOWN_MESSAGE"));
        AckReceiveResult wrongAckResult = nodeA.receiveAck(wrongAck);
        boolean wrongAckRejected = !wrongAckResult.isDelivered();
        System.out.println("NODE_A_WRONG_ACK_CHECK ackMatches="
                + wrongAckResult.isAckMatches()
                + " rejected=" + wrongAckRejected);

        if (!wrongAckRejected || wrongAckResult.isAckMatches()) {
            System.out.println("RESULT=FAIL");
            throw new IllegalStateException("Reference messenger wrong-ACK check failed");
        }

        System.out.println("NODE_A_PROOF_CLAIM_MINTING_CHECK minting="
                + claim.isMintingEnabled()
                + " spendable=" + claim.isSpendable());

        if (claim.isMintingEnabled() || claim.isSpendable()) {
            System.out.println("RESULT=FAIL");
            throw new IllegalStateException("Reference messenger non-minting proof-claim check failed");
        }
        System.out.println("RESULT=PASS");
    }

    private static String demoSignatureFor(String canonicalEnvelopePayload) {
        // This demo signature is an integrity placeholder for the standalone reference demo.
        // It is not production cryptographic signing and does not represent the Android implementation's security model.
        return CanonicalMessageHash.sha256Hex(
                "COLORCHAIN_REFERENCE_DEMO_SIGNATURE_V1|" + canonicalEnvelopePayload);
    }

    private static String escape(String value) {
        return value
                .replace("\\", "\\\\")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }

    private static final class ReferenceNode {
        private final String nodeName;
        private final Map<String, MessageEnvelope> localMessageStore = new LinkedHashMap<>();
        private final Map<String, DeliveryAck> localAckStore = new LinkedHashMap<>();
        private final Map<String, MessageProofClaim> proofClaimStore = new LinkedHashMap<>();
        private final Map<String, Boolean> deliveredMessages = new LinkedHashMap<>();

        private ReferenceNode(String nodeName) {
            this.nodeName = Objects.requireNonNull(nodeName, "nodeName");
        }

        private String getNodeName() {
            return nodeName;
        }

        private int messageCount() {
            return localMessageStore.size();
        }

        private int ackCount() {
            return localAckStore.size();
        }

        private int claimCount() {
            return proofClaimStore.size();
        }

        private boolean hasMessage(String messageHash) {
            return localMessageStore.containsKey(messageHash);
        }

        private MessageEnvelope createEnvelope(
                String envelopeId,
                String toNode,
                long timestampMillis,
                String content) {
            MessageEnvelope envelope = MessageEnvelope.create(
                    envelopeId,
                    nodeName,
                    toNode,
                    timestampMillis,
                    content);
            localMessageStore.put(envelope.getMessageHash(), envelope);
            deliveredMessages.put(envelope.getMessageHash(), false);
            return envelope;
        }

        private EnvelopeReceiveResult receiveEnvelope(MessageEnvelope envelope) {
            String recomputedHash = CanonicalMessageHash.sha256Hex(envelope.canonicalEnvelopePayload());
            boolean sameHash = envelope.getMessageHash().equals(recomputedHash);
            boolean signatureValid = envelope.getDemoSignature().equals(
                    demoSignatureFor(envelope.canonicalEnvelopePayload()));
            boolean addressedToThisNode = nodeName.equals(envelope.getToNode());
            boolean stored = false;
            if (sameHash && signatureValid && addressedToThisNode) {
                localMessageStore.put(envelope.getMessageHash(), envelope);
                stored = true;
            }
            return new EnvelopeReceiveResult(sameHash, signatureValid, stored);
        }

        private DeliveryAck createAck(
                String ackId,
                String toNode,
                long timestampMillis,
                String messageHash) {
            if (!localMessageStore.containsKey(messageHash)) {
                throw new IllegalStateException(nodeName + " cannot ACK an unknown message");
            }
            DeliveryAck ack = DeliveryAck.create(
                    ackId,
                    nodeName,
                    toNode,
                    timestampMillis,
                    messageHash);
            localAckStore.put(ack.getAckHash(), ack);
            return ack;
        }

        private AckReceiveResult receiveAck(DeliveryAck ack) {
            boolean ackHashMatches = ack.getAckHash().equals(
                    CanonicalMessageHash.sha256Hex(ack.canonicalAckPayload()));
            boolean ackForKnownMessage = localMessageStore.containsKey(ack.getMessageHash());
            boolean addressedToThisNode = nodeName.equals(ack.getToNode());
            boolean delivered = ackHashMatches && ackForKnownMessage && addressedToThisNode;
            if (delivered) {
                localAckStore.put(ack.getAckHash(), ack);
                deliveredMessages.put(ack.getMessageHash(), true);
            }
            return new AckReceiveResult(ackHashMatches && ackForKnownMessage, delivered);
        }

        private MessageProofClaim createNonMintingProofClaim(
                String claimId,
                String messageHash,
                String ackHash) {
            if (!Boolean.TRUE.equals(deliveredMessages.get(messageHash))) {
                throw new IllegalStateException(nodeName + " cannot claim proof for an undelivered message");
            }
            if (!localAckStore.containsKey(ackHash)) {
                throw new IllegalStateException(nodeName + " cannot claim proof for an unknown ACK");
            }
            MessageProofClaim claim = MessageProofClaim.create(claimId, messageHash, ackHash, false, false);
            proofClaimStore.put(claim.getClaimHash(), claim);
            return claim;
        }
    }

    private static final class MessageEnvelope {
        private final String envelopeId;
        private final String fromNode;
        private final String toNode;
        private final long timestampMillis;
        private final String content;
        private final String contentHash;
        private final String messageHash;
        private final String demoSignature;

        private MessageEnvelope(
                String envelopeId,
                String fromNode,
                String toNode,
                long timestampMillis,
                String content,
                String contentHash,
                String messageHash,
                String demoSignature) {
            this.envelopeId = Objects.requireNonNull(envelopeId, "envelopeId");
            this.fromNode = Objects.requireNonNull(fromNode, "fromNode");
            this.toNode = Objects.requireNonNull(toNode, "toNode");
            this.timestampMillis = timestampMillis;
            this.content = Objects.requireNonNull(content, "content");
            this.contentHash = Objects.requireNonNull(contentHash, "contentHash");
            this.messageHash = Objects.requireNonNull(messageHash, "messageHash");
            this.demoSignature = Objects.requireNonNull(demoSignature, "demoSignature");
        }

        private static MessageEnvelope create(
                String envelopeId,
                String fromNode,
                String toNode,
                long timestampMillis,
                String content) {
            String contentHash = CanonicalMessageHash.contentHash(content);
            String canonicalPayload = canonicalEnvelopePayload(
                    envelopeId,
                    fromNode,
                    toNode,
                    timestampMillis,
                    content,
                    contentHash);
            String messageHash = CanonicalMessageHash.sha256Hex(canonicalPayload);
            String demoSignature = demoSignatureFor(canonicalPayload);
            return new MessageEnvelope(
                    envelopeId,
                    fromNode,
                    toNode,
                    timestampMillis,
                    content,
                    contentHash,
                    messageHash,
                    demoSignature);
        }

        private String getToNode() {
            return toNode;
        }

        private String getMessageHash() {
            return messageHash;
        }

        private String getDemoSignature() {
            return demoSignature;
        }

        private String canonicalEnvelopePayload() {
            return canonicalEnvelopePayload(
                    envelopeId,
                    fromNode,
                    toNode,
                    timestampMillis,
                    content,
                    contentHash);
        }

        private MessageEnvelope withTamperedContent(String tamperedContent) {
            return new MessageEnvelope(
                    envelopeId,
                    fromNode,
                    toNode,
                    timestampMillis,
                    tamperedContent,
                    contentHash,
                    messageHash,
                    demoSignature);
        }

        private static String canonicalEnvelopePayload(
                String envelopeId,
                String fromNode,
                String toNode,
                long timestampMillis,
                String content,
                String contentHash) {
            return "type=MSG_ENVELOPE"
                    + "\nenvelopeId=" + escape(envelopeId)
                    + "\nfromNode=" + escape(fromNode)
                    + "\ntoNode=" + escape(toNode)
                    + "\ntimestampMillis=" + timestampMillis
                    + "\ncontentHash=" + escape(contentHash)
                    + "\ncontent=" + escape(content);
        }
    }

    private static final class DeliveryAck {
        private final String ackId;
        private final String fromNode;
        private final String toNode;
        private final long timestampMillis;
        private final String messageHash;
        private final String ackHash;

        private DeliveryAck(
                String ackId,
                String fromNode,
                String toNode,
                long timestampMillis,
                String messageHash,
                String ackHash) {
            this.ackId = Objects.requireNonNull(ackId, "ackId");
            this.fromNode = Objects.requireNonNull(fromNode, "fromNode");
            this.toNode = Objects.requireNonNull(toNode, "toNode");
            this.timestampMillis = timestampMillis;
            this.messageHash = Objects.requireNonNull(messageHash, "messageHash");
            this.ackHash = Objects.requireNonNull(ackHash, "ackHash");
        }

        private static DeliveryAck create(
                String ackId,
                String fromNode,
                String toNode,
                long timestampMillis,
                String messageHash) {
            String canonicalPayload = canonicalAckPayload(
                    ackId,
                    fromNode,
                    toNode,
                    timestampMillis,
                    messageHash);
            String ackHash = CanonicalMessageHash.sha256Hex(canonicalPayload);
            return new DeliveryAck(ackId, fromNode, toNode, timestampMillis, messageHash, ackHash);
        }

        private String getToNode() {
            return toNode;
        }

        private String getMessageHash() {
            return messageHash;
        }

        private String getAckHash() {
            return ackHash;
        }

        private String canonicalAckPayload() {
            return canonicalAckPayload(ackId, fromNode, toNode, timestampMillis, messageHash);
        }

        private static String canonicalAckPayload(
                String ackId,
                String fromNode,
                String toNode,
                long timestampMillis,
                String messageHash) {
            return "type=MSG_ACK"
                    + "\nackId=" + escape(ackId)
                    + "\nfromNode=" + escape(fromNode)
                    + "\ntoNode=" + escape(toNode)
                    + "\ntimestampMillis=" + timestampMillis
                    + "\nmessageHash=" + escape(messageHash);
        }
    }

    private static final class MessageProofClaim {
        private final String claimId;
        private final String messageHash;
        private final String ackHash;
        private final boolean mintingEnabled;
        private final boolean spendable;
        private final String claimHash;

        private MessageProofClaim(
                String claimId,
                String messageHash,
                String ackHash,
                boolean mintingEnabled,
                boolean spendable,
                String claimHash) {
            this.claimId = Objects.requireNonNull(claimId, "claimId");
            this.messageHash = Objects.requireNonNull(messageHash, "messageHash");
            this.ackHash = Objects.requireNonNull(ackHash, "ackHash");
            this.mintingEnabled = mintingEnabled;
            this.spendable = spendable;
            this.claimHash = Objects.requireNonNull(claimHash, "claimHash");
        }

        private static MessageProofClaim create(
                String claimId,
                String messageHash,
                String ackHash,
                boolean mintingEnabled,
                boolean spendable) {
            String canonicalPayload = canonicalClaimPayload(
                    claimId,
                    messageHash,
                    ackHash,
                    mintingEnabled,
                    spendable);
            String claimHash = CanonicalMessageHash.sha256Hex(canonicalPayload);
            return new MessageProofClaim(claimId, messageHash, ackHash, mintingEnabled, spendable, claimHash);
        }

        private String getMessageHash() {
            return messageHash;
        }

        private String getClaimHash() {
            return claimHash;
        }

        private boolean isMintingEnabled() {
            return mintingEnabled;
        }

        private boolean isSpendable() {
            return spendable;
        }

        private static String canonicalClaimPayload(
                String claimId,
                String messageHash,
                String ackHash,
                boolean mintingEnabled,
                boolean spendable) {
            return "type=MSG_PROOF_CLAIM"
                    + "\nclaimId=" + escape(claimId)
                    + "\nmessageHash=" + escape(messageHash)
                    + "\nackHash=" + escape(ackHash)
                    + "\nmintingEnabled=" + mintingEnabled
                    + "\nspendable=" + spendable;
        }
    }

    private static final class EnvelopeReceiveResult {
        private final boolean sameHash;
        private final boolean signatureValid;
        private final boolean stored;

        private EnvelopeReceiveResult(boolean sameHash, boolean signatureValid, boolean stored) {
            this.sameHash = sameHash;
            this.signatureValid = signatureValid;
            this.stored = stored;
        }

        private boolean isSameHash() {
            return sameHash;
        }

        private boolean isSignatureValid() {
            return signatureValid;
        }

        private boolean isStored() {
            return stored;
        }

        private boolean isAccepted() {
            return sameHash && signatureValid && stored;
        }
    }

    private static final class AckReceiveResult {
        private final boolean ackMatches;
        private final boolean delivered;

        private AckReceiveResult(boolean ackMatches, boolean delivered) {
            this.ackMatches = ackMatches;
            this.delivered = delivered;
        }

        private boolean isAckMatches() {
            return ackMatches;
        }

        private boolean isDelivered() {
            return delivered;
        }
    }
}
