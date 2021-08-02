package com.project.luxuryCoupons.clr;

import com.project.luxuryCoupons.Login.LoginManager;
import com.project.luxuryCoupons.beans.Coupon;
import com.project.luxuryCoupons.beans.Customer;
import com.project.luxuryCoupons.enums.Category;
import com.project.luxuryCoupons.enums.ClientType;
import com.project.luxuryCoupons.services.CustomerServiceImpl;
import com.project.luxuryCoupons.services.GuestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;

import static com.project.luxuryCoupons.prints.print.printRow;

/**
 * Customer test class
 * Contains the run method that exacutes - the Customer methods
 */
@Component
@Order(4)
@SuppressWarnings("all")
public class mockDataCustomer implements CommandLineRunner {
    /**
     * Customer service impl - field
     */
    @Autowired
    private CustomerServiceImpl customerServiceImpl;
    /**
     * Login manager - field
     */
    @Autowired
    private LoginManager loginManager;

    /**
     * Guest service impl - field
     */
    @Autowired
    private GuestServiceImpl guestServiceImpl;

    /**
     * The run method
     * this method will check all the Customer's methods
     *
     * @param args- contains the supplied command-line arguments as an array of String objects
     */
    @Override
    public void run(String... args) throws Exception {
        try {
            Customer customer1 = Customer.builder().firstName("Daniel").lastName("Peretz").email("daniel@gmail.com").password("daniel1234").build();
            Customer customer2 = Customer.builder().firstName("Noam").lastName("Dadon").email("noam@gmail.com").password("noam999").build();
            Customer customer3 = Customer.builder().firstName("Shiran").lastName("Yacobi").email("shiran@gmail.com").password("shiran555").build();
            guestServiceImpl.register(customer1);
            guestServiceImpl.register(customer2);
            guestServiceImpl.register(customer3);
            printRow();

            customerServiceImpl = (CustomerServiceImpl) loginManager.login("noam@gmail.com", "noam999", ClientType.CUSTOMER);
            printRow();

            Coupon coupon20 = Coupon.builder().companyId(7).amount(500).price(200).category(Category.RESTAURANT).title("Wine tasting").description("Price for one wine tasting includes cheese palette").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/WINE%20TASTING2.jpeg?raw=true")).startDate(Date.valueOf("2021-07-8")).endDate(Date.valueOf("2022-07-06")).build();
            Coupon coupon15 = Coupon.builder().companyId(5).amount(50).price(11_000).category(Category.FASHION).title("desigend bride dress").description("10% Off on all 2021 Eva Bridal collection").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/bridal%20dresses.jpeg?raw=true")).startDate(Date.valueOf("2021-07-8")).endDate(Date.valueOf("2022-05-06")).build();
            customerServiceImpl.addCouponPurchase(coupon20);
            customerServiceImpl.addCouponPurchase(coupon15);
            printRow();
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }

    /**
     * Gets images from assets folder in github to our test coupons
     *
     * @param url - Github url
     * @return byte[] - the image
     * @throws IOException throws an error if the i/o were interupted or failed
     */
    public byte[] ImageByUrl(String url) throws IOException {
        InputStream in = new URL(url).openStream();
        byte[] myFile = in.readAllBytes();
        in.close();
        return myFile;
    }
}