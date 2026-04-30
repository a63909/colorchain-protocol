# NLnet / NGI Zero Commons Application Summary

## Project

ColorChain: Independent mobile-first P2P protocol for resilient public-interest information sharing

## Public repository

https://github.com/a63909/colorchain-protocol

## Short summary

ColorChain is an independent, protocol-agnostic, mobile-first peer-to-peer protocol for resilient public-interest information sharing.

It enables mobile and desktop nodes to publish, synchronize, and preserve information without relying on a central server, centralized platform, or external blockchain ecosystem.

The project combines peer-to-peer message propagation, block-based persistence, mobile-first node architecture, and transparent AI-assisted trust metadata.

The AI-assisted trust layer is advisory. It helps identify spam-like content, duplicated or coordinated messages, manipulation-risk patterns, and suspicious information flows, without censoring content or deciding what users are allowed to see.

## Why this fits NGI Zero Commons

ColorChain is aligned with open internet infrastructure, decentralization, user control, data sovereignty, and public-interest technology.

The project is designed as standalone open infrastructure, not as a plugin for another blockchain or platform. Its purpose is to explore a practical open-source building block for resilient information sharing between mobile and desktop nodes.

The work is relevant to NGI Zero Commons because it focuses on:

- independent peer-to-peer infrastructure;
- mobile-first access;
- open protocol documentation;
- reproducible test scenarios;
- verifiable block-based records;
- advisory and transparent trust metadata;
- user agency instead of centralized platform control.

## Current status

ColorChain is an early working prototype, not a production system.

Current internal implementation includes:

- Android node prototype;
- JVM/desktop node prototype;
- peer-to-peer communication;
- block creation and validation;
- local synchronization;
- early block production logic.

The public repository currently includes:

- protocol documentation;
- roadmap;
- architecture notes;
- demo plan;
- demo script;
- pre-pilot test status;
- screenshot evidence plan;
- open-source scope;
- AI trust-layer design;
- JSON trust metadata examples;
- standalone Java reference implementation.

## Requested support

Requested amount: EUR 42,000

Planned duration: 9 months

The requested support will be used for:

1. Protocol and node stabilization
2. Open-source protocol documentation
3. AI-assisted trust layer prototype
4. Mobile-first resilience experiments
5. Usability and pilot testing
6. Project management, reporting, and release

## Expected outcomes

The expected outcomes are:

- improved Android/JVM node synchronization;
- documented message and block format;
- reproducible test scenarios;
- public demo package;
- small pilot network;
- first AI-assisted trust layer MVP;
- public reference implementation artifacts;
- open-source documentation and final report.

## Open-source scope

The repository currently uses the MIT license.

Planned open components include:

- protocol documentation;
- message/block format documentation;
- reference message schema;
- reference trust metadata schema;
- AI trust-layer methodology;
- reproducible test scenarios;
- selected networking/protocol components;
- demo scripts and public test reports.

The project will not publish private wallet material, seed phrases, private keys, personal data, unreleased UI assets, device-specific configs, or sensitive anti-abuse details that would create immediate attack risk.

## Reference implementation

A small standalone Java reference implementation is available under:

src/reference/java/

It demonstrates:

- message representation;
- canonical hashing;
- advisory trust metadata;
- simple transparent trust scoring heuristics.

It is not production consensus code and is not copied from the private Android application.

It can be compiled from the repository root with:

javac src/reference/java/org/colorchain/protocol/*.java

It can be run with:

java -cp src/reference/java org.colorchain.protocol.ReferenceDemo

## AI-assisted trust layer

The AI-assisted trust layer is designed to provide advisory metadata for decentralized information flows.

Non-goals:

- it does not determine truth;
- it does not censor content;
- it does not remove messages;
- it does not replace human or community judgment;
- it does not create a centralized moderation authority.

Initial signals may include:

- spam-like patterns;
- repeated phrases;
- duplicated content;
- coordinated content similarity;
- abnormal propagation;
- emotionally manipulative wording;
- suspicious metadata patterns.

## Technical challenges

The main technical challenges are:

1. Reliable mobile P2P synchronization
2. Compact and verifiable message/block format
3. Abuse resistance without centralized moderation
4. Explainable advisory trust signals
5. Mobile-first resource and connectivity constraints
6. Reproducible tests and public documentation

## Evidence plan

Public measurements are pending.

The public demo package should include:

- Android node running;
- JVM/desktop node running;
- peer connection evidence;
- message or block propagation;
- synchronization logs;
- block hash or height evidence;
- optional trust metadata examples.

Screenshots and demo assets will be placed under:

media/

## Funding status

The project has not received external grant, investment, or institutional funding so far.

Development has been self-funded by the project founder.

## Important boundaries

ColorChain should not be presented as:

- a Filecoin integration;
- an Ethereum app;
- a Solana app;
- an IPFS-only storage project;
- a token-first project;
- a speculative crypto asset;
- a centralized platform;
- a content moderation company;
- a truth-ranking authority.

## Strongest positioning sentence

ColorChain is independent mobile-first P2P infrastructure for resilient, verifiable, and censorship-resistant public-interest information sharing, with transparent AI-assisted trust signals that help users evaluate information without relying on centralized platforms.
