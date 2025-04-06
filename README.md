# 🧪 PZ1 – Java Unit Testing Project

This repository contains solutions and JUnit 5 tests created as part of the *Programming Fundamentals* course.

## 📦 Contents

- ✅ Java code in `src/main/java`
- 🧪 JUnit tests in `src/test/java`
- 🌐 A simple HTML CV page with styling (`cv.html`, `style.css`)
- 📄 `output.json` with sample data

## ▶️ How to Run Tests

In **IntelliJ IDEA**:
1. Open the project.
2. Navigate to `src/test/java`.
3. Right-click the folder or any test class and select **"Run Tests"**.

## 🧪 Example Test

Here's a sample JUnit test from the `MorseTest` class:

```java
@Test
void morseTest() {
    Morse m = new Morse();
    String input = "Hello World!";
    String expected = ".... . .-.. .-.. --- / .-- --- .-. .-.. -.-.--";
    assertEquals(expected, m.morse(input));
}
```

## 🛠 Technologies Used

- Java 11+
- JUnit 5
- IntelliJ IDEA (recommended)

## 👤 Author

Jakub Kierznowski  
🔗 [GitHub Repo](https://github.com/qualv13/PZ1)

---

💡 *Simple tests make solid code.*
