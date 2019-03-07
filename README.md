# SimpleAppDemo
Demo application using kotlin

# Stack
Support //TODO - Migrate to AndroidX
kotlin 100%
Retrofit
Dagger
RxJava

# Test
The test of this application are split into two different types
Unit test
Integration test

Unit test will validate the correct behaviour for individual classes
Integration test will use Espresso and MockServer to verify the application shows what was expected

# To improve on the future
Add Room to allow offline mode
UI in PostActivity needs to be improved.
Improve unit test coverage with negative scenarios
Replace support libraries for AndroidX
Add static code analisys as lint and findbugs
Obfuscate code for production
Add fastline for automate CI/CD

# How to run unit test
./gradlew :app.network:test
./gradlew :app:test

# How to run integration test
./gradlew :app:connectedDebugAndroidTest
