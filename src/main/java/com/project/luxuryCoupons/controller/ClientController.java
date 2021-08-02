package com.project.luxuryCoupons.controller;

import com.project.luxuryCoupons.beans.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Client controller class
 * is an abstract class thats allows to send and get data using the client service with REST API
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public abstract class ClientController {
    /**
     * Admin controller - field
     */
    @Autowired
    @SuppressWarnings("UnusedDeclaration")
    private AdminController adminController;
    /**
     * Company controller - field
     */
    @Autowired
    @SuppressWarnings("UnusedDeclaration")
    private CompanyController companyController;
    /**
     * Customer controller - field
     */
    @Autowired
    @SuppressWarnings("UnusedDeclaration")
    private CustomerController customerController;

    /**
     * Client login method
     *
     * @param userDetails- the email, client type and the id - of the client for login
     * @return ResponseEntity - returns http response if the login was successful
     */
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDetails userDetails) {
        if (this instanceof AdminController) {
            adminController.login(userDetails);
        } else if (this instanceof CompanyController) {
            companyController.login(userDetails);
        } else if (this instanceof CustomerController) {
            customerController.login(userDetails);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
