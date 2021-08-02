package com.project.luxuryCoupons.services;

import com.project.luxuryCoupons.beans.Company;
import com.project.luxuryCoupons.beans.Coupon;
import com.project.luxuryCoupons.enums.Category;
import com.project.luxuryCoupons.exceptions.LuxuryCouponsException;

import java.util.List;

/**
 * The Company service interface
 * indicates which methods we will implement in the 'Comapny service impl' class
 */
@SuppressWarnings("all")
public interface CompanyService {
    /**
     * Login method
     * checks if allowed to get access and enables the comapny to login to the system and make actions
     *
     * @param email    - the company's email
     * @param password - the company's password
     * @return boolean - if the login was successful
     * @throws LuxuryCouponsException if the values provided are incorrect
     */
    boolean login(String email, String password) throws LuxuryCouponsException;

    /**
     * Add coupon
     * this method adds coupon to the DB
     *
     * @param coupon -  the coupon we want to add
     * @return Coupon instance
     * @throws LuxuryCouponsException if the coupon already exist
     */
    Coupon addCoupon(Coupon coupon) throws LuxuryCouponsException;


    /**
     * Update coupon
     * this method updates the info of an exiting coupon in DB
     *
     * @param coupon - the coupon with the new info for updating
     * @return Coupon instance
     * @throws LuxuryCouponsException if the coupon doesn't exist in DB
     */
    Coupon updateCoupon(Coupon coupon) throws LuxuryCouponsException;

    /**
     * Delete coupon
     * this method deletes a coupon from DB by id
     *
     * @param couponId - the coupon's id
     * @throws LuxuryCouponsException if the coupon doesn't exist in DB
     */
    void deleteCoupon(int couponId) throws LuxuryCouponsException;

    /**
     * Find by coupon id
     * this method finds a coupon by Id
     *
     * @param couponId - the coupon's id
     * @return Coupon instance
     * @throws LuxuryCouponsException if the coupon doesn't exist in DB
     */
    Coupon findByCouponId(int couponId) throws LuxuryCouponsException;

    /**
     * Get company coupons
     * this method gets all the coupons of a specific company
     *
     * @return List of the company's coupons
     */
    List<Coupon> getCompanyCoupons();

    /**
     * Get company coupons
     * this method gets all the coupons of a specific company by specific category
     *
     * @param category - the category type
     * @return List of the company's coupons in this category
     */
    List<Coupon> getCompanyCoupons(Category category);

    /**
     * Get company coupons
     * this method gets all the coupons of a specific company under a max price
     *
     * @param maxPrice - the maxPrice value
     * @return List of the company's coupons that are under the max price
     */
    List<Coupon> getCompanyCoupons(double maxPrice);

    /**
     * Get company details
     *
     * @return the company's info
     */
    Company getCompanyDetails();
}
