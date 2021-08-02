package com.project.luxuryCoupons.services;

import com.project.luxuryCoupons.beans.Customer;
import com.project.luxuryCoupons.exceptions.LuxuryCouponsException;
import com.project.luxuryCoupons.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Guest service impl class
 * Is a facade that exacutes 'Guset service' and contains the logic and actions for this client type
 */
@Service
public class GuestServiceImpl implements GuestService {
    /**
     * Customer repository - field
     */
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Register
     * this method adds a customer to DB
     *
     * @param customer - the customer we want to add
     * @return customer object
     * @throws LuxuryCouponsException - if the customer already exist in DB
     */
    @Override
    public Customer register(Customer customer) throws LuxuryCouponsException {
        for (Customer item : customerRepository.findAll()) {
            if (customer.getEmail().equals(item.getEmail())) {
                throw new LuxuryCouponsException("Customer " + customer.getFirstName() + " " + customer.getLastName() + " already exists !");
            }
        }
        customerRepository.save(customer);
        System.out.println("Customer " + customer.getFirstName() + " " + customer.getLastName() + " added to the system");
        return customerRepository.findByEmailAndPassword(customer.getEmail(), customer.getPassword());
    }
}
