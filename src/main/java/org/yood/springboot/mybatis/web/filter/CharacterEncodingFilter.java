package org.yood.springboot.mybatis.web.filter;


import org.yood.springboot.mybatis.util.EncodingUtils;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(EncodingUtils.UTF_8);
        response.setCharacterEncoding(EncodingUtils.UTF_8);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
