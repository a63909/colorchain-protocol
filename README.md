# ColorChain

ColorChain is an independent, protocol-agnostic, mobile-first peer-to-peer network for resilient public-interest information sharing.

It enables mobile and desktop nodes to publish, synchronize, and preserve information without relying on a central server, centralized platform, or external blockchain ecosystem.

The project combines:

- peer-to-peer message propagation;
- block-based persistence;
- mobile-first node architecture;
- transparent AI-assisted trust signals for spam, duplication, manipulation patterns, and suspicious information flows.

## Current status

ColorChain is currently an early working prototype with:

- Android node prototype;
- JVM/desktop node prototype;
- peer-to-peer communication;
- block creation and validation;
- local synchronization;
- early block production logic.

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

## Public documentation

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
