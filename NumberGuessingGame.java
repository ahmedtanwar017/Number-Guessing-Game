import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 5;
        int maxRounds = 3;
        int totalScore = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        for (int round = 1; round <= maxRounds; round++) {
            int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            System.out.println("Round " + round + ": Guess the number between " + minRange + " and " + maxRange + ":");
            int attemptsLeft = maxAttempts;
            int previousGuess = -1;
            boolean hintUsed = false;

            while (attemptsLeft > 0) {
                System.out.print("Attempts left: " + attemptsLeft + ". Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    totalScore += attemptsLeft * 15; // Increased bonus multiplier
                    break;
                } else if (previousGuess != -1) {
                    int difference = Math.abs(randomNumber - userGuess);
                    int previousDifference = Math.abs(randomNumber - previousGuess);

                    if (difference < previousDifference) {
                        System.out.println("Warmer! Try again.");
                    } else {
                        System.out.println("Colder! Try again.");
                    }
                } else {
                    System.out.println("Not quite. Try again.");
                }

                previousGuess = userGuess;
                attemptsLeft--;

                if (attemptsLeft == 0) {
                    System.out.println("Sorry, you've run out of attempts. The correct number was: " + randomNumber);
                } else if (attemptsLeft == maxAttempts / 2 && !hintUsed) {
                    System.out.println("Would you like a hint? (yes/no)");
                    String useHint = scanner.next();
                    if (useHint.equalsIgnoreCase("yes")) {
                        System.out.println("Hint: The number is divisible by 5.");
                        hintUsed = true;
                    }
                }
            }

            System.out.println("Your score for Round " + round + ": " + (maxAttempts - attemptsLeft) * 15);
            System.out.println("Total Score: " + totalScore);

            if (round < maxRounds) {
                System.out.println("Do you want to play the next round? (yes/no)");
                String playAgain = scanner.next();
                if (!playAgain.equalsIgnoreCase("yes")) {
                    break;
                }
            }
        }

        System.out.println("Thanks for playing! Your final total score: " + totalScore);
        scanner.close();
    }
}