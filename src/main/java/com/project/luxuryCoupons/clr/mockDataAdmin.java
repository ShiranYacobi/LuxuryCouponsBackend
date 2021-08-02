package com.project.luxuryCoupons.clr;

import com.project.luxuryCoupons.Login.LoginManager;
import com.project.luxuryCoupons.beans.Company;
import com.project.luxuryCoupons.enums.ClientType;
import com.project.luxuryCoupons.services.AdminServiceImpl;
import com.project.luxuryCoupons.services.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static com.project.luxuryCoupons.prints.print.printRow;
import static com.project.luxuryCoupons.prints.print.printStart;

/**
 * Admin test class
 * Contains the run method that exacutes - the Administrator methods
 */
@Component
@Order(2)
@SuppressWarnings("all")
public class mockDataAdmin implements CommandLineRunner {
    /**
     * admin service impl - field
     */
    @Autowired
    private AdminServiceImpl adminServiceImpl;
    /**
     * Category service - field
     */
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    /**
     * Login manager - field
     */
    @Autowired
    private LoginManager loginManager;

    /**
     * The run method
     * this method will check all the Admin's methods
     *
     * @param args- contains the supplied command-line arguments as an array of String objects
     */
    @Override
    public void run(String... args) throws Exception {
        try {
            printStart();
            adminServiceImpl = (AdminServiceImpl) loginManager.login("admin@admin.com", "admin", ClientType.ADMIN);
            printStart();

            //Create companies
            Company fashionista = Company.builder().name("Fashionista").email("fashionista@gmail.com").password("fashionista1234").build();
            Company luver = Company.builder().name("Luver Cosmetics").email("luver@gmail.com").password("luverCosmetics").build();
            Company angelSpa = Company.builder().name("Angel Spa").email("angel@spa.com").password("angelSpa567").build();
            Company ivElectric = Company.builder().name("IV Electric").email("ivelectric@hotmail.com").password("electric123").build();
            Company evaBridal = Company.builder().name("Eva Bridal").email("eva@bridals.com").password("evaBridal").build();
            Company restauranta = Company.builder().name("Restauranta").email("restauranta@gmail.com").password("restaurantaFood").build();
            Company bluzWinery = Company.builder().name("Bluz Winery").email("bluzWinery@hotmail.com").password("bluzWinery").build();
            Company flyAir = Company.builder().name("FlyAir").email("flyAir@air.com").password("flyAir1234").build();
            Company shoesFactory = Company.builder().name("Shoes Factory").email("shoesFactory@gmail.com").password("shoesFactory").build();
            Company gentelman = Company.builder().name("Gentelman").email("gentelman@gmail.com").password("Gentelmam").build();

            //adding companies to the database
            System.out.println("Adding companies to the data base:");
            adminServiceImpl.addCompany(fashionista);
            adminServiceImpl.addCompany(luver);
            adminServiceImpl.addCompany(angelSpa);
            adminServiceImpl.addCompany(ivElectric);
            adminServiceImpl.addCompany(evaBridal);
            adminServiceImpl.addCompany(restauranta);
            adminServiceImpl.addCompany(bluzWinery);
            adminServiceImpl.addCompany(flyAir);
            adminServiceImpl.addCompany(shoesFactory);
            adminServiceImpl.addCompany(gentelman);
            printRow();

        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }
}