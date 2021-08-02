package com.project.luxuryCoupons.clr;

import com.project.luxuryCoupons.Login.LoginManager;
import com.project.luxuryCoupons.beans.Coupon;
import com.project.luxuryCoupons.enums.Category;
import com.project.luxuryCoupons.enums.ClientType;
import com.project.luxuryCoupons.services.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;

import static com.project.luxuryCoupons.prints.print.printRow;
import static com.project.luxuryCoupons.prints.print.printStart;

/**
 * Company test class
 * Contains the run method that exacutes - the Company methods
 */
@Component
@Order(3)
@SuppressWarnings("all")
public class mockDataCompany implements CommandLineRunner {
    /**
     * Company service impl - field
     */
    @Autowired
    private CompanyServiceImpl companyServiceImpl;
    /**
     * Login manager - field
     */
    @Autowired
    private LoginManager loginManager;

    /**
     * The run method
     * this method will check all the Companies methods
     *
     * @param args- contains the supplied command-line arguments as an array of String objects
     */
    @Override
    public void run(String... args) throws Exception {
        try {
            printStart();
            companyServiceImpl = (CompanyServiceImpl) loginManager.login("fashionista@gmail.com", "fashionista1234", ClientType.COMPANY);
            printStart();

            //add coupons
            Coupon coupon1 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(10).price(1_000).category(Category.FASHION).title("Evening dress").description("Brand new season 2021 evening dress").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/EVENING%20DRESS.jpeg?raw=true")).startDate(Date.valueOf("2021-07-21")).endDate(Date.valueOf("2022-08-20")).build();
            companyServiceImpl.addCoupon(coupon1);
            Coupon coupon2 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(20).price(5_000).category(Category.FASHION).title("Leather Bag").description("Leather handbag Fashionista special design").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/LEATHER%20BAG.jpeg?raw=true")).startDate(Date.valueOf("2021-07-22")).endDate(Date.valueOf("2022-09-30")).build();
            companyServiceImpl.addCoupon(coupon2);
            printRow();

            printStart();
            companyServiceImpl = (CompanyServiceImpl) loginManager.login("luver@gmail.com", "luverCosmetics", ClientType.COMPANY);
            printStart();

            //add coupons
            Coupon coupon6 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(100).price(500).category(Category.COSMETICS).title("Eyeshadow Palette").description("Brand new dark eyeshadow palette").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/EYESHADOW%20PALETTE.jpeg?raw=true")).startDate(Date.valueOf("2021-05-8")).endDate(Date.valueOf("2022-06-01")).build();
            companyServiceImpl.addCoupon(coupon6);
            Coupon coupon7 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(200).price(750).category(Category.COSMETICS).title("Red Luver lipstic").description("Red new lipstic from the house of Luver").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/LIPSTIC.jpeg?raw=true")).startDate(Date.valueOf("2021-05-01")).endDate(Date.valueOf("2022-09-20")).build();
            companyServiceImpl.addCoupon(coupon7);
            Coupon coupon8 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(50).price(1_000).category(Category.COSMETICS).title("Luver's make-up kit").description("Brand new make-up kit from the house of luver contains: 4 brushes, eyeshadow palette, highliter, 2 lipstics and glitter eyeshadow").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/MAKEUP%20KIT.jpeg?raw=true")).startDate(Date.valueOf("2021-01-12")).endDate(Date.valueOf("2022-06-25")).build();
            companyServiceImpl.addCoupon(coupon8);
            printRow();

            printStart();
            companyServiceImpl = (CompanyServiceImpl) loginManager.login("angel@spa.com", "angelSpa567", ClientType.COMPANY);
            printStart();

            // add coupons
            Coupon coupon9 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(100).price(800).category(Category.COSMETICS).title("Anti aging treatment").description("50 min of anti againg treatment at Angel spa").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/ANTI%20AGING%20TREATMENT.jpeg?raw=true")).startDate(Date.valueOf("2021-06-8")).endDate(Date.valueOf("2022-06-06")).build();
            companyServiceImpl.addCoupon(coupon9);
            Coupon coupon10 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(200).price(350).category(Category.COSMETICS).title("Body Cream").description("Organic body cream by Angel Spa").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/body%20cream.jpeg?raw=true")).startDate(Date.valueOf("2021-07-01")).endDate(Date.valueOf("2022-09-20")).build();
            companyServiceImpl.addCoupon(coupon10);
            printRow();

            printStart();
            companyServiceImpl = (CompanyServiceImpl) loginManager.login("ivelectric@hotmail.com", "electric123", ClientType.COMPANY);
            printStart();

            //add coupons
            Coupon coupon11 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(100).price(1_000).category(Category.ELECTRICITY).title("Coffee machine").description("Brand new design of IV Electric coffee machine").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/COFFEE%20MACHINE.jpeg?raw=true")).startDate(Date.valueOf("2021-06-8")).endDate(Date.valueOf("2022-06-06")).build();
            companyServiceImpl.addCoupon(coupon11);
            Coupon coupon12 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(200).price(3_000).category(Category.ELECTRICITY).title("Laptop").description("2021 new laptop by IV Electric").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/LAPTOP.jpeg?raw=true")).startDate(Date.valueOf("2021-07-01")).endDate(Date.valueOf("2022-09-20")).build();
            companyServiceImpl.addCoupon(coupon12);
            Coupon coupon13 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(300).price(1_500).category(Category.ELECTRICITY).title("Tablet").description("2021 new tablet by IV Electric").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/TABLET.jpeg?raw=true")).startDate(Date.valueOf("2021-07-01")).endDate(Date.valueOf("2022-09-20")).build();
            companyServiceImpl.addCoupon(coupon13);
            Coupon coupon14 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(210).price(2_500).category(Category.ELECTRICITY).title("Smart watch").description("2021 new smart watch by IV Electric").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/watch.jpg?raw=true")).startDate(Date.valueOf("2021-07-01")).endDate(Date.valueOf("2022-09-20")).build();
            companyServiceImpl.addCoupon(coupon14);
            printRow();

            printStart();
            companyServiceImpl = (CompanyServiceImpl) loginManager.login("eva@bridals.com", "evaBridal", ClientType.COMPANY);
            printStart();

            //add coupons
            Coupon coupon15 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(50).price(11_000).category(Category.FASHION).title("desigend bride dress").description("10% Off on all 2021 Eva Bridal collection").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/bridal%20dresses.jpeg?raw=true")).startDate(Date.valueOf("2021-07-8")).endDate(Date.valueOf("2022-05-06")).build();
            companyServiceImpl.addCoupon(coupon15);
            Coupon coupon16 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(100).price(20_000).category(Category.FASHION).title("25% on custom design").description("25% off on custom bridal dress designs").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/bride.jpeg?raw=true")).startDate(Date.valueOf("2021-07-01")).endDate(Date.valueOf("2022-09-20")).build();
            companyServiceImpl.addCoupon(coupon16);
            printRow();

            printStart();
            companyServiceImpl = (CompanyServiceImpl) loginManager.login("restauranta@gmail.com", "restaurantaFood", ClientType.COMPANY);
            printStart();

            // add coupons
            Coupon coupon18 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(500).price(1_200).category(Category.RESTAURANT).title("Dinner for two").description("Dinner for two at Restauranta").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/FOOD3.jpeg?raw=true")).startDate(Date.valueOf("2021-07-8")).endDate(Date.valueOf("2022-12-01")).build();
            companyServiceImpl.addCoupon(coupon18);
            Coupon coupon19 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(180).price(1_100).category(Category.RESTAURANT).title("Brunch for two").description("Special discount on bruch for two at Restaranta").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/BRUNCH.jpeg?raw=true")).startDate(Date.valueOf("2021-07-01")).endDate(Date.valueOf("2022-09-20")).build();
            companyServiceImpl.addCoupon(coupon19);
            printRow();

            printStart();
            companyServiceImpl = (CompanyServiceImpl) loginManager.login("bluzWinery@hotmail.com", "bluzWinery", ClientType.COMPANY);
            printStart();

            // add coupons
            Coupon coupon20 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(500).price(200).category(Category.RESTAURANT).title("Wine tasting").description("Price for one wine tasting includes cheese palette").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/WINE%20TASTING2.jpeg?raw=true")).startDate(Date.valueOf("2021-07-8")).endDate(Date.valueOf("2022-07-06")).build();
            companyServiceImpl.addCoupon(coupon20);
            printRow();

            printStart();
            companyServiceImpl = (CompanyServiceImpl) loginManager.login("flyAir@air.com", "flyAir1234", ClientType.COMPANY);
            printStart();

            //add coupons
            Coupon coupon21 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(500).price(3_000).category(Category.VACATION).title("Croatia - Flights & Hotel").description("Price for one passanger including direct flights with FlyAir and 5 starts hotel").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/CROATIA.jpeg?raw=true")).startDate(Date.valueOf("2021-07-1")).endDate(Date.valueOf("2022-01-01")).build();
            companyServiceImpl.addCoupon(coupon21);
            Coupon coupon22 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(180).price(1_000).category(Category.VACATION).title("Italy- Corpo flight").description("Price for one passanger, direct flights with FlyAir").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/ITALY.jpeg?raw=true")).startDate(Date.valueOf("2021-07-01")).endDate(Date.valueOf("2022-09-20")).build();
            companyServiceImpl.addCoupon(coupon22);
            Coupon coupon23 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(200).price(5_000).category(Category.VACATION).title("Exclusive ski deal").description("Exclusive ski deal in 4 location resorts").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/SKI.jpeg?raw=true")).startDate(Date.valueOf("2021-02-01")).endDate(Date.valueOf("2022-03-20")).build();
            companyServiceImpl.addCoupon(coupon23);
            Coupon coupon24 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(160).price(10_000).category(Category.VACATION).title("All-inclusive vacation in Maldives").description("All- inclusive vacation in Maldives at a 5 stars resort including flights with flyAir").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/VACATION.jpeg?raw=true")).startDate(Date.valueOf("2021-02-01")).endDate(Date.valueOf("2022-12-20")).build();
            companyServiceImpl.addCoupon(coupon24);
            printRow();

            printStart();
            companyServiceImpl = (CompanyServiceImpl) loginManager.login("shoesFactory@gmail.com", "shoesFactory", ClientType.COMPANY);
            printStart();

            // add coupons
            Coupon coupon25 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(250).price(1_000).category(Category.FASHION).title("Moccasin").description("Leather men Brown moccasin shoes 2021 season collection").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/MEN%20SHOES.jpeg?raw=true")).startDate(Date.valueOf("2021-07-1")).endDate(Date.valueOf("2022-01-01")).build();
            companyServiceImpl.addCoupon(coupon25);
            Coupon coupon26 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(180).price(1_999).category(Category.FASHION).title("Brown men shoes").description("Brown men leather shoes 2021 season collection").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/MEN%20SHOES2.jpeg?raw=true")).startDate(Date.valueOf("2021-07-01")).endDate(Date.valueOf("2022-09-20")).build();
            companyServiceImpl.addCoupon(coupon26);
            Coupon coupon3 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(10).price(2_500).category(Category.FASHION).title("Glitter heels").description("Brand new design, glitter silver heels").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/heels.jpeg?raw=true")).startDate(Date.valueOf("2021-07-20")).endDate(Date.valueOf("2022-10-30")).build();
            companyServiceImpl.addCoupon(coupon3);
            Coupon coupon4 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(30).price(2_200).category(Category.FASHION).title("Red heels").description("Brand new design, luxury red heels").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/women%20heels.jpeg?raw=true")).startDate(Date.valueOf("2021-07-20")).endDate(Date.valueOf("2022-10-10")).build();
            companyServiceImpl.addCoupon(coupon4);
            Coupon coupon5 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(50).price(1_200).category(Category.FASHION).title("Black basic heels").description("Black basic heels").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/women%20heels2.jpeg?raw=true")).startDate(Date.valueOf("2021-07-15")).endDate(Date.valueOf("2022-05-10")).build();
            companyServiceImpl.addCoupon(coupon5);
            printRow();

            printStart();
            companyServiceImpl = (CompanyServiceImpl) loginManager.login("gentelman@gmail.com", "Gentelmam", ClientType.COMPANY);
            printStart();

            //add coupons
            Coupon coupon27 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(1000).price(10_000).category(Category.FASHION).title("15% Off on 2020 suits collection").description("15% Off on all 2020 suits collection").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/MEN%20SUIT.jpeg?raw=true")).startDate(Date.valueOf("2021-07-1")).endDate(Date.valueOf("2022-01-01")).build();
            companyServiceImpl.addCoupon(coupon27);
            Coupon coupon28 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(1800).price(21_000).category(Category.FASHION).title("5% Off on 2021 suits collection").description("5% off on all 2021 suits collection").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/MEN%20SUITS.jpeg?raw=true")).startDate(Date.valueOf("2021-07-01")).endDate(Date.valueOf("2022-09-20")).build();
            companyServiceImpl.addCoupon(coupon28);
            Coupon coupon29 = Coupon.builder().companyId(companyServiceImpl.getCompanyId()).amount(10).price(2_500).category(Category.FASHION).title("Brown leather belt for men").description("Brown leather belt for men from 2021 collection").image(ImageByUrl("https://github.com/DadonStyle/LuxuryCouponsAssets/blob/main/belt.jpg?raw=true")).startDate(Date.valueOf("2021-07-20")).endDate(Date.valueOf("2022-10-30")).build();
            companyServiceImpl.addCoupon(coupon29);
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