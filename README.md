
# Selenium-Gradle

![Status](https://img.shields.io/badge/Lifecycle-active-green)
![Release](https://img.shields.io/badge/Release-v1.0.0-blue)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
![Java](https://img.shields.io/badge/Java-8%2B-orange)
![Gradle](https://img.shields.io/badge/Gradle-7.6+-02303A)

A scalable test automation framework using Selenium WebDriver, Cucumber, and Gradle, designed for UI testing across modern browsers.

## Table of Contents

- [Selenium-Gradle](#selenium-gradle)
  - [Table of Contents](#table-of-contents)
  - [Features](#features)
  - [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Setup](#setup)
  - [Project Structure](#project-structure)
  - [Customization](#customization)
  - [Test Execution](#test-execution)
  - [Branch Naming Convention](#branch-naming-convention)
  - [License](#license)

## Features

- Automated UI testing with Selenium WebDriver
- Gradle build and dependency management
- Easy configuration and extensibility

## Getting Started

### Prerequisites

- Java (JDK 8 or higher)
- Gradle (or use the Gradle Wrapper)
- ChromeDriver/GeckoDriver installed and in your PATH

### Setup

1. Clone the repository:

    ```bash
    git clone https://github.com/yourusername/Selenium-Gradle.git
    cd Selenium-Gradle
    ```

2. Build the project:

    ```bash
    ./gradlew build
    ```

3. Run tests:

    ```bash
    ./gradlew test
    ```

## Project Structure

```plaintext
.
├── src
│   └── test
│       ├── java
│       │   ├── pages
│       │   ├── runner
│       │   └── steps
│       └── resources
│           ├── features
│           └── logback.xml
├── .gitattributes
├── .gitignore
├── build.gradle
├── gradlew
├── gradlew.bat
├── README.md
└── settings.gradle
```

## Customization

- Update dependencies in `build.gradle` as needed.
- Define your test scenarios using `.feature` files under `src/test/resources/features`.
- This framework follows the **Page Object Model (POM)** pattern for test automation:
  - `src/test/java/pages`: page classes containing locators and interaction methods.
  - `src/test/java/steps`: step definitions that map Gherkin steps to actions.
  - `src/test/java/runner`: JUnit test runner configuration.

## Test Execution

Run all tests and generate the basic Cucumber HTML report:

```bash
./gradlew clean test
```

The report will be generated at:

```text
build/reports/cucumber/index.html
```

## Branch Naming Convention

We use `master` as the stable base branch. New branches should follow this pattern:

| Type              | Prefix        | Example                      |
|-------------------|--------------|------------------------------|
| New feature       | `feature/`    | `feature/driver-manager`     |
| Test cases        | `test/`       | `test/ws1-smoke-flow`        |
| Bug fix           | `fix/`        | `fix/incorrect-selector`     |
| Refactor          | `refactor/`   | `refactor/basepage-cleanup`  |
| Tool integration  | `integration/`| `integration/allure-report`  |

## License

This project is licensed under Apache 2.0.
