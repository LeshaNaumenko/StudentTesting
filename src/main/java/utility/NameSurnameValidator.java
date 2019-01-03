package utility;

public class NameSurnameValidator {
    private static final String FIRST_AND_LAST_NAMES = "^[a-zA-Zа-яА-ЯёЁ][a-zA-Zа-яА-ЯёЁ0-9-_\\.]{1,20}$";

    public static boolean checkNames(String names){
        return names.matches(FIRST_AND_LAST_NAMES);
    }

}
