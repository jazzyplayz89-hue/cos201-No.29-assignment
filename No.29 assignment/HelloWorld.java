import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

/**
 * HelloWorld - Java Welcome Hub (My First Java Program)
 *
 * An interactive, menu-driven first program that:
 *  1. Greets the user by name with a time-aware greeting
 *  2. Runs an environment check (Java version, OS, CPU cores, memory)
 *  3. Offers a 5-question Java basics quiz with scoring
 *  4. Plays a number guessing game with hints
 *  5. Shows random programming tips
 *  6. Shows a session summary (time spent, scores, games won)
 */
public class HelloWorld {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static final long startTime = System.currentTimeMillis();

    private static String name;
    private static int quizzesTaken = 0;
    private static int bestQuizScore = -1;
    private static int gamesPlayed = 0;
    private static int gamesWon = 0;
    private static int tipsSeen = 0;

    // ---------- Quiz data ----------
    private static final String[] QUESTIONS = {
        "Which method is the entry point of a Java program?",
        "Which command compiles a Java source file?",
        "What is the file extension of a Java source file?",
        "Which data type stores whole numbers?",
        "Which symbol ends most statements in Java?"
    };

    private static final String[][] OPTIONS = {
        {"start()", "main()", "run()", "init()"},
        {"java", "javac", "jar", "javadoc"},
        {".class", ".jav", ".java", ".jvm"},
        {"String", "double", "boolean", "int"},
        {":", ".", ";", ","}
    };

    private static final int[] ANSWERS = {1, 1, 2, 3, 2}; // index of the correct option

    // ---------- Tips data ----------
    private static final String[] TIPS = {
        "The class name must match the file name, including capital letters.",
        "Read error messages from the top. The first error is usually the real one.",
        "Use meaningful variable names: 'totalPrice' beats 'x' every time.",
        "Test your code often. Small changes are easier to debug than big ones.",
        "Java is case-sensitive: 'System' and 'system' are different things.",
        "Comment WHY you did something, not just what the code does.",
        "Indent your code consistently. It makes bugs far easier to spot.",
        "Always close your Scanner when you are done with it."
    };

    // =====================================================
    //                        MAIN
    // =====================================================
    public static void main(String[] args) {
        printBanner();
        name = readText("What is your name? ");
        greetUser();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose an option (0-6): ", 0, 6);
            System.out.println();

            switch (choice) {
                case 1:
                    greetUser();
                    break;
                case 2:
                    environmentCheck();
                    break;
                case 3:
                    runQuiz();
                    break;
                case 4:
                    playGuessingGame();
                    break;
                case 5:
                    showTip();
                    break;
                case 6:
                    showSummary();
                    break;
                case 0:
                    running = false;
                    break;
            }
        }

