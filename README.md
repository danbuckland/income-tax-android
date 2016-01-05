# Income Tax Calculator

An Android app for calculating PAYE tax and National Insurance deductions in the UK, developed in part to demonstrate Behaviour Driven Development (BDD) with [Cucumber](https://github.com/cucumber/cucumber/) and [Calabash for Android](https://github.com/calabash/calabash-android).

## Documentation

You can find more information about this project and follow along with its progress by visiting the [Income Development](https://trello.com/b/Ujd76HdL/income-development) Trello Board.

Whilst **Income** has a practical use, it is primarily intended as an educational aid for testers and developers looking to understand the Behaviour Driven Development of mobile apps. The project is provided as open source under the MIT license so that it can be freely used and adapted.

## Building & Running

Although it should be possible to build and run the project using any Android IDE, the project was built using Android Studio and therefore it's recommended to use Android Studio with the latest Android SDK tools.

1. Open the root folder of the project in Android Studio
2. Sync Gradle by selecting **Sync Project with Gradle Files** in Android Studio
3. Select **Run** and choose an emulator or physical device to deploy to

## Running Tests

As well as automated Cucumber tests that can be run using Calabash, calculations done by the application are unit tested and should be run before building.

#### Unit Tests

Unit tests have been written using JUnit 4. If you are familiar with unit testing in Android Studio, the tests should be easy enough to find and run.

All unit tests can be found in the `income/app/src/test` directory.

#### Cucumber BDD Tests

All [Cucumber](https://github.com/cucumber/cucumber/) tests have been written in Ruby using [Calabash for Android](https://github.com/calabash/calabash-android). To run, you'll need to have a recent version of Ruby installed. If you're new to Ruby, I'd highly recommend using [RVM](https://rvm.io/) with the latest stable version of Ruby, and [creating](https://rvm.io/gemsets/creating) and [using](https://rvm.io/gemsets/using) a specific Gemset for running the tests.

##### Install required Ruby gems

1. In a terminal, navigate to the project root directory
2. Enter `gem install bundler` to install **Bundler**
3. Enter `bundle install` to install required Ruby gems including **Cucumber** and **Calabash**

##### Run the tests against the build

1. Build the Android project locally
2. Launch an emulator or connect an Android device in debug mode
3. Enter `calabash-android run app/build/outputs/apk/app-debug.apk` to install the built app and run the tests


## Copyright

Copyright (c) 2015 Dan Buckland and Contributors. See LICENSE for details.
