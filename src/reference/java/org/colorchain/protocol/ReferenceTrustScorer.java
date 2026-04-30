package org.colorchain.protocol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public final class ReferenceTrustScorer {
    private static final List<String> SUSPICIOUS_CALL_TO_ACTIONS = Arrays.asList(
            "forward now",
            "share immediately",
            "urgent action",
            "everyone must",
            "do not ignore",
            "send to all");

    // This is a transparent placeholder for the future AI-assisted trust layer MVP.
    public TrustMetadata score(String content) {
        String safeContent = content == null ? "" : content.trim();
        Set<String> flags = new LinkedHashSet<>();
        double spamScore = 0.05d;
        double duplicationRisk = 0.05d;
        double manipulationRisk = 0.05d;

        if (safeContent.isEmpty()) {
            flags.add("empty-content");
            return new TrustMetadata(
                    0.90d,
                    0.70d,
                    0.80d,
                    "high",
                    new ArrayList<>(flags),
                    "The content is empty or nearly empty, so risk is elevated for low-information or malformed messages.");
        }

        String normalized = safeContent.toLowerCase(Locale.ROOT);
        List<String> words = tokenize(normalized);

        if (safeContent.length() < 20) {
            spamScore += 0.25d;
            manipulationRisk += 0.20d;
            flags.add("short-text");
        }

        int repeatedWordCount = repeatedWordCount(words);
        if (repeatedWordCount > 0) {
            spamScore += Math.min(0.35d, repeatedWordCount * 0.08d);
            duplicationRisk += Math.min(0.20d, repeatedWordCount * 0.04d);
            flags.add("repeated-word");
        }

        int repeatedPhraseCount = repeatedPhraseCount(normalized);
        if (repeatedPhraseCount > 0) {
            spamScore += Math.min(0.25d, repeatedPhraseCount * 0.10d);
            duplicationRisk += Math.min(0.30d, repeatedPhraseCount * 0.08d);
            flags.add("repeated-phrase");
        }

        int uppercaseWordCount = uppercaseWordCount(safeContent);
        if (uppercaseWordCount >= 3) {
            manipulationRisk += Math.min(0.35d, uppercaseWordCount * 0.05d);
            flags.add("uppercase-emphasis");
        }

        int exclamationCount = countChar(safeContent, '!');
        if (exclamationCount >= 3) {
            manipulationRisk += Math.min(0.20d, exclamationCount * 0.03d);
            flags.add("excessive-exclamation");
        }

        for (String phrase : SUSPICIOUS_CALL_TO_ACTIONS) {
            if (normalized.contains(phrase)) {
                manipulationRisk += 0.18d;
                flags.add("suspicious-call-to-action");
                break;
            }
        }

        if (containsRepeatedSentence(normalized)) {
            duplicationRisk += 0.25d;
            flags.add("duplicated-content-marker");
        }

        String trustSignal = classify(spamScore, duplicationRisk, manipulationRisk);
        String explanation = buildExplanation(flags, trustSignal);

        return new TrustMetadata(
                spamScore,
                duplicationRisk,
                manipulationRisk,
                trustSignal,
                new ArrayList<>(flags),
                explanation);
    }

    private static List<String> tokenize(String normalized) {
        String cleaned = normalized.replaceAll("[^a-z0-9\\s]", " ").trim();
        if (cleaned.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(cleaned.split("\\s+"));
    }

    private static int repeatedWordCount(List<String> words) {
        int repeated = 0;
        String previous = null;
        int streak = 1;
        for (String word : words) {
            if (word.equals(previous)) {
                streak++;
                if (streak == 2) {
                    repeated++;
                }
            } else {
                streak = 1;
            }
            previous = word;
        }
        return repeated;
    }

    private static int repeatedPhraseCount(String normalized) {
        int repeated = 0;
        String[] phrases = normalized.split("[\\.!?]");
        Set<String> seen = new LinkedHashSet<>();
        for (String phrase : phrases) {
            String trimmed = phrase.trim();
            if (trimmed.length() < 8) {
                continue;
            }
            if (!seen.add(trimmed)) {
                repeated++;
            }
        }
        return repeated;
    }

    private static int uppercaseWordCount(String content) {
        int count = 0;
        for (String raw : content.split("\\s+")) {
            String lettersOnly = raw.replaceAll("[^A-Za-z]", "");
            if (lettersOnly.length() >= 3 && lettersOnly.equals(lettersOnly.toUpperCase(Locale.ROOT))) {
                count++;
            }
        }
        return count;
    }

    private static int countChar(String content, char target) {
        int count = 0;
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == target) {
                count++;
            }
        }
        return count;
    }

    private static boolean containsRepeatedSentence(String normalized) {
        String[] parts = normalized.split("[\\.!?]");
        Set<String> seen = new LinkedHashSet<>();
        for (String part : parts) {
            String trimmed = part.trim();
            if (trimmed.length() < 12) {
                continue;
            }
            if (!seen.add(trimmed)) {
                return true;
            }
        }
        return false;
    }

    private static String classify(double spamScore, double duplicationRisk, double manipulationRisk) {
        double max = Math.max(spamScore, Math.max(duplicationRisk, manipulationRisk));
        if (max >= 0.70d) {
            return "high";
        }
        if (max >= 0.35d) {
            return "medium";
        }
        return "low";
    }

    private static String buildExplanation(Set<String> flags, String trustSignal) {
        if (flags.isEmpty()) {
            return "No strong heuristic risk markers were detected in this reference example.";
        }
        return "The reference scorer assigned a "
                + trustSignal
                + " signal because it detected: "
                + String.join(", ", flags)
                + ".";
    }
}
