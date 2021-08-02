package com.project.luxuryCoupons.exceptions;

/**
* The Token expired exception class
* uses custom exception in case the token expired
 */
public class TokenExpiredException extends Exception{
    /**
     * Constructor for creating new exception
     * @param message - the custom message for the report
     */
    public TokenExpiredException(String message) {
        super(message);
    }
}
