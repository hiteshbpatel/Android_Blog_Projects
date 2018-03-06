package com.venketesh.test;

import android.widget.Toast;

/**
 * Created by yaswanth on 21-11-2017.
 */

public class PasswordValidator {
    public static boolean isValidPassword(String password){
        if(password == null){
            return false;
        }

       if(password.length()<6)
            return false;
        return true;

    }
}
