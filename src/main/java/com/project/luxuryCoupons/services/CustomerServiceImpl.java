package com.project.luxuryCoupons.services;

import com.project.luxuryCoupons.beans.Coupon;
import com.project.luxuryCoupons.beans.Customer;
import com.project.luxuryCoupons.enums.Category;
import com.project.luxuryCoupons.exceptions.LuxuryCouponsException;
import com.project.luxuryCoupons.repository.CouponRepository;
import com.project.luxuryCoupons.repository.CustomerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * The Customer service impl class
 * Is a facade that exacutes 'Customer service' and contains the logic and actions for this client type
 */
@Service
@Data
@SuppressWarnings("all")
public class CustomerServiceImpl extends ClientService implements CustomerService {
    /**
     * Customer repository - field
     */
    @Autowired
    @SuppressWarnings("UnusedDeclaration")
    private CustomerRepository customerRepository;
    /**
     * Coupon repository - field
     */
    @Autowired
    @SuppressWarnings("UnusedDeclaration")
    private CouponRepository couponRepository;
    /**
     * Customer Id - field
     */
    private int customerId;

    /**
     * Login method
     * checks if allowed to get access and enables the customer to login to the system and make actions
     *
     * @param email    - the customer's email
     * @param password - the customer's password
     * @return boolean - if the login was successful
     * @throws LuxuryCouponsException - if the values provided are incorrect
     */
    @Override
    public boolean login(String email, String password) throws LuxuryCouponsException {
        if (customerRepository.findByEmailAndPassword(email, password) == null) {
            throw new LuxuryCouponsException("Incorrect email or password !");
        }
        Customer customer = customerRepository.findByEmailAndPassword(email, password);
        this.customerId = customer.getCustomerId();
        System.out.println("Logging was successful, welcome Customer: " + customer.getFirstName() + " " + customer.getLastName());
        return true;
    }

    /**
     * Update customer
     * this method will update the info of an existing customer in DB
     *
     * @param customer - the customer with the new info for updating
     * @throws LuxuryCouponsException - if the customer doesn't exist in DB
     */
    @Override
    public void updateCustomer(Customer customer) throws LuxuryCouponsException {
        if (!(customerRepository.existsById(customerId))) {
            throw new LuxuryCouponsException("Customer not found !");
        } else {
            customer.setCustomerId(customerId);
            customerRepository.saveAndFlush(customer);
        }
    }

    /**
     * Add coupon purchase
     * this method will chcek if the purchasing matches all the conditions, and makes the purchase if possible
     *
     * @param coupon - the coupon
     * @throws LuxuryCouponsException - throws an error based on what condition wasn't right
     */
    @Override
    public void addCouponPurchase(Coupon coupon) throws LuxuryCouponsException {
        Coupon myCoupon = couponRepository.findByCompanyIdAndTitle(coupon.getCompanyId(), coupon.getTitle());
        if (!(couponRepository.existsById(myCoupon.getCouponId()))) {
            throw new LuxuryCouponsException("Coupon doesn't exist !");
        }
        Customer customer = customerRepository.getOne(customerId);
        List<Coupon> coupons = customerRepository.findByCustomerId(customerId).getCoupons();
        Date currentDate = new java.util.Date(new java.util.Date().getTime());
        //checks if the coupon expired
        if (currentDate.after(myCoupon.getEndDate())) {
            throw new LuxuryCouponsException("Coupon purchase Unsuccessful !\n Coupon expired ");
        }
        //Checks if the coupon is available..
        if (myCoupon.getAmount() < 1) {
            throw new LuxuryCouponsException("Coupon purchase Unsuccessful !\n Coupon not available ");
        }
        if (coupons.size() > 0) {
            for (Coupon item : coupons) {
                //Checks if the customer already purchased this coupon
                if ((myCoupon.getCouponId() == item.getCouponId())) {
                    throw new LuxuryCouponsException("Coupon purchase Unsuccessful !\n Coupon already been purchased");
                }
            }
        }
        //updates the amount after purchase
        myCoupon.setAmount((myCoupon.getAmount() - 1));
        couponRepository.saveAndFlush(myCoupon);
        coupons.add(myCoupon);
        customer.setCoupons(coupons);
        customerRepository.saveAndFlush(customer);
        System.out.println("Coupon " + myCoupon.getTitle() + " purchased successfully !");
    }

    /**
     * Get customer coupons
     * this method gets all the coupons of a specific customer
     *
     * @return List of the customer's coupons
     */
    @Override
    public List<Coupon> getCustomerCoupons() {
        return customerRepository.getOne(customerId).getCoupons();
    }

    /**
     * Get customer coupons
     * this method gets all the coupons of a specific customer by specific category
     *
     * @return List of the customer's coupons in this category
     */
    @Override
    public List<Coupon> getCustomerCoupons(Category category) {
        List<Coupon> coupons = getCustomerCoupons();
        coupons.removeIf(coupon -> !(coupon.getCategory().equals(category)));
        return coupons;
    }

    /**
     * Get customer coupons
     * this method gets all the coupons of a specific customer under a max price
     *
     * @return List of the customer's coupons that are under the max price
     */
    @Override
    public List<Coupon> getCustomerCoupons(double maxPrice) {
        List<Coupon> coupons = getCustomerCoupons();
        coupons.removeIf(coupon -> coupon.getPrice() > maxPrice);
        return coupons;
    }

    /**
     * Get customer details
     *
     * @return the customer's info
     */
    @Override
    public Customer getCustomerDetails() {
        return customerRepository.findByCustomerId(this.customerId);
    }
}
