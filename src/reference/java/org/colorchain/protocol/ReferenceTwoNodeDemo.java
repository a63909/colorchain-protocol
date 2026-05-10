package org.colorchain.protocol;

public final class ReferenceTwoNodeDemo {
    private static final String GENESIS_HASH =
            CanonicalMessageHash.sha256Hex("COLORCHAIN_REFERENCE_GENESIS_V1");
    private static final long FIXED_TIMESTAMP_MILLIS = 1700000000000L;

    private ReferenceTwoNodeDemo() {
    }

    public static void main(String[] args) {
        ReferenceNode nodeA = new ReferenceNode("NODE_A");
        ReferenceNode nodeB = new ReferenceNode("NODE_B");

        System.out.println("COLORCHAIN_REFERENCE_TWO_NODE_DEMO");
        System.out.println("NODE_A_START height=" + nodeA.getHeight() + " head=" + nodeA.getHeadHash());
        System.out.println("NODE_B_START height=" + nodeB.getHeight() + " head=" + nodeB.getHeadHash());

        // This is a standalone reference demo. It is not production consensus code.
        // It is not the Android implementation and it is not a real network transport.
        // It demonstrates canonical same-hash propagation semantics.
        ColorChainMessage record = buildReferenceRecord(nodeA.getHeadHash());
        String canonicalPayload = record.canonicalPayload();
        String recordHash = CanonicalMessageHash.sha256Hex(canonicalPayload);

        nodeA.accept(recordHash);
        System.out.println("NODE_A_LOCAL_ACCEPT hash=" + recordHash + " height=" + nodeA.getHeight());
        System.out.println("NODE_A_SEND hash=" + recordHash);

        String receivedPayload = canonicalPayload;
        String receivedHash = CanonicalMessageHash.sha256Hex(receivedPayload);
        boolean sameHash = recordHash.equals(receivedHash);
        boolean prevMatches = record.getPreviousBlockHash().equals(nodeB.getHeadHash());

        System.out.println("NODE_B_RECEIVE hash=" + receivedHash);
        System.out.println("NODE_B_VERIFY sameHash=" + sameHash + " prevMatches=" + prevMatches);

        if (!sameHash || !prevMatches) {
            System.out.println("RESULT=FAIL");
            throw new IllegalStateException("Reference two-node verification failed");
        }

        nodeB.accept(receivedHash);
        System.out.println("NODE_B_ACCEPT hash=" + receivedHash + " height=" + nodeB.getHeight());
        System.out.println("RESULT=PASS");
    }

    private static ColorChainMessage buildReferenceRecord(String previousHash) {
        String content = "Reference public-interest record for local same-hash two-node verification.";
        String contentHash = CanonicalMessageHash.contentHash(content);
        return new ColorChainMessage(
                "reference-record-001",
                "node-a-reference",
                FIXED_TIMESTAMP_MILLIS,
                content,
                previousHash,
                contentHash,
                null);
    }

    private static final class ReferenceNode {
        private final String name;
        private int height;
        private String headHash;

        private ReferenceNode(String name) {
            this.name = name;
            this.height = 0;
            this.headHash = GENESIS_HASH;
        }

        private int getHeight() {
            return height;
        }

        private String getHeadHash() {
            return headHash;
        }

        private void accept(String hash) {
            if (hash == null || hash.isEmpty()) {
                throw new IllegalArgumentException(name + " cannot accept an empty hash");
            }
            this.height += 1;
            this.headHash = hash;
        }
    }
}
