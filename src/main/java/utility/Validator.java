package utility;

public class Validator {
private static final String EMAIL = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
private static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,}$";
private static final String FIRST_AND_LAST_NAMES = "^[a-zA-Zа-яА-ЯёЁ][a-zA-Zа-яА-ЯёЁ0-9-_\\.]{1,20}$";

    public static boolean checkEmail(String email){
        return email.matches(EMAIL);
    }
    public static boolean checkPassword(String pass){
        return pass.matches(PASSWORD);
    }
    public static boolean checkNames(String names){
        return names.matches(FIRST_AND_LAST_NAMES);
    }

}
