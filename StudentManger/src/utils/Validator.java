package utils;

import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Validator {

    private static final Scanner SCANNER = new Scanner(System.in);

    public Validator() {
    }

    public static int getInt(String messInf, String numOutOfRange, String errorNum, int min, int max) {
        while (true) {
            try {
                System.out.println(messInf);
                int number = Integer.parseInt(SCANNER.nextLine().trim());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println(numOutOfRange);
            } catch (Exception e) {
                System.out.println(errorNum);
            }
        }
    }

    public static double getDouble(String messInf, String numOutOfRange, String errorNum, int min, int max) {
        while (true) {
            try {
                System.out.println(messInf);
                double number = Double.parseDouble(SCANNER.nextLine().trim());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println(numOutOfRange);
            } catch (Exception e) {
                System.out.println(errorNum);
            }
        }
    }

    public static String getString(String messInf, String messError, String REGEX) {
        while (true) {
            System.out.println(messInf);
            String str = SCANNER.nextLine();
            if (str.matches(REGEX)) {
                return str;
            }
            System.out.println(messError);
        }
    }

}