        showSummary();
        System.out.println("\nCompiled, run and verified. Happy coding, " + name + "!");
        scanner.close();
    }

    // =====================================================
    //                      MENU ACTIONS
    // =====================================================

    private static void greetUser() {
        int hour = LocalDateTime.now().getHour();
        String greeting;
        if (hour < 12) {
            greeting = "Good morning";
        } else if (hour < 17) {
            greeting = "Good afternoon";
        } else {
            greeting = "Good evening";
        }
        System.out.println("\n" + greeting + ", " + name + "! Welcome to Java.");

        String goal = readOptionalText("What would you like to build with Java? (press Enter to skip) ");
        if (goal.isEmpty()) {
            System.out.println("No problem. You can decide as you learn.");
        } else {
            System.out.println("Great goal! Building " + goal + " is a perfect reason to learn Java.");
        }
    }

    private static void environmentCheck() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy 'at' HH:mm");
        long maxMemoryMb = Runtime.getRuntime().maxMemory() / (1024 * 1024);

        System.out.println("------ ENVIRONMENT CHECK ------");
        System.out.println("Date and time  : " + LocalDateTime.now().format(formatter));
        System.out.println("Java version   : " + System.getProperty("java.version"));
        System.out.println("Java vendor    : " + System.getProperty("java.vendor"));
        System.out.println("JVM            : " + System.getProperty("java.vm.name"));
        System.out.println("Operating sys  : " + System.getProperty("os.name")
                + " (" + System.getProperty("os.arch") + ")");
        System.out.println("CPU cores      : " + Runtime.getRuntime().availableProcessors());
        System.out.println("Max JVM memory : " + maxMemoryMb + " MB");
        System.out.println("Status         : PASSED - Java is installed and working!");
        System.out.println("-------------------------------");
    }

    private static void runQuiz() {
        System.out.println("------ JAVA BASICS QUIZ ------");
        System.out.println("Answer with A, B, C or D.\n");
        int score = 0;

        for (int i = 0; i < QUESTIONS.length; i++) {
            System.out.println("Question " + (i + 1) + " of " + QUESTIONS.length + ": " + QUESTIONS[i]);
            for (int j = 0; j < 4; j++) {
                System.out.println("   " + (char) ('A' + j) + ") " + OPTIONS[i][j]);
            }

            int picked = readLetter("Your answer: ");
            if (picked == ANSWERS[i]) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Not quite. The answer is " + (char) ('A' + ANSWERS[i])
                        + ") " + OPTIONS[i][ANSWERS[i]] + ".\n");
            }
        }

        quizzesTaken++;
        if (score > bestQuizScore) {
            bestQuizScore = score;
        }

        System.out.println("You scored " + score + " out of " + QUESTIONS.length + ".");
        if (score == QUESTIONS.length) {
            System.out.println("Perfect score! You are ready for the next lesson.");
        } else if (score >= 3) {
            System.out.println("Nice work! A little revision and you will ace it.");
        } else {
            System.out.println("Keep practising. Try tip #5 in the menu, then retake the quiz.");
        }
    }

    private static void playGuessingGame() {
        final int maxNumber = 50;
        final int maxAttempts = 7;
        int secret = random.nextInt(maxNumber) + 1;
        boolean won = false;

        System.out.println("------ GUESSING GAME ------");
        System.out.println("I'm thinking of a number between 1 and " + maxNumber + ".");
        System.out.println("You have " + maxAttempts + " attempts.\n");

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            int guess = readInt("Attempt " + attempt + "/" + maxAttempts + " - your guess: ", 1, maxNumber);

            if (guess == secret) {
                System.out.println("YES! " + secret + " is correct. You got it in " + attempt
                        + (attempt == 1 ? " attempt!" : " attempts!"));
                won = true;
                break;
            }

            String direction = guess < secret ? "Too low." : "Too high.";
            int distance = Math.abs(guess - secret);
            String heat;
            if (distance <= 3) {
                heat = "Burning hot!";
            } else if (distance <= 8) {
                heat = "Warm.";
            } else {
                heat = "Cold.";
            }
            System.out.println(direction + " " + heat);
        }

        gamesPlayed++;
        if (won) {
            gamesWon++;
        } else {
            System.out.println("\nOut of attempts! The number was " + secret + ". Better luck next time.");
        }
    }

    private static void showTip() {
        tipsSeen++;
        System.out.println("------ PROGRAMMING TIP ------");
        System.out.println(TIPS[random.nextInt(TIPS.length)]);
    }

    private static void showSummary() {
        long totalSeconds = (System.currentTimeMillis() - startTime) / 1000;
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;

        System.out.println("------ SESSION SUMMARY ------");
        System.out.println("Name            : " + name);
        System.out.println("Time spent      : " + minutes + " min " + seconds + " sec");
        System.out.println("Quizzes taken   : " + quizzesTaken
                + (bestQuizScore >= 0 ? " (best: " + bestQuizScore + "/" + QUESTIONS.length + ")" : ""));
        System.out.println("Games played    : " + gamesPlayed + " (won: " + gamesWon + ")");
        System.out.println("Tips viewed     : " + tipsSeen);
        System.out.println("-----------------------------");
    }

    // =====================================================
    //                        DISPLAY
    // =====================================================

    private static void printBanner() {
        System.out.println("==========================================");
        System.out.println("      HELLO, WORLD!  -  JAVA WELCOME HUB  ");
        System.out.println("           My First Java Program          ");
        System.out.println("==========================================");
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("------------------------------------------");
        System.out.println("  MAIN MENU  -  Hi, " + name + "!");
        System.out.println("------------------------------------------");
        System.out.println("  1. Greet me again");
        System.out.println("  2. Environment check");
        System.out.println("  3. Java basics quiz");
        System.out.println("  4. Number guessing game");
        System.out.println("  5. Programming tip");
        System.out.println("  6. Session summary");
        System.out.println("  0. Exit");
        System.out.println("------------------------------------------");
    }

    // =====================================================
    //                   INPUT HELPER METHODS
    // =====================================================

    private static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = scanner.nextLine().trim();
            if (!text.isEmpty()) {
                return text;
            }
            System.out.println("Input cannot be empty. Try again.");
        }
    }

    private static String readOptionalText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Please enter a whole number from " + min + " to " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("That is not a valid whole number. Try again.");
            }
        }
    }

    /** Reads A, B, C or D and returns 0-3. */
    private static int readLetter(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = scanner.nextLine().trim().toUpperCase();
            if (text.length() == 1 && text.charAt(0) >= 'A' && text.charAt(0) <= 'D') {
                return text.charAt(0) - 'A';
            }
            System.out.println("Please type A, B, C or D.");
        }
    }
}
