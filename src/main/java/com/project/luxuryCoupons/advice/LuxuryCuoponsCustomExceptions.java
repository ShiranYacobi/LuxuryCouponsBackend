package com.project.luxuryCoupons.advice;

import com.project.luxuryCoupons.exceptions.LuxuryCouponsException;
import com.project.luxuryCoupons.exceptions.TokenExpiredException;
import com.project.luxuryCoupons.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * Luxury coupons advice
 * Handles client side exceptions in REST controller
 * and generally allows to write global code that can be applied to a wide range of controllers
 */
@RestController
@ControllerAdvice
@SuppressWarnings("all")
public class LuxuryCuoponsCustomExceptions extends Exception {
    /**
     * Handle exception method
     * shows the error description and details
     * @param err - the error
     * @return the and description
     */
    @ExceptionHandler(value = {LuxuryCouponsException.class})
    public ResponseEntity handleException(Exception err){
        return new ResponseEntity<>(err.getMessage(),HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle user exception
     * shows the user's error description and details
     * @param err - the error
     * @return the description and details
     */
    @ExceptionHandler(value = {UnauthorizedException.class})
    public ResponseEntity handleUserException(Exception err){
        return new ResponseEntity<>(err.getMessage(),HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handle token exception
     * shows the error description and details
     * @param err - the error
     * @return the description and details
     */
    @ExceptionHandler(value = {TokenExpiredException.class})
    public ResponseEntity handleTokenExeption(Exception err){
        return new ResponseEntity<>(err.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
