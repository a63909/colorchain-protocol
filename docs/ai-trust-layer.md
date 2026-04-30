# AI-assisted Trust Layer

## Purpose

The AI layer provides advisory trust metadata for decentralized information flows.

## Non-goals

- It does not determine truth.
- It does not censor content.
- It does not remove messages.
- It does not replace human/community judgment.
- It does not create a centralized moderation authority.

## Example output

```json
{
  "spam_score": 0.12,
  "duplication_risk": 0.08,
  "manipulation_risk": 0.31,
  "trust_signal": "medium",
  "flags": ["short-text", "repeated-phrase"],
  "explanation": "The message contains repeated phrases but no severe spam indicators."
}
```

## Initial signals

- spam-like patterns
- repeated phrases
- duplicated content
- coordinated content similarity
- abnormal propagation
- emotionally manipulative wording
- suspicious metadata patterns

## Safety limitations

- false positives are possible
- false negatives are possible
- adversarial behavior is expected
- scores should be explainable
- users should be able to inspect why a signal was produced

## Grant milestone

The first grant milestone should implement an MVP of the AI-assisted trust layer together with evaluation notes describing observed limitations, explainability requirements, and areas that still need public measurement.
