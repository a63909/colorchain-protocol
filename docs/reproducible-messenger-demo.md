# Reproducible Messenger Two-Node Demo

## Purpose

This demo provides a minimal reproducible Java reference artifact for reviewers. It simulates a Messenger MVP flow between two local ColorChain-style nodes.

## What it demonstrates

- MessageEnvelope creation;
- canonical message hash calculation;
- demo-only envelope integrity verification;
- local storage on sender and receiver;
- DeliveryAck flow;
- delivered-state confirmation;
- non-minting MessageProofClaim creation;
- PASS/FAIL console result.

## What it does not demonstrate

- real Android app behavior;
- real WebSocket/P2P transport;
- production secure messaging;
- production end-to-end encryption;
- audited messenger security;
- real private user messages;
- LBU₽ minting or rewards;
- wallet recovery;
- transaction flow;
- production privacy guarantees.

## Run instructions

Unix/macOS/Linux:

```bash
mkdir -p /tmp/colorchain-reference-build
javac -d /tmp/colorchain-reference-build src/reference/java/org/colorchain/protocol/*.java
java -cp /tmp/colorchain-reference-build org.colorchain.protocol.ReferenceMessengerTwoNodeDemo
```

Windows PowerShell:

```powershell
$build = "$env:TEMP\colorchain-reference-build"
Remove-Item -Recurse -Force $build -ErrorAction SilentlyContinue
New-Item -ItemType Directory -Force $build | Out-Null
javac -d $build src\reference\java\org\colorchain\protocol\*.java
java -cp $build org.colorchain.protocol.ReferenceMessengerTwoNodeDemo
```

## Expected output

```text
COLORCHAIN_REFERENCE_MESSENGER_TWO_NODE_DEMO
NODE_A_START messages=0 acks=0 claims=0
NODE_B_START messages=0 acks=0 claims=0
NODE_A_CREATE_ENVELOPE type=MSG_ENVELOPE messageHash=13cd4d16ec0e6d19c3dba43bff88e86002660d49b214662dabac30b4b1f255bf stored=true
NODE_A_SEND type=MSG_ENVELOPE messageHash=13cd4d16ec0e6d19c3dba43bff88e86002660d49b214662dabac30b4b1f255bf
NODE_B_RECEIVE type=MSG_ENVELOPE messageHash=13cd4d16ec0e6d19c3dba43bff88e86002660d49b214662dabac30b4b1f255bf sameHash=true signatureValid=true stored=true
NODE_B_SEND type=MSG_ACK messageHash=13cd4d16ec0e6d19c3dba43bff88e86002660d49b214662dabac30b4b1f255bf ackHash=1a2d6196913cb26a8aba674ef3995916d19dcc722796bb0067238b5b8ce852cd
NODE_A_RECEIVE type=MSG_ACK messageHash=13cd4d16ec0e6d19c3dba43bff88e86002660d49b214662dabac30b4b1f255bf ackMatches=true delivered=true
NODE_A_CREATE_PROOF_CLAIM messageHash=13cd4d16ec0e6d19c3dba43bff88e86002660d49b214662dabac30b4b1f255bf claimHash=2fe5061ce57f7780dbedc9195fd67e9da5506a3433b05a205362fb374a908c00 minting=false
RESULT=PASS
```

## Relation to Messenger MVP foundation

The internal Android/JVM prototype includes signed message envelopes, delivery acknowledgements, local persistence, redacted JVM/desktop archival metadata, and non-minting MessageProofClaim.

This local Java demo is intentionally smaller and reproducible. It does not replace the internal Android/JVM implementation, but gives reviewers a command-line artifact that demonstrates the application-layer message/ACK/proof-claim flow.

## Safety

No real message, private key, seed phrase, wallet material, IP address, device ID, personal data, or private archive content is included.
