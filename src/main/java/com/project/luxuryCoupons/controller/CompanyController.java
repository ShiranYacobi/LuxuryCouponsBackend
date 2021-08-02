package com.project.luxuryCoupons.controller;

import com.project.luxuryCoupons.beans.Coupon;
import com.project.luxuryCoupons.beans.UserDetails;
import com.project.luxuryCoupons.enums.Category;
import com.project.luxuryCoupons.enums.ClientType;
import com.project.luxuryCoupons.exceptions.LuxuryCouponsException;
import com.project.luxuryCoupons.exceptions.TokenExpiredException;
import com.project.luxuryCoupons.services.CompanyServiceImpl;
import com.project.luxuryCoupons.utils.JWTutil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * Comapny controller class
 * Allows to send and get data using the company service with REST API
 */
//http://localHost:8080/COMPANY
@RestController
@RequestMapping("COMPANY")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@SuppressWarnings("all")
public class CompanyController extends ClientController {
    /**
     * Company service field
     */
    @Autowired
    private CompanyServiceImpl companyService;
    /**
     * JWTutil field
     */
    private final JWTutil JWTutil;

    /**
     * Login method
     * checks if allowed to get access and enables the comapny to login to the system and make actions
     *
     * @param userDetails    - the company email, cleint type, id
     * @return ResponseEntity - http response if the login was successful
     */
    //http://localHost:8080/COMPANY/login
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDetails userDetails) {
        try {
            if (companyService.login(userDetails.getEmail(), userDetails.getPassword())) {
                String myToken = JWTutil.generateToken(new UserDetails(userDetails.getEmail(), ClientType.COMPANY, companyService.getCompanyId()));
                return new ResponseEntity<>(myToken, HttpStatus.ACCEPTED);
            }
        } catch (LuxuryCouponsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    /**
     * Add coupon
     * this method adds coupon to the DB
     * @param coupon-  the coupon we want to add
     * @return response status
     */
    //http://localhost:8080/COMPANY/addCoupon?amount=&category=&description=&endDate=&price=&startDate=&title="
    @PostMapping("addCoupon")
    public ResponseEntity<?> addCoupon(@RequestHeader (name="Authorization") String token, @RequestParam int amount, @RequestParam double price, @RequestParam Category category, @RequestParam String title, @RequestParam String description, @RequestParam MultipartFile image, @RequestParam Date startDate, @RequestParam Date endDate) throws IOException, TokenExpiredException {
        if(JWTutil.validateToken(token)){
            Coupon coupon = new Coupon(companyService.getCompanyId(), amount,price, category, title, description, image.getBytes(),startDate,endDate);
            try {
                return ResponseEntity.ok()
                        .header("Authorization",JWTutil.generateToken(new UserDetails(
                                JWTutil.extractEmail(token),
                                ClientType.COMPANY, companyService.getCompanyId()))).body(companyService.addCoupon(coupon));
            } catch (LuxuryCouponsException e) {
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Update coupon
     * this method updates the info of an exiting coupon in DB
     * @param coupon- the coupon with the new info for updating
     * @return response status
     */
    //http://localhost:8080/COMPANY/updateCoupon
    @PutMapping("updateCoupon")
    public ResponseEntity<?> updateCoupon(@RequestHeader (name="Authorization") String token, @RequestBody Coupon coupon) throws TokenExpiredException {
        if (JWTutil.validateToken(token)) {
            try {
                companyService.updateCoupon(coupon);
            } catch (LuxuryCouponsException e) {
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok()
                    .header("Authorization", JWTutil.generateToken(new UserDetails(
                            JWTutil.extractEmail(token),
                            ClientType.COMPANY, companyService.getCompanyId())))
                    .body(null);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Delete coupon
     * this method deletes a coupon from DB by id
     * @param couponId - the coupon's id
     * @return response status
     */
    //http://localhost:8080/COMPANY/deleteCoupon/couponId
    @DeleteMapping("deleteCoupon/{couponId}")
    public ResponseEntity<?> deleteCoupon(@RequestHeader (name="Authorization") String token,@PathVariable int couponId) throws TokenExpiredException {
        if (JWTutil.validateToken(token)) {
            try {
                companyService.deleteCoupon(couponId);
            } catch (LuxuryCouponsException e) {
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok().header("Authorization", JWTutil.generateToken(new UserDetails(
                    JWTutil.extractEmail(token),
                    ClientType.COMPANY, companyService.getCompanyId()))).body(null);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Get company coupons
     * this method gets all the coupons of a specific company
     * @return List of the company's coupons and response status
     */
    //http://localHost:8080/COMPANY/getCompanyCoupons
    @GetMapping("getCompanyCoupons")
    public ResponseEntity<?> getCompanyCoupons(@RequestHeader (name="Authorization") String token) throws TokenExpiredException {
        if (JWTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", JWTutil.generateToken(new UserDetails(
                            JWTutil.extractEmail(token),
                            ClientType.COMPANY, companyService.getCompanyId()
                    )))
                    .body(companyService.getCompanyCoupons());
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Get company coupons
     * this method gets all the coupons of a specific company by specific category
     * @param category - the category
     * @return List of the company's coupons in this category and response status
     */
    //http://localHost:8080/COMPANY/getCoupons/getCompanyCouponsByCategory/category
    @GetMapping("getCompanyCouponsByCategory/{category}")
    public ResponseEntity<?> getCompanyCoupons(@RequestHeader (name="Authorization") String token,@PathVariable Category category) throws TokenExpiredException {
        if (JWTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", JWTutil.generateToken(new UserDetails(
                            JWTutil.extractEmail(token),
                            ClientType.COMPANY, companyService.getCompanyId()
                    )))
                    .body(companyService.getCompanyCoupons(category));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Get company coupons
     * this method gets all the coupons of a specific company under a max price
     * @param maxPrice -the maximum price
     * @return List of the company's coupons that are under the max price and response status
     */
    //http://localHost:8080/COMPANY/getCompanyCouponsByPrice/maxPrice
    @GetMapping("getCompanyCouponsByPrice/{maxPrice}")
    public ResponseEntity<?> getCompanyCoupons(@RequestHeader (name="Authorization") String token,@PathVariable double maxPrice) throws TokenExpiredException {
        if (JWTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", JWTutil.generateToken(new UserDetails(
                            JWTutil.extractEmail(token),
                            ClientType.COMPANY, companyService.getCompanyId()
                    )))
                    .body(companyService.getCompanyCoupons(maxPrice));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Company details
     * @return the company's info and response status
     */
    //http://localHost:8080/COMPANY/companyDetails
    @GetMapping("companyDetails")
    public ResponseEntity<?> getCompanyDetails(@RequestHeader (name="Authorization") String token) throws TokenExpiredException {
        if (JWTutil.validateToken(token)) {
            return ResponseEntity.ok()
                    .header("Authorization", JWTutil.generateToken(new UserDetails(
                            JWTutil.extractEmail(token),
                            ClientType.COMPANY, companyService.getCompanyId()
                    )))
                    .body(companyService.getCompanyDetails());
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
