package org.colorchain.protocol;

import java.util.Objects;

public final class ColorChainMessage {
    private final String messageId;
    private final String authorId;
    private final long timestampMillis;
    private final String content;
    private final String previousBlockHash;
    private final String contentHash;
    private final TrustMetadata trustMetadata;

    public ColorChainMessage(
            String messageId,
            String authorId,
            long timestampMillis,
            String content,
            String previousBlockHash,
            String contentHash,
            TrustMetadata trustMetadata) {
        this.messageId = Objects.requireNonNull(messageId, "messageId");
        this.authorId = Objects.requireNonNull(authorId, "authorId");
        this.timestampMillis = timestampMillis;
        this.content = Objects.requireNonNull(content, "content");
        this.previousBlockHash = previousBlockHash == null ? "" : previousBlockHash;
        this.contentHash = Objects.requireNonNull(contentHash, "contentHash");
        this.trustMetadata = trustMetadata;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public long getTimestampMillis() {
        return timestampMillis;
    }

    public String getContent() {
        return content;
    }

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public String getContentHash() {
        return contentHash;
    }

    public TrustMetadata getTrustMetadata() {
        return trustMetadata;
    }

    public ColorChainMessage withTrustMetadata(TrustMetadata metadata) {
        return new ColorChainMessage(
                messageId,
                authorId,
                timestampMillis,
                content,
                previousBlockHash,
                contentHash,
                metadata);
    }

    public String canonicalPayload() {
        return "messageId=" + escape(messageId)
                + "\nauthorId=" + escape(authorId)
                + "\ntimestampMillis=" + timestampMillis
                + "\npreviousBlockHash=" + escape(previousBlockHash)
                + "\ncontentHash=" + escape(contentHash)
                + "\ncontent=" + escape(content);
    }

    @Override
    public String toString() {
        return "ColorChainMessage{"
                + "messageId='" + messageId + '\''
                + ", authorId='" + authorId + '\''
                + ", timestampMillis=" + timestampMillis
                + ", previousBlockHash='" + previousBlockHash + '\''
                + ", contentHash='" + contentHash + '\''
                + ", trustMetadata=" + trustMetadata
                + '}';
    }

    private static String escape(String value) {
        return value
                .replace("\\", "\\\\")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}
