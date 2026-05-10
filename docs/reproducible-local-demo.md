# Reproducible Local Two-Node Demo

## Purpose

This demo provides a minimal reproducible Java reference artifact for reviewers. It simulates two ColorChain-style nodes accepting the same canonical record hash.

## What it demonstrates

- canonical payload construction;
- deterministic hash calculation;
- local accept on Node A;
- simulated send from Node A to Node B;
- hash verification on Node B;
- same-hash accept on Node B;
- tampered payload rejection;
- mismatched previous-hash rejection;
- PASS/FAIL console result.

## What it does not demonstrate

- real Android app behavior;
- real WebSocket/P2P transport;
- production consensus;
- wallet recovery;
- transaction flow;
- LBU₽ rewards;
- production security;
- end-to-end encryption.

## Negative checks

The demo includes negative checks so that it does not only demonstrate the happy path. It verifies that a tampered canonical payload is rejected and that a record with a mismatched previous hash is rejected.

## Run instructions

Unix/macOS/Linux style:

```bash
mkdir -p /tmp/colorchain-reference-build
javac -d /tmp/colorchain-reference-build src/reference/java/org/colorchain/protocol/*.java
java -cp /tmp/colorchain-reference-build org.colorchain.protocol.ReferenceTwoNodeDemo
```

Windows PowerShell style:

```powershell
$build = "$env:TEMP\colorchain-reference-build"
Remove-Item -Recurse -Force $build -ErrorAction SilentlyContinue
New-Item -ItemType Directory -Force $build | Out-Null
javac -d $build src\reference\java\org\colorchain\protocol\*.java
java -cp $build org.colorchain.protocol.ReferenceTwoNodeDemo
```

## Expected output

```text
COLORCHAIN_REFERENCE_TWO_NODE_DEMO
NODE_A_START height=0 head=919b714f78e43fde38bb4535114c9088b5cc285d1c09c7ee903c0ab190247480
NODE_B_START height=0 head=919b714f78e43fde38bb4535114c9088b5cc285d1c09c7ee903c0ab190247480
NODE_A_LOCAL_ACCEPT hash=4c606739eb2fd6f1cc61b9b7416d7dda62ae47355f165049b96b0b45efcd64ec height=1
NODE_A_SEND hash=4c606739eb2fd6f1cc61b9b7416d7dda62ae47355f165049b96b0b45efcd64ec
NODE_B_RECEIVE hash=4c606739eb2fd6f1cc61b9b7416d7dda62ae47355f165049b96b0b45efcd64ec
NODE_B_VERIFY sameHash=true prevMatches=true
NODE_B_TAMPER_CHECK sameHash=false rejected=true
NODE_B_WRONG_PREV_CHECK prevMatches=false rejected=true
NODE_B_ACCEPT hash=4c606739eb2fd6f1cc61b9b7416d7dda62ae47355f165049b96b0b45efcd64ec height=1
RESULT=PASS
```

## Relation to live demo

The live Android/phone demo remains available here:

https://youtu.be/ejcNmULAKG8

The local Java demo is intentionally smaller and reproducible. It complements the live demo by giving reviewers a command-line artifact they can compile and run.

## Safety

No seed phrase, private key, real wallet data, IP address, device ID, or private message is included.
