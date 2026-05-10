# ColorChain

ColorChain is an independent mobile-first peer-to-peer protocol stack for resilient decentralized communication and state synchronization across unstable or restricted networks.

It separates protocol infrastructure from applications built on top of it. Android and JVM/desktop nodes are prototype implementations used to validate the core protocol direction.

ColorChain does not depend on a central server, centralized platform, or external blockchain ecosystem.

The project combines:

- peer-to-peer transport and message propagation;
- block-based state synchronization;
- mobile-first node architecture;
- application-layer messaging experiments;
- transparent advisory trust metadata for spam, duplication, anomaly, and manipulation-risk signals.

## Protocol, not just an app

ColorChain Core is the protocol layer.

Android/JVM nodes, wallet functionality, transaction flow, Messenger MVP, and trust-signal experiments are prototype implementations and application layers around the core protocol.

The public documentation should therefore be read as protocol infrastructure work first, not as a token-first product or a single mobile application.

## Current status

ColorChain is currently an early working prototype with:

- Android node prototype;
- JVM/desktop node prototype;
- peer-to-peer communication;
- block creation and validation;
- local synchronization;
- early block production logic.

## Current working components

- Android node prototype;
- JVM/desktop node direction;
- peer-to-peer communication;
- block creation and validation;
- local synchronization;
- same-hash block propagation between Android emulator and physical Samsung phone;
- seed phrase restore / wallet recovery path;
- transaction flow;
- Messenger MVP foundation;
- advisory AI trust metadata design.

Recent internal prototype milestones:

- live same-hash P2P block propagation between Android emulator and physical Samsung phone;
- repaired and validated Android transaction flow;
- repaired and validated seed phrase restore / wallet recovery path;
- initial Messenger MVP foundation with signed message envelopes, delivery acknowledgements, local persistence, redacted JVM archival metadata, and non-minting MessageProofClaim prototype.

## Goal

The goal is to build independent open infrastructure for censorship-resistant, verifiable, and resilient public-interest information sharing.

## Independence

ColorChain is designed as standalone infrastructure.

It does not depend on any external blockchain ecosystem, centralized platform, or storage provider. Future interoperability with external systems may be explored only if it strengthens the protocol without compromising independence.

## AI-assisted trust layer

The AI trust layer is advisory and transparent.

It does not censor content and does not decide what users are allowed to see. It provides metadata that helps users evaluate risks such as:

- spam-like content;
- duplicated or coordinated content;
- manipulation patterns;
- abnormal propagation behavior;
- suspicious information flows.

## Repository purpose

This repository is intended to document the open protocol direction, roadmap, architecture, and grant-facing development plan for ColorChain.

## For grant reviewers

For NLnet/NGI Zero Commons reviewers, the project-specific application summary is available here:

[docs/nlnet-application.md](docs/nlnet-application.md)

A sanitized propagation proof is available here:
[docs/evidence-std-to-physical-gen.md](docs/evidence-std-to-physical-gen.md)

Live demo video:
https://youtu.be/ejcNmULAKG8

Reproducible local Java demo:
[docs/reproducible-local-demo.md](docs/reproducible-local-demo.md)

Reproducible Messenger Java demo:
[docs/reproducible-messenger-demo.md](docs/reproducible-messenger-demo.md)

## Public documentation

- [docs/nlnet-application.md](docs/nlnet-application.md)
- [docs/alliance-product-update.md](docs/alliance-product-update.md)
- [docs/reproducible-local-demo.md](docs/reproducible-local-demo.md)
- [docs/reproducible-messenger-demo.md](docs/reproducible-messenger-demo.md)
- [docs/evidence-std-to-physical-gen.md](docs/evidence-std-to-physical-gen.md)
- [docs/evidence-wallet-restore.md](docs/evidence-wallet-restore.md)
- [docs/evidence-transaction-flow.md](docs/evidence-transaction-flow.md)
- [docs/wallet-and-transactions.md](docs/wallet-and-transactions.md)
- [docs/messenger-mvp.md](docs/messenger-mvp.md)
- [docs/nlnet-resubmission-draft.md](docs/nlnet-resubmission-draft.md)
- [docs/roadmap.md](docs/roadmap.md)
- [docs/architecture.md](docs/architecture.md)
- [docs/demo.md](docs/demo.md)
- [docs/demo-script.md](docs/demo-script.md)
- [docs/test-results.md](docs/test-results.md)
- [docs/screenshots.md](docs/screenshots.md)
- [docs/open-source-scope.md](docs/open-source-scope.md)
- [docs/ai-trust-layer.md](docs/ai-trust-layer.md)
- [docs/grant-positioning.md](docs/grant-positioning.md)

## Reference implementation

A small standalone Java reference implementation is available under:

[src/reference/java/](src/reference/java/)

It demonstrates message representation, canonical hashing, and advisory trust metadata. It is not production consensus code.

The reference implementation now includes a reproducible local two-node demo that can be compiled and run without Android, private data, or external dependencies.

The reference implementation also includes a reproducible Messenger two-node demo that demonstrates a minimal MessageEnvelope -> DeliveryAck -> non-minting MessageProofClaim flow without Android, private messages, or external dependencies.

## License and trademarks

This repository is released under the MIT License.

Project names, marks, and branding are not licensed as trademarks under the MIT License. See [TRADEMARKS.md](TRADEMARKS.md).
