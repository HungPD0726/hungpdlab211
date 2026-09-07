package main;

import java.util.Random;
import java.util.Scanner;

/**
 * Program to search an element in a sorted array using Binary Search.
 * Course: LAB211
 * Assignment Code: J1.S.P0006
 * LOC: 70
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
     * Sorts array in ascending order using Bubble Sort algorithm.
     *
     * @param array array to be sorted
     */
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
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
     * Searches for a target value in a sorted array using Binary Search (recursive).
     *
     * @param array  sorted array
     * @param target value to search for
     * @param left   left boundary index
     * @param right  right boundary index
     * @return index of target if found, otherwise -1
     */
    public static int binarySearch(int[] array, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (array[mid] == target) {
            return mid;
        } else if (array[mid] > target) {
            return binarySearch(array, target, left, mid - 1);
        } else {
            return binarySearch(array, target, mid + 1, right);
        }
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

        // Step 2: Generate random elements, sort array, and display
        int[] array = generateRandomArray(length);
        bubbleSort(array);
        System.out.print("Sorted array: ");
        displayArray(array);

        // Step 3: Perform binary search and display result
        int foundIndex = binarySearch(array, searchValue, 0, length - 1);
        if (foundIndex != -1) {
            System.out.println("Found " + searchValue + " at index: " + foundIndex);
        } else {
            System.out.println("Not found " + searchValue + " in array.");
        }
    }
}
