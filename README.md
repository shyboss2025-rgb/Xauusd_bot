# XAUUSD Hybrid Sniper — corrected Android project

## What was corrected
- Explicit Java 17 and Kotlin JVM 17 compatibility settings.
- Clear Android Gradle Plugin/Kotlin plugin versions and Android project structure.
- Replaced the earlier H1/RSI-only prototype logic with the requested manual Hybrid Sniper checklist:
  - H4 EMA 50 / EMA 200 trend
  - H1 liquidity sweep
  - M15 market structure shift
  - M5 engulfing-candle confirmation
- Risk default is 2%, capped at 2%; reward:risk is fixed at 1:3.
- Validates swing placement and broker contract inputs.
- Floors lot size to the broker volume step and rejects trades if minimum volume exceeds the risk budget.
- Approval is a local log only. No broker connection or order execution is implemented.

## Important
This app uses manually entered, closed-candle values. It does not download live prices, connect to Deriv, send MT5 orders, or run unattended. It is a decision-support prototype, not a proven profitable strategy. With a $15 account, 2% risk is $0.30; minimum contract volume may make a valid trade impossible. Always demo-test and verify the XAUUSD contract specification.

## Build environment
- Android Gradle Plugin: 8.7.3
- Gradle: 8.9
- JDK: 17
- Kotlin: 2.0.21
- compileSdk: 35
- minSdk: 23

Open the project root (the folder containing `settings.gradle`) in Android Studio, allow Gradle sync, then build the debug APK.
