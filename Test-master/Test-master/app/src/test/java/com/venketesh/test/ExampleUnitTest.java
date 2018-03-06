package com.venketesh.test;

import com.venketesh.test.PasswordValidator;
        import com.venketesh.test.UsernameValidator;

        import org.junit.Test;
        import static org.junit.Assert.*;
public class ExampleUnitTest {

    //Testcase for Username
    @Test
    public void email_isCorrect() throws Exception {
        assertTrue("Invalid Username", UsernameValidator.isValidUsername("venketesh"));
    }

    //Testcase for Password
    @Test
    public void password_isCorrect() throws Exception {
        assertTrue("Invalid Password", PasswordValidator.isValidPassword("venketesh123"));
    }
}
