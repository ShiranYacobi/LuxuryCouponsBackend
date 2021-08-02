package com.project.luxuryCoupons.Login;

import com.project.luxuryCoupons.enums.ClientType;
import com.project.luxuryCoupons.exceptions.LuxuryCouponsException;
import com.project.luxuryCoupons.services.*;
import com.project.luxuryCoupons.prints.ServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * The Login manager is a singleton
 * contains a Login method, that allows all the client types to connect to the system
 */
@Component
@RequiredArgsConstructor
public class LoginManager {
    /**
     * Admin Service Impl - field
     */
    private final AdminServiceImpl adminServiceImpl;
    /**
     * Company Service Impl - field
     */
    private final CompanyServiceImpl companyServiceImpl;
    /**
     * Customer Service Impl - field
     */
    private final CustomerServiceImpl customerServiceImpl;

    /**
     * Login method
     * will login a specific client type: Admin, Company, or Customer
     *
     * @param email      - the email of the client for login
     * @param password   - the password of the client for login
     * @param clientType - a specific client type
     * @return client service instance
     * @throws LuxuryCouponsException - throws exceptions if the client doesn't exist
     */
    public ClientService login(String email, String password, ClientType clientType) throws LuxuryCouponsException {
        switch (clientType) {
            case ADMIN -> {
                ServiceType.printAdmin();
                return (adminServiceImpl.login(email, password)) ? adminServiceImpl : null;
            }
            case COMPANY -> {
                ServiceType.printCompany();
                return (companyServiceImpl.login(email, password)) ? companyServiceImpl : null;
            }
            case CUSTOMER -> {
                ServiceType.printCustomer();
                return (customerServiceImpl.login(email, password)) ? customerServiceImpl : null;
            }
        }
        return null;
    }
}

