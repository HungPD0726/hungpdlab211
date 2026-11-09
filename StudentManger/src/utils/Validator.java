package utils;

import java.util.Scanner;

/**
 * Utility class providing robust console input validation.
 * Enforces correct data types, numeric ranges, and regex pattern matching.
 *
 * @author HP
 */
public class Validator {

    private static final Scanner SCANNER = new Scanner(System.in);

    public Validator() {
    }
    
    /**
     * Prompts for and validates an integer within a specified [min, max] range.
     * <p>
     * Execution flow:
     * <ul>
     *   <li>Runs an infinite loop {@code while (true)} with a {@code try-catch} block.</li>
     *   <li>Reads console input via {@code nextLine().trim()} and parses it using {@link Integer#parseInt}.</li>
     *   <li>Validates whether the parsed integer falls within [min, max].
     *     - If valid: Returns the number.
     *     - If out of range: Displays {@code numOutOfRange}.</li>
     *   <li>If non-integer input is encountered: Catches exception and displays {@code errorNum}.</li>
     * </ul>
     *
     * @param messInf Prompt message displayed to the user.
     * @param numOutOfRange Error message displayed when input is out of range.
     * @param errorNum Error message displayed when input cannot be parsed as an integer.
     * @param min Minimum allowable integer value.
     * @param max Maximum allowable integer value.
     * @return A valid integer within the range [min, max].
     */
    public static int getInt(String messInf, String numOutOfRange, String errorNum, int min, int max){
        while (true) {            
            try {
                System.out.println(messInf);
                int number = Integer.parseInt(SCANNER.nextLine().trim());
                if (number >= min && number <= max){
                    return number;
                }
                System.out.println(numOutOfRange);
            } catch (Exception e) {
                System.out.println(errorNum);
            }
        }
    }
    
    /**
     * Prompts for and validates a double within a specified [min, max] range.
     * <p>
     * Execution flow:
     * <ul>
     *   <li>Continuously prompts until valid double input within [min, max] is entered.</li>
     *   <li>Parses input using {@link Double#parseDouble}.</li>
     *   <li>Catches parse exceptions and validates boundary constraints.</li>
     * </ul>
     *
     * @param messInf Prompt message displayed to the user.
     * @param numOutOfRange Error message displayed when input is out of range.
     * @param errorNum Error message displayed when input is not a valid double.
     * @param min Minimum allowable double value.
     * @param max Maximum allowable double value.
     * @return A valid double within the range [min, max].
     */
    public static double getDouble(String messInf, String numOutOfRange, String errorNum, int min, int max){
        while (true) {            
            try {
                System.out.println(messInf);
                double number = Double.parseDouble(SCANNER.nextLine().trim());
                if (number >= min && number <= max){
                    return number;
                }
                System.out.println(numOutOfRange);
            } catch (Exception e) {
                System.out.println(errorNum);
            }
        }
    }

    /**
     * Prompts for and validates a string against a specified regular expression (Regex).
     * <p>
     * Execution flow:
     * <ul>
     *   <li>Displays prompt {@code messInf} and reads string input from the console.</li>
     *   <li>Validates the input string against {@code REGEX} via {@link String#matches(String)}.</li>
     *   <li>If matched, returns the valid string.</li>
     *   <li>If unmatched, prints error message {@code messError} and prompts again.</li>
     * </ul>
     *
     * @param messInf Prompt message displayed to the user.
     * @param messError Error message displayed when input does not match regex.
     * @param REGEX Regular expression pattern to validate against.
     * @return A valid string matching the regex.
     */
    public static String getString(String messInf, String messError, String REGEX){
        while (true) {            
            System.out.println(messInf);
            String str = SCANNER.nextLine();
            if(str.matches(REGEX)){
                return str;
            }
            System.out.println(messError);
        }
    }

}
