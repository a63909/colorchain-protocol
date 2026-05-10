# NLnet Resubmission Draft Notes

## Improved short abstract

ColorChain is an independent mobile-first peer-to-peer protocol stack for resilient decentralized communication and state synchronization across unstable or restricted networks.

The project separates protocol infrastructure from applications built on top of it. Current prototype implementations include Android nodes, JVM/desktop node work, peer-to-peer block propagation, local synchronization, wallet recovery, transaction flow, and an early Messenger MVP foundation.

The requested NLnet work focuses on stabilizing the open protocol core: Android/JVM node reliability, synchronization behavior, message/block format documentation, reproducible test scenarios, mobile resilience experiments, and an advisory AI-assisted trust metadata layer.

The AI trust layer is not part of consensus and does not determine truth or censor content. It is intended as optional local metadata for spam, duplication, and anomaly signals.

The expected outcome is a documented open-source prototype and public evidence package demonstrating resilient mobile-first P2P propagation, verifiable records, and transparent trust metadata for public-interest information sharing.

## Updated technical focus

The revised framing should emphasize protocol infrastructure rather than an app or token.

Core work areas:

- mobile-first P2P transport and recovery;
- canonical block/state synchronization;
- Android/JVM node reliability;
- public message/block format documentation;
- reproducible local and multi-node demo scenarios;
- advisory trust metadata that remains outside consensus;
- public evidence reports with sanitized logs and screenshots.
- reproducible standalone Java two-node reference demo that reviewers can compile and run locally.
- reproducible standalone Java Messenger two-node demo that reviewers can compile and run locally.
- negative/tamper checks in the public Java reference demos so reviewers can inspect rejection behavior.

Messenger MVP, wallet recovery, and transaction flow should be described as internal prototype milestones that show the protocol can support application layers. They should not be presented as audited production products.

## Updated milestones

Suggested NLnet milestone framing:

1. Core protocol documentation and reference artifacts
2. Android/JVM node synchronization reliability
3. Mobile resilience experiments and constrained-network testing
4. Reproducible public demo scenarios and evidence package
5. Advisory AI trust metadata MVP and evaluation notes
6. Open-source release report and sustainability documentation

## Budget and milestone framing

Funding should be framed around engineering time, testing, documentation, release preparation, and public evidence production.

Suggested categories:

- protocol engineering;
- Android/JVM node stabilization;
- synchronization and recovery testing;
- mobile network resilience experiments;
- documentation and reproducible scenarios;
- AI trust metadata prototype and evaluation notes;
- project management and reporting.

Wallet/transaction and Messenger MVP work should be mentioned only as prototype evidence around the core protocol. They should not be framed as financial product delivery or production messaging launch.

## Safe AI trust wording

The AI-assisted trust layer is advisory only.

It does not:

- determine truth;
- censor content;
- remove messages;
- participate in consensus;
- replace human or community judgment;
- create a centralized moderation authority.

It may provide optional local metadata for:

- spam-like patterns;
- duplicated content;
- abnormal propagation;
- suspicious metadata patterns;
- manipulation-risk indicators.

False positives and false negatives must be documented.

## Application-layer evidence

The Messenger MVP foundation can be mentioned as evidence that the protocol can support application-layer communication flows.

Safe wording:

- signed message envelopes;
- delivery acknowledgements;
- local persistence;
- redacted JVM/desktop archival metadata;
- non-minting MessageProofClaim prototype.

Avoid claiming production messaging readiness, end-to-end encryption, or live messaging rewards.

## Wallet and transaction evidence

Wallet recovery and transaction flow can be mentioned as internal Android prototype milestones.

Safe wording:

- seed phrase restore / wallet recovery path validated in controlled local testing;
- transaction flow validated in internal prototype testing;
- no real seed phrase, private key, or raw wallet data is published;
- not audited production wallet infrastructure;
- not a financial product claim.

## Positioning sentence

ColorChain is independent mobile-first open protocol infrastructure for resilient peer-to-peer communication, verifiable state synchronization, and advisory trust metadata across unstable or restricted network environments.
