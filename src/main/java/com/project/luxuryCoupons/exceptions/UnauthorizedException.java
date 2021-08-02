package com.project.luxuryCoupons.exceptions;

/**
 * Unauthorized Exception class
 * uses custom exception in case the client cannot execute methos that dont belong to his type
 */
public class UnauthorizedException extends Exception{
    /**
     * Constructor for creating new exception
     *
     * @param message - the custom message for the report
     */
    public UnauthorizedException(String message) {
        super(message);
    }
}
