package by.hustlestar.command.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by Hustler on 01.11.2016.
 */
public class QueryUtil {

    private QueryUtil() {
    }

    public static String createHttpQueryString(HttpServletRequest request){
        Enumeration<String> params = request.getParameterNames();
        String query = "";

        String key;
        String value;
        while(params.hasMoreElements()){
            key = params.nextElement();
            value  = request.getParameter(key);
            query = query + "&" + key + "=" + value;
        }
        query = request.getRequestURL() + "?" + query;
        return query;
    }
}
