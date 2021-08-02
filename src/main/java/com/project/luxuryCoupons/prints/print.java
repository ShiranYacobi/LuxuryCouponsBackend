package com.project.luxuryCoupons.prints;

/**
 * Print class
 * is a util class for asthethic printing in the program
 */
public class print {
    /**
     * Print start
     * asthethic printing when the program starts working
     */
    public static void printStart(){
        for (int counter = 0; counter < 50; counter += 1){
            System.out.print("~!");
        }
        System.out.println();
    }

    /**
     * Print row
     * asthethic printing between each print
     */
    public static void printRow(){
        for (int counter = 0; counter < 50; counter += 1){
            System.out.print("__");
        }
        System.out.println();
    }
}
