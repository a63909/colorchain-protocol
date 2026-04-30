package org.colorchain.protocol;

public final class ReferenceDemo {
    private ReferenceDemo() {
    }

    public static void main(String[] args) {
        ReferenceTrustScorer scorer = new ReferenceTrustScorer();

        ColorChainMessage[] messages = new ColorChainMessage[] {
                buildMessage(
                        "msg-001",
                        "android-node-alpha",
                        1760000000000L,
                        "Community update: the local volunteer station is open for water distribution from 18:00 to 20:00.",
                        "0000000000000000000000000000000000000000000000000000000000000000",
                        scorer),
                buildMessage(
                        "msg-002",
                        "desktop-node-beta",
                        1760000005000L,
                        "SHARE IMMEDIATELY!!! SHARE IMMEDIATELY!!! Water alert water alert water alert!!!",
                        "7d5ab21ddf1d7f1a6c2d46b514497f74134b65f0d9c8f7d6e1b6d4f7f9010a11",
                        scorer),
                buildMessage(
                        "msg-003",
                        "android-node-gamma",
                        1760000010000L,
                        "EVERYONE MUST ACT NOW!!! Do not ignore this update. Forward now to every contact right now!!!",
                        "4a3ac6a8a0a4d4efbda681f040fefcf8fc24531b57dd720f785ea4b5cfa87c21",
                        scorer)
        };

        for (ColorChainMessage message : messages) {
            System.out.println("=== " + message.getMessageId() + " ===");
            System.out.println("Canonical payload:");
            System.out.println(message.canonicalPayload());
            System.out.println();
            System.out.println("Content hash:");
            System.out.println(message.getContentHash());
            System.out.println();
            System.out.println("Trust metadata JSON:");
            System.out.println(message.getTrustMetadata().toJson());
            System.out.println();
        }
    }

    private static ColorChainMessage buildMessage(
            String messageId,
            String authorId,
            long timestampMillis,
            String content,
            String previousBlockHash,
            ReferenceTrustScorer scorer) {
        String contentHash = CanonicalMessageHash.contentHash(content);
        ColorChainMessage message = new ColorChainMessage(
                messageId,
                authorId,
                timestampMillis,
                content,
                previousBlockHash,
                contentHash,
                null);
        TrustMetadata trustMetadata = scorer.score(content);
        return message.withTrustMetadata(trustMetadata);
    }
}
