package utility;

import org.junit.Test;

import static org.junit.Assert.*;

public class NameSurnameValidatorTest {
    private static final String CORRECT_NAME_OR_SURNAME =  "Alex._.134";
    private static final String INVALID_NAME_OR_SURNAME =  "asasasasasasasasasasas20";
    @Test
    public void shouldReturnTrueOnValidPassword() {
        boolean checkNames = NameSurnameValidator.checkNames(CORRECT_NAME_OR_SURNAME);
        assertTrue(checkNames);
    }


    @Test
    public void shouldReturnFalseOnValidPassword() {
        boolean checkNames = NameSurnameValidator.checkNames(INVALID_NAME_OR_SURNAME);
        assertFalse(checkNames);
    }

}