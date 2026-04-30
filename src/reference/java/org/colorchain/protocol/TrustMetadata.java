package org.colorchain.protocol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public final class TrustMetadata {
    private final double spamScore;
    private final double duplicationRisk;
    private final double manipulationRisk;
    private final String trustSignal;
    private final List<String> flags;
    private final String explanation;

    public TrustMetadata(
            double spamScore,
            double duplicationRisk,
            double manipulationRisk,
            String trustSignal,
            List<String> flags,
            String explanation) {
        this.spamScore = clamp(spamScore);
        this.duplicationRisk = clamp(duplicationRisk);
        this.manipulationRisk = clamp(manipulationRisk);
        this.trustSignal = Objects.requireNonNull(trustSignal, "trustSignal");
        this.flags = Collections.unmodifiableList(new ArrayList<>(Objects.requireNonNull(flags, "flags")));
        this.explanation = Objects.requireNonNull(explanation, "explanation");
    }

    public double getSpamScore() {
        return spamScore;
    }

    public double getDuplicationRisk() {
        return duplicationRisk;
    }

    public double getManipulationRisk() {
        return manipulationRisk;
    }

    public String getTrustSignal() {
        return trustSignal;
    }

    public List<String> getFlags() {
        return flags;
    }

    public String getExplanation() {
        return explanation;
    }

    public String toJson() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        builder.append("  \"spam_score\": ").append(formatDouble(spamScore)).append(",\n");
        builder.append("  \"duplication_risk\": ").append(formatDouble(duplicationRisk)).append(",\n");
        builder.append("  \"manipulation_risk\": ").append(formatDouble(manipulationRisk)).append(",\n");
        builder.append("  \"trust_signal\": \"").append(escapeJson(trustSignal)).append("\",\n");
        builder.append("  \"flags\": [");
        for (int i = 0; i < flags.size(); i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append("\"").append(escapeJson(flags.get(i))).append("\"");
        }
        builder.append("],\n");
        builder.append("  \"explanation\": \"").append(escapeJson(explanation)).append("\"\n");
        builder.append("}");
        return builder.toString();
    }

    @Override
    public String toString() {
        return toJson();
    }

    private static double clamp(double value) {
        if (value < 0.0d) {
            return 0.0d;
        }
        if (value > 1.0d) {
            return 1.0d;
        }
        return value;
    }

    private static String formatDouble(double value) {
        return String.format(Locale.US, "%.2f", value);
    }

    private static String escapeJson(String value) {
        StringBuilder escaped = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            switch (c) {
                case '\\':
                    escaped.append("\\\\");
                    break;
                case '"':
                    escaped.append("\\\"");
                    break;
                case '\n':
                    escaped.append("\\n");
                    break;
                case '\r':
                    escaped.append("\\r");
                    break;
                case '\t':
                    escaped.append("\\t");
                    break;
                default:
                    if (c < 0x20) {
                        escaped.append(String.format(Locale.US, "\\u%04x", (int) c));
                    } else {
                        escaped.append(c);
                    }
            }
        }
        return escaped.toString();
    }
}
