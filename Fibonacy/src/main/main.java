package main;

/**
 * Program to display the 45 sequence Fibonacci using recursion.
 * Course: LAB211
 * Assignment Code: J1.S.P0009
 * LOC: 50
 *
 * @author HP
 */
public class main {

    /**
     * Recursive method to find and display Fibonacci sequence.
     * Uses recursion (tail recursion) to calculate and display 45 terms
     * in O(n) time complexity, avoiding exponential O(2^n) overhead.
     *
     * @param term   number of remaining Fibonacci terms to display
     * @param lower  the previous Fibonacci number (starts at 1)
     * @param higher the current Fibonacci number (starts at 0)
     * @return the Fibonacci number at current step
     */
    public static int fibonacci(int term, int lower, int higher) {
        if (term < 1) {
            return higher;
        }
        System.out.print(higher);
        if (term > 1) {
            System.out.print(", ");
        }
        return fibonacci(term - 1, higher, lower + higher);
    }

    /**
     * Main method to execute the program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("The 45 sequence fibonacci:");
        fibonacci(45, 1, 0);
        System.out.println();
    }
}
