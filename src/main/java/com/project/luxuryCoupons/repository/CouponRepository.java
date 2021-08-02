package com.project.luxuryCoupons.repository;

import com.project.luxuryCoupons.beans.Coupon;
import com.project.luxuryCoupons.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The Coupon repository class
 * exacute coupons CRUD
 */
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    /**
     * Find by company Id
     * this method returns a list of all the coupons of a company by it's id from DB
     *
     * @param companyId - the company's id
     * @return List of coupons
     */
    List<Coupon> findByCompanyId(int companyId);

    /**
     * Find by coupon Id
     * this method finds a coupon from DB by id
     *
     * @param couponId - the coupon's Id
     * @return Coupon instance
     */
    Coupon findByCouponId(int couponId);

    /**
     * Find by company id and title
     * this method finds a coupon from DB by company id and coupon title
     *
     * @param companyId - the company's id
     * @param title     - the coupon's title
     * @return Coupon instance
     */
    Coupon findByCompanyIdAndTitle(int companyId, String title);

    /**
     * Delete customer coupons
     * this method deletes from DB a coupon from all the customers it has realtions with
     *
     * @param couponId - the coupon's id
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `luxurycouponsspring`.`customer_coupons` WHERE coupons_coupon_id=:couponId", nativeQuery = true)
    void deleteCustomerCoupon(int couponId);

    /**
     * Delete company coupons
     * this method deletes from DB a coupon from the company it has relations with
     *
     * @param couponId - the coupon's id
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `luxurycouponsspring`.`company_coupons` WHERE `coupons_coupon_id`=:couponId", nativeQuery = true)
    void deleteCompanyCoupon(int couponId);

    /**
     * Find by category
     * this method find coupons by category from DB
     *
     * @param category - the category
     * @return List of coupons by specific category
     */
    List<Coupon> findByCategory(Category category);
}
