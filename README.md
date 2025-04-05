# JUnit Testing in Java - PZ1 Course Project

## Overview
This repository demonstrates my implementation of JUnit testing during the PZ1 (Programming Fundamentals 1) course. It showcases test-driven development practices and unit testing in Java.

## Project Structure
/src
/main/java - Main Java source code
/test/java - JUnit test cases
/lib - JUnit and other library dependencies

## Key Features
- Comprehensive unit tests for all core functionality
- Test-driven development approach
- Parameterized tests for multiple input scenarios
- Exception handling tests

## Example Test Case
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
    
    @Test
    void testReverseString() {
        StringUtils utils = new StringUtils();
        assertEquals("cba", utils.reverse("abc"));
        assertEquals("", utils.reverse(""));
    }
    
    @Test
    void testNullInput() {
        StringUtils utils = new StringUtils();
        assertThrows(IllegalArgumentException.class, 
            () -> utils.reverse(null));
    }
}
```

Testing Methodology
Test First: Wrote tests before implementation

Complete Coverage: Aimed for 100% method coverage

Edge Cases: Tested boundary conditions

Readable Tests: Used descriptive test names

Dependencies:
JUnit 5
Java 11+

How to Run
In IDE:
Right-click test directory

Select "Run Tests"

Command Line:
```bash
javac -cp junit-platform-console-standalone.jar src/test/java/*.java
java -jar junit-platform-console-standalone.jar --class-path src/test/java --scan-class-path
```
Best Practices Applied
Single responsibility per test method

Clear assertion messages

Proper test isolation

Meaningful test names

Author
Jakub Kierznowski - PZ1 Course Submission
