package by.hustlestar.controller.filter;

import java.io.IOException;
import javax.servlet.*;

public class CharacterEncodingFilter implements Filter {

    private static final String CHARSET = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(CHARSET);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}