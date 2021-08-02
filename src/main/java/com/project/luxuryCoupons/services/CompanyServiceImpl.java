package com.project.luxuryCoupons.services;

import com.project.luxuryCoupons.beans.Company;
import com.project.luxuryCoupons.beans.Coupon;
import com.project.luxuryCoupons.enums.Category;
import com.project.luxuryCoupons.exceptions.LuxuryCouponsException;
import com.project.luxuryCoupons.repository.CompanyRepository;
import com.project.luxuryCoupons.repository.CouponRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Company service impl class
 * Is a facade that exacutes 'Comapny service' and contains the logic and actions for this client type
 */
@Service
@Data
@SuppressWarnings("all")
public class CompanyServiceImpl extends ClientService implements CompanyService {
    /**
     * Company repository - field
     */
    @Autowired
    @SuppressWarnings("UnusedDeclaration")
    private CompanyRepository companyRepository;
    /**
     * Coupon repository - field
     */
    @Autowired
    @SuppressWarnings("UnusedDeclaration")
    private CouponRepository couponRepository;
    /**
     * Company Id - field
     */
    private int companyId;

    /**
     * Login method
     * checks if allowed to get access and enables the comapny to login to the system and make actions
     *
     * @param email    - the company's email
     * @param password - the company's password
     * @return boolean - if the login was successful
     * @throws LuxuryCouponsException - if the values provided are incorrect
     */
    @Override
    public boolean login(String email, String password) throws LuxuryCouponsException {
        if (companyRepository.findByEmailAndPassword(email, password) == null) {
            throw new LuxuryCouponsException("Company not found !");
        }
        Company company = companyRepository.findByEmailAndPassword(email, password);
        this.companyId = company.getCompanyId();
        System.out.println("Logging was successful, welcome Company: " + company.getName());
        return true;
    }

    /**
     * Add coupon
     * this method adds coupon to the DB
     *
     * @param coupon -  the coupon we want to add
     * @return Coupon instance
     * @throws LuxuryCouponsException if the coupon already exist
     */
    @Override
    public Coupon addCoupon(Coupon coupon) throws LuxuryCouponsException {
        for (Coupon item : couponRepository.findAll()) {
            if (coupon.getTitle().equals(item.getTitle()) && coupon.getCompanyId() == companyId) {
                throw new LuxuryCouponsException("Title already taken !");
            }
        }
        coupon.setCompanyId(companyId);
        couponRepository.save(coupon);
        Company company = companyRepository.getOne(companyId);
        company.getCoupons().add(coupon);
        adminService.updateCompany(company);
        System.out.println(coupon.getTitle() + " added to the company " + coupon.getCompanyId());
        return coupon;
    }


    /**
     * Update coupon
     * this method updates the info of an exiting coupon in DB
     *
     * @param coupon - the coupon with the new info for updating
     * @return Coupon instance
     * @throws LuxuryCouponsException - if the coupon doesn't exist in DB
     */
    @Override
    public Coupon updateCoupon(Coupon coupon) throws LuxuryCouponsException {
        if (!(couponRepository.existsById(coupon.getCouponId()))) {
            throw new LuxuryCouponsException("Coupon not found !");
        } else {
            couponRepository.saveAndFlush(coupon);
        }
        return couponRepository.findByCouponId(coupon.getCouponId());
    }

    /**
     * Delete coupon
     * this method deletes a coupon from DB by id
     *
     * @param couponId - the coupon's id
     * @throws LuxuryCouponsException - if the company doesn't exist in DB
     */
    @Override
    public void deleteCoupon(int couponId) throws LuxuryCouponsException {
        if (!(couponRepository.existsById(couponId))) {
            throw new LuxuryCouponsException("Coupon id doesn't exist !");
        }
        couponRepository.deleteCompanyCoupon(couponId);
        couponRepository.deleteCustomerCoupon(couponId);
        couponRepository.deleteById(couponId);
        System.out.println("Coupon " + couponId + " deleted successfully!");
    }

    /**
     * Find by coupon id
     * this method finds a coupon by Id
     *
     * @param couponId - the coupon's id
     * @return Coupon instance
     * @throws LuxuryCouponsException - if the coupon doesn't exist in DB
     */
    @Override
    public Coupon findByCouponId(int couponId) throws LuxuryCouponsException {
        if (!(couponRepository.existsById(couponId))) {
            throw new LuxuryCouponsException("Coupon id doesn't exist !");
        }
        return couponRepository.findByCouponId(couponId);
    }

    /**
     * Get company coupons
     * this method gets all the coupons of a specific company
     *
     * @return List of the company's coupons
     */
    @Override
    public List<Coupon> getCompanyCoupons() {
        return couponRepository.findByCompanyId(companyId);
    }

    /**
     * Get company coupons
     * this method gets all the coupons of a specific company by specific category
     *
     * @return List of the company's coupons in this category
     */
    @Override
    public List<Coupon> getCompanyCoupons(Category category) {
        List<Coupon> coupons = getCompanyCoupons();
        coupons.removeIf(coupon -> !(coupon.getCategory().equals(category)));
        return coupons;
    }

    /**
     * Get company coupons
     * this method gets all the coupons of a specific company under a max price
     *
     * @return List of the company's coupons that are under the max price
     */
    @Override
    public List<Coupon> getCompanyCoupons(double maxPrice) {
        List<Coupon> coupons = getCompanyCoupons();
        coupons.removeIf(coupon -> coupon.getPrice() > maxPrice);
        return coupons;
    }

    /**
     * Get company details
     *
     * @return the company's info
     */
    @Override
    public Company getCompanyDetails() {
        return companyRepository.findByCompanyId(companyId);
    }
}
