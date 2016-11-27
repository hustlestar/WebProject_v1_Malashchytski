package by.hustlestar.command.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dell on 25.11.2016.
 */
public class CommandsUtil {

    private static final String LANGUAGE = "language";
    private static final String RUSSIAN = "ru";

    private static final String WELCOME_PAGE = "index.jsp";
    private static final String PREVIOUS_QUERY = "previousQuery";


    public static Object getLanguage(HttpServletRequest request) {
        Object lang = request.getSession(false).getAttribute(LANGUAGE);
        if (lang==null){
            return RUSSIAN;
        }
        return lang;
    }

    public static String getPreviousQuery(HttpServletRequest request) {
        String previousQuery = (String) request.getSession(false).getAttribute(PREVIOUS_QUERY);
        if (previousQuery == null) {
            previousQuery = WELCOME_PAGE;
        }
        return previousQuery;
    }
}
