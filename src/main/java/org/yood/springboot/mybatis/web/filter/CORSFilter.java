package org.yood.springboot.mybatis.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CORSFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CORSFilter.class);

    private static final String CLIENT_ORIGIN = "localhost:8088";

    @Value("${cors.origin}")
    private String corsOrigin;

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        LOGGER.info("Client origin = {}, HTTP Method={}", CLIENT_ORIGIN, request.getMethod());
//        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, CLIENT_ORIGIN);
//        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
//        if (request.getHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD) != null && HttpMethod.OPTIONS.name()
//                .equals(request.getMethod())) {
//            response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE");
//            response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Content-Type");
//            response.addHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
//        }
//        filterChain.doFilter(request, response);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        LOGGER.info("Client origin = {}, HTTP Method={}", corsOrigin, request.getMethod());

        response.addHeader("Access-Control-Allow-Origin", corsOrigin);
        response.addHeader("Access-Control-Allow-Credentials", "true");
        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            response.addHeader("Access-Control-Max-Age", "3600");
        }
        filterChain.doFilter(request, response);
    }
}
