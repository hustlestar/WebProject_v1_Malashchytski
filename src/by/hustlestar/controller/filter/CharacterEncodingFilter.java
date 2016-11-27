package by.hustlestar.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import javax.servlet.*;

public class CharacterEncodingFilter implements Filter {
    private final static Logger LOGGER = LogManager.getRootLogger();
    private static final String CHARSET = "UTF-8";
    private static final String ENCODING = "encoding";
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private String encoding;
    private ServletContext servletContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter(ENCODING);
        servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(encoding);
        servletContext.log("Charset was set "+encoding);
        LOGGER.info("Filter was done: charset encoding was set.");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}