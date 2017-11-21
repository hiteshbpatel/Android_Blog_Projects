package com.yash.yaswanth.test;

/**
 * Created by user on 11/21/2017.
 */

public class PasswordValidator {

    public static boolean isValidPassword(String password)
    {
        // a null string is invalid
        if ( password == null )
            return false;

        // a string less than equal to 6 characters is invalid
        if ( password.length() <= 6 )
            return false;


        return true;
    }
}