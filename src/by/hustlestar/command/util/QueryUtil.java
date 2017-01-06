package by.hustlestar.command.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * Created by Hustler on 01.11.2016.
 */
public class QueryUtil {
    private static final String SESSION_PREV_QUERY = "previousQuery";
    private static final char QUERY_SEPARATOR = '?';

    private QueryUtil() {
    }

    public static void saveCurrentQueryToSession(HttpServletRequest request){
        HttpSession session = request.getSession(true);

        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();

        if(queryString == null){
            session.setAttribute(SESSION_PREV_QUERY, requestURI);
        }
        else {
            session.setAttribute(SESSION_PREV_QUERY, requestURI + QUERY_SEPARATOR + queryString);
        }
    }
}
