package by.hustlestar.service.util;

/**
 * Created by Hustler on 10.11.2016.
 */
public class Validation {

    public static boolean validate(String login, String email, String password, String sex) {

        if (login == null || login.isEmpty()) {
            return false;
        }

        if (email == null || email.isEmpty() || !email.matches("\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6}")) {
            return false;
        }

        if (password == null || password.isEmpty()) {
            return false;
        }

        if (sex == null || sex.isEmpty()) {
            return false;
        }

        return true;
    }

    public static boolean validate(String login, String password) {
        if (login == null || login.isEmpty()) {
            return false;
        }
        if (password == null || password.isEmpty()) {
            return false;
        }
        return true;
    }

}
