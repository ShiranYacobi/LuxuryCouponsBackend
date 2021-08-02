package com.project.luxuryCoupons.services;

import com.project.luxuryCoupons.exceptions.LuxuryCouponsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Client service
 * is an abstract class that indicates all types of clients
 * who can use the system and the login method for them to inherit
 */
@Component
public abstract class ClientService {
    /**
     * Admin service impl - field
     */
    @Autowired
    protected AdminServiceImpl adminService;
    /**
     * Company service impl - field
     */
    @Autowired
    @SuppressWarnings("UnusedDeclaration")
    protected CompanyServiceImpl companyService;
    /**
     * Customer service impl - field
     */
    @Autowired
    @SuppressWarnings("UnusedDeclaration")
    protected CustomerServiceImpl customerService;

    /**
     * Client login method
     *
     * @param email    - the email of the client for login
     * @param password - the password of the client for login
     * @return boolean - returns if the login was successful
     * @throws LuxuryCouponsException - throws an error if the client doesn't exist
     */
    public boolean login(String email, String password) throws LuxuryCouponsException {
        if (this instanceof AdminService) {
            AdminServiceImpl adminService = new AdminServiceImpl();
            return adminService.login(email, password);
        } else if (this instanceof CompanyService) {
            CompanyServiceImpl companyService = new CompanyServiceImpl();
            return companyService.login(email, password);
        } else if (this instanceof CustomerService) {
            CustomerServiceImpl customerService = new CustomerServiceImpl();
            return customerService.login(email, password);
        }
        return false;
    }
}
