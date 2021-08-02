package com.project.luxuryCoupons.clr;

import com.project.luxuryCoupons.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import static com.project.luxuryCoupons.prints.print.printRow;


/**
 * Init data class
 * Contains the run method that exacutes - add all categories method
 */
@Component
@Order(1)
@SuppressWarnings("all")
public class InitData implements CommandLineRunner {
    /**
     * Category service field
     */
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    /**
     * The run method
     * this method will check all the system's methods
     * @param args- contains the supplied command-line arguments as an array of String objects
     */
    @Override
    public void run(String... args) throws Exception {
        printRow();
        categoryServiceImpl.addAllCategories();
        System.out.print("All the categories added to the data base\n");
        printRow();
    }
}
