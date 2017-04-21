package by.hustlestar.service.validation;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator is a class used for validation of data from services.
 */
public class Validator {

    private static final String REGEXP = "[\\w\\W]{1,4000}";
    private static final Pattern PATTERN_REGEXP = Pattern.compile(REGEXP);
    private static final String TITLE = "[a-zA-Z0-9_ \\-]{4,64}";
    private static final Pattern PATTERN_TITLE = Pattern.compile(TITLE);
    private static final String NUMBER = "[\\d]+";
    private static final Pattern PATTERN_NUMBER = Pattern.compile(NUMBER);
    private static final String YEAR = "[1|2]{1}[9|0|1]{1}[\\d]{2}";
    private static final Pattern PATTERN_YEAR = Pattern.compile(YEAR);
    private static final String LOGIN = "[a-zA-Z_0-9]{3,16}";
    private static final Pattern PATTERN_LOGIN = Pattern.compile(LOGIN);
    private static final String EMAIL = "\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6}";

    /**
     * This method validates array of Strings. Any string should be at least 1 symbol long.
     *
     * @param data array of Strings
     * @return true if everything is fine
     */
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

    /**
     * This method validates int numbers. Numbers should be greater than 0.
     *
     * @param data array of ints
     * @return true if everything is fine
     */
    public static boolean validate(int... data) {
        for (int arg : data) {
            if (arg < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method validates string to consist only of digits.
     *
     * @param data String
     * @return true if everything matches
     */
    public static boolean validateNumber(String data) {
        Matcher matcher;
        matcher = PATTERN_NUMBER.matcher(data);
        return matcher.matches();
    }

    /**
     * This method is used to check login for matching regexp.
     *
     * @param login String
     * @return true if everything matches
     */
    public static boolean validateLogin(String login) {
        Matcher matcher;
        matcher = PATTERN_LOGIN.matcher(login);
        return matcher.matches();
    }

    /**
     * Validates length of password.
     *
     * @param password byte array
     * @return true if greater than 5
     */
    public static boolean validatePassword(byte[] password) {
        return password.length > 5;
    }

    /**
     * Compares two passwords to be equal.
     *
     * @param password    byte array
     * @param passwordrep byte array
     * @return result of comparison
     */
    public static boolean validatePassword(byte[] password, byte[] passwordrep) {
        return Arrays.equals(password, passwordrep);
    }

    /**
     * Validates email to match regexp for email.
     *
     * @param email String
     * @return result
     */
    public static boolean validateEmail(String email) {
        return email.matches(EMAIL);
    }

    /**
     * Validates year to consist only digits and match regexp.
     *
     * @param year YYYY
     * @return result
     */
    public static boolean validateYear(String year) {
        Matcher matcher;
        matcher = PATTERN_YEAR.matcher(year);
        return matcher.matches();
    }
}
