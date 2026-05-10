# ColorChain Architecture

ColorChain is designed as an independent mobile-first peer-to-peer protocol stack for resilient decentralized communication and state synchronization.

The architecture separates the core protocol from application layers and experimental utility mechanisms. Android and JVM/desktop nodes are prototype implementations used to validate the protocol direction.

## Protocol stack

### 1. Transport layer

The transport layer is responsible for node-to-node communication.

It is designed around:

- peer-to-peer communication;
- peer connection and discovery direction;
- connection recovery;
- constrained mobile networks;
- unstable or restricted network environments.

HELLO / capability negotiation is treated as an internal prototype direction and should be documented publicly only as implementation evidence becomes reproducible.

### 2. Canonical block/state layer

The canonical block/state layer organizes records into verifiable state transitions.

It includes:

- block-based records;
- canonical hashing;
- block validation;
- local synchronization;
- wallet-state / UTXO handling in the internal Android prototype;
- future deterministic replay and state-validation documentation where applicable.

This layer is the main protocol infrastructure focus for public documentation and grant work.

### 3. Mobile node layer

ColorChain treats mobile devices as first-class network participants.

The mobile node layer focuses on:

- Android-first node participation;
- intermittent connectivity;
- battery and network constraints;
- physical phones as first-class nodes;
- mobile-to-desktop and mobile-to-mobile synchronization testing.

Desktop/JVM nodes remain important for reliability testing, long-running participation, archival experiments, and reproducible public demos.

### 4. Application messaging layer

ColorChain is beginning to support application-layer messaging above the Core v1 P2P/block foundation.

The current internal prototype includes:

- Messenger MVP foundation;
- signed MessageEnvelope;
- DeliveryAck;
- local message persistence;
- redacted JVM/desktop archival metadata;
- non-minting MessageProofClaim.

This layer is early prototype work. It does not claim production-grade end-to-end encryption or live LBU₽ rewards for messaging.

### 5. Advisory trust layer

The advisory trust layer generates optional metadata about content and network behavior.

Possible signals include:

- spam probability;
- duplication risk;
- manipulation-risk patterns;
- abnormal propagation patterns;
- suspicious metadata patterns.

This layer is not consensus-critical and is not a centralized moderation authority. Network validity remains cryptographic/protocol-level; trust metadata is advisory and should be explainable to users.

Where possible, trust-signal experiments should move toward local, client-side, or on-device heuristics rather than centralized decision-making.

### 6. Experimental utility/incentive layer

LBU₽ is treated as an experimental native utility unit for prototype-stage network incentives.

This layer may explore:

- node incentives;
- block rewards;
- capped participation incentives;
- future communication-backed participation proofs after technical validation, abuse-resistance design, and legal review.

This is not the center of the NLnet protocol positioning. ColorChain should not be presented as a token-first project, a public offering, a market-listing effort, or a financial-return product.

Messaging rewards are not enabled.

## Design principles

ColorChain is designed around these principles:

- independence;
- decentralization;
- mobile-first access;
- protocol infrastructure before application hype;
- transparent trust signals;
- open documentation;
- censorship resistance;
- user agency;
- no dependency on a central platform;
- no dependency on an external blockchain ecosystem.
