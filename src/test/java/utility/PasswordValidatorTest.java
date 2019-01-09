package utility;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordValidatorTest {
    private static final String CORRECT_PASSWORD = "3123532Kkl";
    private static final String INVALID_PASSWORD = "123";

    @Test
    public void shouldReturnTrueOnValidPassword() {
        boolean checkPassword = PasswordValidator.checkPassword(CORRECT_PASSWORD);
        assertTrue(checkPassword);
    }


    @Test
    public void shouldReturnFalseOnValidPassword() {
        boolean checkPassword = PasswordValidator.checkPassword(INVALID_PASSWORD);
        assertFalse(checkPassword);
    }
}