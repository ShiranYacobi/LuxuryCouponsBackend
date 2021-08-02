package com.project.luxuryCoupons.prints;

/**
 * Service type class
 * is a util class for asthethic service type printing in the program
 */
public class ServiceType {
    /**
     * Print admin
     * this method will print whenever an admin will login to the program
     */
    public static void printAdmin() {
        System.out.println("" +
                "   db       8           w       w       w              w              \n" +
                "  dPYb   .d88 8d8b.d8b. w 8d8b. w d88b w8ww 8d8b .d88 w8ww .d8b. 8d8b \n" +
                " dPwwYb  8  8 8P Y8P Y8 8 8P Y8 8 `Yb.  8   8P   8  8  8   8' .8 8P   \n" +
                "dP    Yb `Y88 8   8   8 8 8   8 8 Y88P  Y8P 8    `Y88  Y8P `Y8P' 8    \n");
        System.out.println();
    }

    /**
     * Print company
     * this method will print whenever a company will login to the program
     */
    public static void printCompany() {
        System.out.println("" +
                ".d88b                                        \n" +
                "8P    .d8b. 8d8b.d8b. 88b. .d88 8d8b. Yb  dP \n" +
                "8b    8' .8 8P Y8P Y8 8  8 8  8 8P Y8  YbdP  \n" +
                "`Y88P `Y8P' 8   8   8 88P' `Y88 8   8   dP   \n" +
                "                      8                dP    ");
        System.out.println();
    }

    /**
     * Print customer
     * this method will print whenever a customer will login to the program
     */
    public static void printCustomer() {
        System.out.println("" +
                ".d88b             w                              \n" +
                "8P    8   8 d88b w8ww .d8b. 8d8b.d8b. .d88b 8d8b \n" +
                "8b    8b d8 `Yb.  8   8' .8 8P Y8P Y8 8.dP' 8P   \n" +
                "`Y88P `Y8P8 Y88P  Y8P `Y8P' 8   8   8 `Y88P 8    \n" +
                "                                                 ");
        System.out.println();
    }
}
