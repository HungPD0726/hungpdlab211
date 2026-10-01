/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;

/**
 *
 * @author win
 */
public class Validator {

    private static final Scanner SCANNER = new Scanner(System.in);

    private Validator() {

    }

    public static int getInt(String messageInfo, String messsageErrorOutOfRange,
            String messageErrorNumber, int min, int max) {
        do {
            try {
                System.out.print(messageInfo);
                int number = Integer.parseInt(SCANNER.nextLine());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println(messsageErrorOutOfRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }

    public static double getDouble(String messageInfo, String messsageErrorOutOfRange,
            String messageErrorNumber, double min, double max) {
        do {
            try {
                System.out.print(messageInfo);
                double number = Double.parseDouble(SCANNER.nextLine());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println(messsageErrorOutOfRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }

    public static String getString(String messageInfo, String messageError, final String REGEX) {
        do {
            System.out.print(messageInfo);
            String str = SCANNER.nextLine().trim();
            if (str.matches(REGEX)) {
                return str;
            }
            System.out.println(messageError);
        } while (true);
    }

    public static String getName(String messageInfo, String messageError, final String REGEX) {
        do {
            System.out.print(messageInfo);
            String str = SCANNER.nextLine();
            if (str.matches(REGEX)) {
                String[] arr = str.split("\\s+");
                StringBuilder newStr = new StringBuilder();
                for (String word : arr) {
                    if (!word.isEmpty()) {
                        newStr.append(word.substring(0, 1).toUpperCase())
                                .append(word.substring(1).toLowerCase())
                                .append(" ");
                    }
                }
                return newStr.toString().trim();
            }
            System.out.println(messageError);
        } while (true);
    }

}
