# cos201-No.29-assignment
# Development Environment and First Java Program

This repository contains my first Java program and documents how I set up my Java development environment.

## Program: `HelloWorld.java` (Java Welcome Hub)

A menu-driven, interactive console program that goes beyond "Hello, World!" while still proving my Java environment works.

| Menu option | What it does |
|-------------|--------------|
| 1. Greet me again | Time-aware greeting (morning, afternoon or evening) and asks what I want to build with Java. |
| 2. Environment check | Shows date/time, Java version, vendor, JVM, operating system, CPU cores and maximum JVM memory. |
| 3. Java basics quiz | 5 multiple-choice questions about Java fundamentals, with scoring and feedback. |
| 4. Number guessing game | Guess a number from 1 to 50 in 7 attempts, with too high/too low and hot/cold hints. |
| 5. Programming tip | Shows a random beginner programming tip. |
| 6. Session summary | Shows time spent, quizzes taken (best score), games played and won, and tips viewed. |
| 0. Exit | Prints the session summary and exits. |

**Other features:** all input is validated (empty text, letters instead of numbers and out-of-range values are rejected without crashing), and the program loops until I choose to exit.

## Development Environment

| Tool | Details |
|------|---------|
| Java Development Kit (JDK) | [Your JDK version, e.g. JDK 21] |
| Code editor | Visual Studio Code |
| VS Code extension | Extension Pack for Java |
| Operating system | [Your OS, e.g. Windows 11] |

### Setup steps
1. Downloaded and installed the JDK (version 11 or newer).
2. Installed Visual Studio Code.
3. Installed the **Extension Pack for Java** from the VS Code Extensions panel.
4. Verified the installation in the terminal:
   ```
   java -version
   javac -version
   ```

## How to Compile and Run

Open a terminal in the project folder, then run:

```
javac HelloWorld.java
java HelloWorld
```

Or in VS Code, open `HelloWorld.java` and click the **Run** button above the `main` method.

## Sample Output

```
==========================================
      HELLO, WORLD!  -  JAVA WELCOME HUB
           My First Java Program
==========================================
What is your name? Ada

Good morning, Ada! Welcome to Java.
What would you like to build with Java? (press Enter to skip) a mobile app
Great goal! Building a mobile app is a perfect reason to learn Java.

------------------------------------------
  MAIN MENU  -  Hi, Ada!
------------------------------------------
  1. Greet me again
  2. Environment check
  3. Java basics quiz
  4. Number guessing game
  5. Programming tip
  6. Session summary
  0. Exit
------------------------------------------
Choose an option (0-6): 2

------ ENVIRONMENT CHECK ------
Date and time  : Sunday, 01 November 2026 at 09:30
Java version   : 21.0.2
Java vendor    : Eclipse Adoptium
JVM            : OpenJDK 64-Bit Server VM
Operating sys  : Windows 11 (amd64)
CPU cores      : 8
Max JVM memory : 4096 MB
Status         : PASSED - Java is installed and working!
-------------------------------
```

*(Replace this with the real output from your own machine, or add a screenshot.)*

## Verification

I compiled and executed the program successfully, tested every menu option, and confirmed that the output is correct.

## Author

- **Name:** [Your Full Name]
- **Student ID:** [Your Student ID]
- **Course:** [Course Name / Code]
