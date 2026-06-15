# Bowling Scoring API

A Spring Boot REST API for calculating American Ten-Pin Bowling scores.

## 🛠️ Tech Stack

This project is built using modern Java and Spring Ecosystem tools:

* **Java Version:** 21
* **Framework:** Spring Boot 3.5.15
* **Build Tool:** Maven
* **Boilerplate Reduction:** Lombok
* **Encoding:** UTF-8

## 📦 Key Dependencies

* **Spring Web:** For building RESTful web services and controllers.
* **Spring Validation:** Jakarta Bean Validation (`@Valid`, `@Pattern`, etc.) for robust API input checking.
* **Jackson Databind Nullable:** For handling advanced JSON serialization and deserialization.

## 🧪 Testing & Code Quality Tools

This project places a heavy emphasis on test reliability and code quality, utilizing the following plugins:

* **JUnit 5 (Jupiter) & Spring Boot Test:** The primary testing frameworks.
* **JaCoCo (Java Code Coverage):** Configured to automatically generate test coverage reports during the standard build lifecycle.
* **PITest (Mutation Testing):** Integrated to evaluate the effectiveness of the unit tests by injecting faults into the code (`com.bnpp.bowling.*`).

---

## 🚀 Getting Started

### Prerequisites

Ensure you have the following installed on your local machine:
* [Java Development Kit (JDK) 21](https://adoptium.net/)
* [Apache Maven](https://maven.apache.org/download.cgi) (or use the provided Maven wrapper if included in your repo)

### 📝 Development Guidelines & Encoding

To prevent cross-platform character issues, this project strictly uses **UTF-8** encoding.
If you are contributing to this project, please ensure your IDE (IntelliJ, Eclipse, VS Code) is configured to:
* Use `UTF-8` for all Global, Project, and Default encoding settings.
* Use `UTF-8` for the JVM standard output/error (e.g., `-Dfile.encoding=UTF-8`).
* Use `LF` (Line Feed) for line separators.


### Building the Project

To compile the code, process Lombok annotations, and build the runnable JAR:

```bash
mvn clean install

```

### Running the Application

You can start the Spring Boot server using the Spring Boot Maven plugin:

```bash
mvn spring-boot:run

```

By default, the application will start on `http://localhost:8080`.

---

## 🚦 Running Tests

### Standard Unit Tests

To execute the JUnit 5 test suite:

```bash
mvn clean test

```

### Code Coverage (JaCoCo)

The JaCoCo plugin is bound to the test phase. Running the tests will automatically generate a coverage report.
You can view the HTML report by navigating to:

```text
target/site/jacoco/index.html

```

### Mutation Testing (PITest)

To run mutation testing and ensure your tests are actually catching behavioral changes:

```bash
mvn clean test-compile org.pitest:pitest-maven:mutationCoverage -Djacoco.skip=true

```

*Note: PITest is configured to utilize 4 threads for faster execution and excludes the main `BowlingApplication` class from mutation.* You can view the generated mutation report at:

```text
target/pit-reports/index.html

```

## 🔌 API Reference

### Calculate Score
Calculates the total score for a valid sequence of American Ten-Pin Bowling rolls.

**Endpoint:** `POST /api/v1/bowling/score`

**Headers:** `Content-Type: application/json`

#### Success Response (200 OK)

**Request Body:**
```json
{
  "sequence": "X 7/ 9- X -8 8/ -6 X X X81"
}

```

**Response Body:**

```json
{
  "score": 167,
  "sequence": "X 7/ 9- X -8 8/ -6 X X X81"
}

```

---

#### Validation Error Response (400 Bad Request)

If the API receives an invalid payload (e.g., empty string, null, or invalid characters like letters), it will cleanly reject the request.

**Request Body:**

```json
{
  "sequence": "X 5/ ABC"
}

```

**Response Body:**

```json
{
  "sequence": "Sequence contains invalid characters. Only digits (0-9), 'X', '/', '-', and spaces are allowed."
}

```

---

## ⚙️ Important Notes for Java 21

Due to security updates in Java 21 regarding dynamic agent loading (which affects testing libraries like Mockito), the Maven plugins in this project (`maven-surefire-plugin` and `pitest-maven`) have been explicitly configured with the `-XX:+EnableDynamicAgentLoading` JVM argument. This ensures tests run smoothly without disruptive console warnings.

```

```