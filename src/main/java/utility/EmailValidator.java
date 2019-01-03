package utility;

public class EmailValidator {
    private static final String EMAIL = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";

    public static boolean checkEmail(String email) {
        return email.matches(EMAIL);
    }

}
