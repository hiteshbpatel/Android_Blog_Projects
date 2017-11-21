package com.yash.yaswanth.test;
import android.app.Activity;
import android.widget.EditText;
import org.junit.Test;
import static org.junit.Assert.*;
public class ExampleUnitTest {

    //Testcase for Email id
    @Test
    public void email_isCorrect() throws Exception {
        assertTrue(EmailAddressValidator.isValidEmailAddress("yaswanthb53@gmail.com"));
    }

    //Testcase for Password
    @Test
    public void password_isCorrect() throws Exception {
        assertTrue(PasswordValidator.isValidPassword("yashwanth@123"));
    }
}
