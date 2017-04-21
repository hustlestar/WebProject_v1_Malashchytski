package by.hustlestar.command.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * CommandsUtil class is a helper class used to gather some common methods
 * which are used by many Command implementation classes.
 */
public class CommandsUtil {

    private static final String LANGUAGE = "language";
    private static final String RUSSIAN = "ru";

    private static final String WELCOME_PAGE = "index.jsp";
    private static final String PREVIOUS_QUERY = "previousQuery";
    private static final String SESSION_PREV_QUERY = "previousQuery";
    private static final char QUERY_SEPARATOR = '?';

    private CommandsUtil() {
    }

    /**
     * This method is used to get session language, if session exists
     * in other case returns russian language by default.
     * @param request
     * @return String language
     */
    public static Object getLanguage(HttpServletRequest request) {
        Object lang = request.getSession(false).getAttribute(LANGUAGE);
        if (lang == null) {
            return RUSSIAN;
        }
        return lang;
    }

    /**
     * This method is used to get previous query string if it exists,
     * in other case it returns default welcome page.
     * @param request
     * @return String previous query String
     */
    public static String getPreviousQuery(HttpServletRequest request) {
        String previousQuery = (String) request.getSession(false).getAttribute(PREVIOUS_QUERY);
        if (previousQuery == null) {
            previousQuery = WELCOME_PAGE;
        }
        return previousQuery;
    }

    /**
     * This method creates session for client if it does not exist
     * and sets query string attribute to this session object.
     * @param request
     */
    public static void saveCurrentQueryToSession(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();

        if (queryString == null) {
            session.setAttribute(SESSION_PREV_QUERY, requestURI);
        } else {
            session.setAttribute(SESSION_PREV_QUERY, requestURI + QUERY_SEPARATOR + queryString);
        }
    }
}
