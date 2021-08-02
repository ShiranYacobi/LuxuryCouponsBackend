package com.project.luxuryCoupons.controller;

import com.project.luxuryCoupons.beans.Coupon;
import com.project.luxuryCoupons.beans.Customer;
import com.project.luxuryCoupons.beans.UserDetails;
import com.project.luxuryCoupons.enums.Category;
import com.project.luxuryCoupons.enums.ClientType;
import com.project.luxuryCoupons.exceptions.LuxuryCouponsException;
import com.project.luxuryCoupons.exceptions.TokenExpiredException;
import com.project.luxuryCoupons.services.CustomerServiceImpl;
import com.project.luxuryCoupons.utils.JWTutil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Customer controller class
 * Allows to send and get data using the customer service with REST API
 */
//http://localHost:8080/CUSTOMER
@RestController
@RequestMapping("CUSTOMER")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@SuppressWarnings("all")
public class CustomerController extends ClientController {
    /**
     * Customer service - field
     */
    @Autowired
    private CustomerServiceImpl customerService;
    /**
     * JWTutil - field
     */
    private final JWTutil JWTutil;

    /**
     * Login method
     * checks if allowed to get access and enables the customer to login to the system and make actions
     *
     * @param userDetails - the customer email, cleint type, id
     * @return boolean - if the login was successful
     */
    //http://localHost:8080/CUSTOMER/login
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDetails userDetails) {
        try {
            if (customerService.login(userDetails.getEmail(), userDetails.getPassword())) {
                String myToken = JWTutil.generateToken(new UserDetails(userDetails.getEmail(), ClientType.CUSTOMER, customerService.getCustomerId()));
                return new ResponseEntity<>(myToken, HttpStatus.ACCEPTED);
            }
        } catch (LuxuryCouponsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    /**
     * Update customer
     * this method will update the info of an existing customer in DB
     *
     * @param customer - the customer with the new info for updating
     * @return response status
     */
    //http://localhost:8080/CUSTOMER/updateCustomer
    @PutMapping("updateCustomer")
    public ResponseEntity<?> updateCustomer(@RequestHeader(name = "Authorization") String token, @RequestBody Customer customer) throws TokenExpiredException {
        if (JWTutil.validateToken(token)) {
            try {
                customerService.updateCustomer(customer);
            } catch (LuxuryCouponsException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok()
                    .header("Authorization", JWTutil.generateToken(new UserDetails(
                            JWTutil.extractEmail(token),
                            ClientType.CUSTOMER, customerService.getCustomerId())))
                    .body(null);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Add coupon purchase
     * this method will chcek if the purchasing matches all the conditions, and makes the purchase if possible
     *
     * @param coupon - the coupons we want to purchase
     * @return response status
     */
    //http://localHost:8080/CUSTOMER/addPurchaseCoupon
    @PostMapping("addPurchaseCoupon")
    public ResponseEntity<?> purchaseCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws TokenExpiredException {
        if (JWTutil.validateToken(token)) {
            try {
                customerService.addCouponPurchase(coupon);
            } catch (LuxuryCouponsException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok()
                    .header("Authorization", JWTutil.generateToken(new UserDetails(
                            JWTutil.extractEmail(token),
                            ClientType.CUSTOMER, customerService.getCustomerId())))
                    .body(null);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Get customer coupons
     * this method gets all the coupons of a specific customer
     *
     * @return List of the customer's coupons and response status
     */
    //http://localHost:8080/CUSTOMER/getCustomerCoupons
    @GetMapping("getCustomerCoupons")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader(name = "Authorization") String token) throws TokenExpiredException {
        if (JWTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", JWTutil.generateToken(new UserDetails(
                            JWTutil.extractEmail(token),
                            ClientType.CUSTOMER, customerService.getCustomerId())))
                    .body(customerService.getCustomerCoupons());
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Get customer coupons
     * this method gets all the coupons of a specific customer by specific category
     *
     * @param category -the category
     * @return List of the customer's coupons in this category and response status
     */
    //http://localHost:8080/CUSTOMER/getCustomerCouponsByCategory/category
    @GetMapping("getCustomerCouponsByCategory/{category}")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader(name = "Authorization") String token, @PathVariable Category category) throws TokenExpiredException {
        if (JWTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", JWTutil.generateToken(new UserDetails(
                            JWTutil.extractEmail(token),
                            ClientType.CUSTOMER, customerService.getCustomerId())))
                    .body(customerService.getCustomerCoupons(category));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Get customer coupons
     * this method gets all the coupons of a specific customer under a max price
     *
     * @param maxPrice -the maximum price
     * @return List of the customer's coupons that are under the max price and response status
     */
    //http://localHost:8080/CUSTOMER/getCustomerCouponsByPrice/maxPrice
    @GetMapping("getCustomerCouponsByPrice/{maxPrice}")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader(name = "Authorization") String token, @PathVariable double maxPrice) throws TokenExpiredException {
        if (JWTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", JWTutil.generateToken(new UserDetails(
                            JWTutil.extractEmail(token),
                            ClientType.CUSTOMER, customerService.getCustomerId())))
                    .body(customerService.getCustomerCoupons(maxPrice));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Get customer details
     *
     * @return the customer's info and response status
     */
    //http://localHost:8080/CUSTOMER/getDetails
    @GetMapping("getDetails")
    public ResponseEntity<?> getCustomerDetails(@RequestHeader(name = "Authorization") String token) throws TokenExpiredException {
        if (JWTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", JWTutil.generateToken(new UserDetails(
                            JWTutil.extractEmail(token),
                            ClientType.CUSTOMER, customerService.getCustomerId()
                    )))
                    .body(customerService.getCustomerDetails());
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
