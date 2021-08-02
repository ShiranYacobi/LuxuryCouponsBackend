package com.project.luxuryCoupons.beans;

import com.project.luxuryCoupons.enums.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The User details class
 * represent the user data
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDetails {
    /**
     * Email - field
     */
    private String email;
    /**
     * Password - filed
     */
    private String password;
    /**
     * Client type - filed
     */
    private ClientType clientType;
    /**
     * User id - filed
     */
    private int userId;

    /**
     * Constructor without the password
     * @param email
     * @param clientType
     * @param userId
     */
    public UserDetails(String email, ClientType clientType, int userId) {
        this.email = email;
        this.clientType = clientType;
        this.userId = userId;
    }
}
