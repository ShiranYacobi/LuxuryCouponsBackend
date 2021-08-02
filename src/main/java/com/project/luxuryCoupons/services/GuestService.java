package com.project.luxuryCoupons.services;

import com.project.luxuryCoupons.beans.Customer;
import com.project.luxuryCoupons.exceptions.LuxuryCouponsException;

/**
 * The Guest service interface
 * indicates which methods we will implement in the guest service impl class
 */
public interface GuestService {

    /**
     * Register
     * this method allows a geust to register to the client's system, and adds a customer to DB
     *
     * @param customer - the customer we want to add
     * @return Customer instance
     * @throws LuxuryCouponsException if the customer already exist in DB
     */
    Customer register(Customer customer) throws LuxuryCouponsException;
}
