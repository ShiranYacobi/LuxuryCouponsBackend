package com.project.luxuryCoupons.controller;

import com.project.luxuryCoupons.beans.Customer;
import com.project.luxuryCoupons.enums.Category;
import com.project.luxuryCoupons.exceptions.LuxuryCouponsException;
import com.project.luxuryCoupons.repository.CouponRepository;
import com.project.luxuryCoupons.services.GuestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Guest controller class
 * Allows to send and get data using the guest and coupon repository service with REST API
 */
@RestController
@RequestMapping("guest")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@SuppressWarnings("all")
public class GuestController {

    /**
     * Guest service - filed
     */
    @Autowired
    private GuestServiceImpl guestService;

    /**
     * Coupon repository - filed
     */
    @Autowired
    private CouponRepository couponRepository;

    /**
     * Add customer
     * this method adds a customer to DB
     *
     * @param customer - the customer we want to add
     * @return response status
     */
    //http://localHost:8080/guest/register
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody Customer customer) {
        try {
            return new ResponseEntity<>(guestService.register(customer), HttpStatus.CREATED);
        } catch (LuxuryCouponsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get all category coupons
     * this method find coupons by category
     *
     * @param category - the category name
     * @return - list of coupons by the same category type
     */
    //http://localhost:8080/guest/category/{category}
    @GetMapping("/category/{category}")
    public ResponseEntity<?> getAllCategoryCoupons(@PathVariable Category category) {
        return ResponseEntity.ok(couponRepository.findByCategory(category));
    }

    /**
     * Get all coupons
     * this method gets all coupons to presente in the website store
     *
     * @return all the coupons
     */
    @GetMapping("allCoupons")
    public ResponseEntity<?> getAllCoupons() {
        return new ResponseEntity<>(couponRepository.findAll(), HttpStatus.OK);
    }
}
