package by.hustlestar.service.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dell on 16.12.2016.
 */
public class Validator {

    private static final String REGEXP = "[\\w\\W]{1,4000}";
    private static final Pattern PATTERN_REGEXP = Pattern.compile(REGEXP);
    private static final String TITLE = "[a-zA-Z0-9_ \\-]{4,64}";
    private static final Pattern PATTERN_TITLE = Pattern.compile(TITLE);
    private static final String NUMBER = "[\\d]+";
    private static final Pattern PATTERN_NUMBER = Pattern.compile(NUMBER);
    private static final String YEAR = "[\\d]{4}";
    private static final Pattern PATTERN_YEAR = Pattern.compile(YEAR);
    private static final String LOGIN = "[a-zA-Z_0-9]{3,20}";
    private static final Pattern PATTERN_LOGIN = Pattern.compile(LOGIN);
    private static final String PASSWORD = "[a-zA-Z_0-9]{6,30}";
    private static final Pattern PATTERN_PASSWORD = Pattern.compile(PASSWORD);

    public static boolean validate(String... data) {
        Matcher matcher;
        for (String arg : data) {
            matcher = PATTERN_REGEXP.matcher(arg);
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }

    public static boolean validate(int... data) {
        for (int arg : data) {
            if (arg < 0) {
                return false;
            }
        }
        return true;
    }
    /*
    public static boolean validate(String data) {
        Matcher matcher;
        matcher = PATTERN_TITLE.matcher(data);
        return matcher.matches();
    }*/

    public static boolean validateNumber(String data) {
        Matcher matcher;
        matcher = PATTERN_NUMBER.matcher(data);
        return matcher.matches();
    }

    public static boolean validateLogin(String login) {
        Matcher matcher;
        matcher = PATTERN_LOGIN.matcher(login);
        return matcher.matches();
    }

    public static boolean validatePassword(String password) {
        Matcher matcher;
        matcher = PATTERN_PASSWORD.matcher(password);
        return matcher.matches();
    }
    public static boolean validatePassword(String password, String passwordrep) {
        Matcher matcher;
        matcher = PATTERN_PASSWORD.matcher(password);
        return matcher.matches() && password.equals(passwordrep);
    }

    public static boolean validateEmail(String email) {
        return email.matches("\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6}");
    }

    public static boolean validateYear(String year) {
        Matcher matcher;
        matcher = PATTERN_YEAR.matcher(year);
        return matcher.matches();
    }
}
