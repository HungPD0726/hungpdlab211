package main;

import java.util.Random;
import java.util.Scanner;

/**
 * Program to search an element in an array using Linear Search.
 * Course: LAB211
 * Assignment Code: J1.S.P0010
 * LOC: 50
 *
 * @author HP
 */
public class main {

    /**
     * Helper method to validate and read an integer from user input.
     *
     * @param scanner      Scanner instance
     * @param msg          prompt message
     * @param positiveOnly true if the input must be a positive integer (> 0)
     * @return valid integer
     */
    public static int checkInputInt(Scanner scanner, String msg, boolean positiveOnly) {
        while (true) {
            System.out.println(msg);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (positiveOnly && value <= 0) {
                    System.out.println("Please enter a positive decimal number (> 0).");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }

    /**
     * Generates an array of random integers in the range [0, length).
     *
     * @param length number of elements
     * @return randomly generated array
     */
    public static int[] generateRandomArray(int length) {
        Random random = new Random();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(length);
        }
        return array;
    }

    /**
     * Displays array elements formatted as [e1, e2, ..., en].
     *
     * @param array array to display
     */
    public static void displayArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /**
     * Searches for a target value in an array using Linear Search.
     *
     * @param array  array to search in
     * @param target value to search for
     * @return index of first occurrence of target, or -1 if absent
     */
    public static int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Main method to execute the program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Prompt user to enter number of array and search value
        int length = checkInputInt(scanner, "Enter number of array:", true);
        int searchValue = checkInputInt(scanner, "Enter search value:", false);

        // Step 2: Generate random elements and display array
        int[] array = generateRandomArray(length);
        System.out.print("The array: ");
        displayArray(array);

        // Step 3: Perform linear search and display result
        int foundIndex = linearSearch(array, searchValue);
        if (foundIndex != -1) {
            System.out.println("Found " + searchValue + " at index: " + foundIndex);
        } else {
            System.out.println("Not found " + searchValue + " in array.");
        }
    }
}
