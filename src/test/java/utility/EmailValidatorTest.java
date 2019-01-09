package utility;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailValidatorTest {
    private static final String VALID_EMAIL = "username@aaa.aaa";
    private static final String INVALID_EMAIL = "username@sad";
    @Test
    public void shouldReturnTrueOnValidEmail() {
        boolean checkEmail = EmailValidator.checkEmail(VALID_EMAIL);
        assertTrue(checkEmail);
    }

    @Test
    public void shouldReturnFalseOnValidEmail() {
        boolean checkEmail = EmailValidator.checkEmail(INVALID_EMAIL);
        assertFalse(checkEmail);
    }
}