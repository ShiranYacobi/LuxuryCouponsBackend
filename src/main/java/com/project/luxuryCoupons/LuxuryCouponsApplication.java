package com.project.luxuryCoupons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Luxury coupon application class
 * this is a 'spring application' class used to bootstrap and launch a Spring application from a Java main method,
 * and creates application context.
 */
@SpringBootApplication
@EnableScheduling
public class LuxuryCouponsApplication {
	/**
	 * The main method exacutes the "run" method in "init data" class
	 * @param args contains the supplied command-line arguments as an array of String objects.
	 */
	public static void main(String[] args) {
		SpringApplication.run(LuxuryCouponsApplication.class, args);
	}
}
