package bullscows;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        final int digit;
        final int symbol;

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Input the length of the secret code:");
            String tempDigit = scanner.nextLine();
            if (!tempDigit.matches("[0-9]+")) {
                System.out.printf("Error: \"%S\" isn't a valid number.%n", tempDigit);
                System.exit(0);
            }
            // digit = scanner.nextInt();
            digit = Integer.parseInt(tempDigit);
        }

        if (digit > 36) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", digit);
            System.exit(0);
        } else if (digit <= 0) {
            System.out.println("Error: There must be at least 1 secret code.");
            System.exit(0);
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Input the number of possible symbols in the code:");
            String tempSymbol = scanner.nextLine();
            if (!tempSymbol.matches("[0-9]+")) {
                System.out.printf("Error: \"%S\" isn't a valid number.%n", tempSymbol);
                System.exit(0);
            }
            // symbol = scanner.nextInt();
            symbol = Integer.parseInt(tempSymbol);
        }

        if (digit > symbol) {
            System.out.println("Error: it's not possible to generate a code with a length of 6 with 5 unique symbols.");
            System.exit(0);
        }

        if (symbol > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        } else if (symbol <= 0) {
            System.out.println("Error: There should be at least 1 symbol.");
            System.exit(0);
        }

        // String answer = randomGenerator(digit);
        StringBuilder star = new StringBuilder();
        for (int i = 0; i < digit; i++) {
            star.append("*");
        }

        StringBuilder range;
        char lastSymbol;
        if (symbol <= 10) {
            range = new StringBuilder("0-");
            lastSymbol = (char) ('0' + symbol - 1);
        } else {
            range = new StringBuilder("0-9, a-");
            lastSymbol = (char) ('a' + symbol - 10 - 1);
        }
        range.append(lastSymbol);

        String answer = randomGenerator(digit);
        System.out.printf("The secret is prepared: %S (%S).%n", star.toString(), range); //TODO
        System.out.println("Okay, let's start a game!");
        for (int turn = 1;;turn++) {
            int bull = 0;
            int cow = 0;
            System.out.printf("Turn %d:%n", turn);
            String trial;
            try (Scanner scanner = new Scanner(System.in);) {
                trial = scanner.nextLine();
            }
            for (int i = 0; i < answer.length(); i++) {
                if (trial.contains(String.valueOf(answer.charAt(i)))) {
                    cow++;
                }
                if (answer.charAt(i) == trial.charAt(i)) {
                    cow--;
                    bull++;
                }
            }
            if (bull == digit) {
                System.out.printf("Grade: %d bulls%n", digit);
                break;
            } else if (bull == 0 && cow == 0) {
                System.out.println("Grade: None. ");
            } else if (bull != 0 && cow == 0) {
                System.out.printf("Grade: %d bull(s).%n", bull);
            } else if (cow != 0 && bull == 0) {
                System.out.printf("Grade: %d cow(s).%n", cow);
            } else {
                System.out.printf("Grade: %d bull(s) and %d cow(s).%n", bull, cow);
            }
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }

    public static String randomGenerator(int length) {
        ArrayList<String> randomList = new ArrayList<>();

        if (length <= 10) {
            for (int i = 0; i < length; i++) {
                randomList.add(Integer.toString(i));
            }
        } else {
            for (int i = 0; i < 10; i++) {
                randomList.add(Integer.toString(i));
            }
            for (char j = 'a'; j < (char) ('a' + length - 10 - 1); j++) {
                randomList.add(Character.toString(j));
            }
        }

        Collections.shuffle(randomList);

        StringBuilder result = new StringBuilder();
        for(String i: randomList) {
            result.append(i);
        }

        return result.toString();
    }
}