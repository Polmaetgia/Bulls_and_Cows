import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        cowsAndBulls();
    }

    public static String digitString(int countOfSymbols) {
        SecretCode secretCode = new SecretCode();
        String originalLine = secretCode.combination(countOfSymbols);
        StringBuilder digitString = new StringBuilder();
        for (char c : originalLine.toCharArray()) {
            if (Character.isDigit(c)) {
                digitString.append(c);
            }
        }
        if (digitString.isEmpty()) {
            return "no digits";
        }

        int fistNumber = Character.getNumericValue(digitString.toString().charAt(0));
        int lastNumber = Character.getNumericValue(digitString.toString().charAt(digitString.length() - 1));
        return fistNumber + "-" + lastNumber;
    }

    public static String letterString(int countOfSymbols) {
        SecretCode secretCode = new SecretCode();
        String originalLine = secretCode.combination(countOfSymbols);
        StringBuilder letterString = new StringBuilder();
        for (char c : originalLine.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                letterString.append(c);
            }
        }
        if (letterString.isEmpty()) {
            return "no letters";
        }
        char fistLetter = letterString.toString().charAt(0);
        char lastLetter = letterString.toString().charAt(letterString.length() - 1);
        return fistLetter + "-" + lastLetter;
    }


    public static String message(int bulls, int cows, String secretCode) {
        StringBuilder sb = new StringBuilder();
        sb.append("Grade: ");
        if (bulls == 0) {
            if (cows == 0) {
                sb.append("None");
            } else if (cows == 1) {
                sb.append(cows).append(" cow\n");
            } else {
                sb.append(cows).append(" cows\n");
            }
        } else if (bulls == 1) {
            if (cows == 0) {
                sb.append(bulls).append(" bull\n");
                if (bulls == secretCode.length()) {
                    sb.append("Congratulations! You guessed the secret code.\n");
                }
            } else if (cows == 1) {
                sb.append(bulls).append(" bull and ").append(cows).append(" cow\n");
            } else {
                sb.append(bulls).append(" bulls and ").append(cows).append(" cows\n");
            }
        } else {
            if (cows == 0) {
                sb.append(bulls).append(" bulls\n");
                if (bulls == secretCode.length()) {
                    sb.append("Congratulations! You guessed the secret code.\n");
                }
            } else if (cows == 1) {
                sb.append(bulls).append(" bulls and ").append(cows).append(" cow\n");
            } else {
                sb.append(bulls).append(" bulls and ").append(cows).append(" cows\n");
            }
        }
        return sb.toString();
    }

    public static boolean flag(int length, int countOfSymbols) {
        if (length > countOfSymbols) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with " +
                    "%d unique symbols.\n", length, countOfSymbols);
            return false;
        }
        if (length <= 0) {
            System.out.println("Error: length cannot be less than or equal to zero");
            return false;
        }
        if (countOfSymbols > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return false;
        }
        return true;
    }

    public static void cowsAndBulls() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Input the length of the secret code:");
            int length = scanner.nextInt();
            System.out.println("Input the number of possible symbols in the code:");
            int countOfSymbols = scanner.nextInt();
            if (flag(length, countOfSymbols)) {
                SecretCode secretCode = new SecretCode();
                secretCode.setCode(secretCode.combination(length, countOfSymbols));
                String outputMessage = "The secret is prepared: " +
                        "*".repeat(secretCode.getCode().length()) + " (" + digitString(countOfSymbols) + ", "
                        + letterString(countOfSymbols) + ")";
                System.out.println(outputMessage);
                System.out.println("Okay, let's start a game!");
                InputCode inputCode = new InputCode();
                int count = 0;

                while (true) {
                    System.out.printf("Turn %d:\n", ++count);
                    inputCode.setCode(inputCode.enterInputCode(secretCode.getCode()));
                    char[] codeArray = inputCode.codeArray(inputCode.getCode());
                    char[] secretCodeArray = secretCode.codeArray(secretCode.getCode());
                    int bulls = 0, cows = 0;
                    for (int i = 0; i < secretCodeArray.length; i++) {
                        for (int j = 0; j < codeArray.length; j++) {
                            if (secretCodeArray[i] == codeArray[j] && i == j) {
                                bulls++;
                            } else if (secretCodeArray[i] == codeArray[j]) {
                                cows++;
                            }

                        }
                    }
                    String message;
                    if (bulls == secretCodeArray.length) {
                        message = message(bulls, cows, secretCode.getCode());
                        System.out.println(message);
                        break;
                    } else {
                        message = message(bulls, cows, secretCode.getCode());
                        System.out.println(message);
                    }

                }
            }
        } catch (InputMismatchException inputMismatchException) {
            System.out.printf("Error: \"%s\" isn't a valid number.", scanner.nextLine());
        }
    }
}