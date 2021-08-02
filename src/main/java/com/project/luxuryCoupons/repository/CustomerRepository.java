package com.project.luxuryCoupons.repository;

import com.project.luxuryCoupons.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Customer repository class
 * exacute customers CRUD
 */
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    /**
     * Find by email and password
     * this method finds a customer from DB by email and password
     * @param email - the customer's email
     * @param password - the customer's password
     * @return Customer instance
     */
    Customer findByEmailAndPassword(String email, String password);

    /**
     * Find by email
     * this method finds a customer from DB by email
     * @param email - the customer's email
     * @return Customer instance
     */
    Customer findByEmail(String email);

    /**
     * Find by customer Id
     * this method finds a customer from DB by id
     * @param customerId- the customer's id
     * @return Customer instance
     */
    Customer findByCustomerId(int customerId);
}
