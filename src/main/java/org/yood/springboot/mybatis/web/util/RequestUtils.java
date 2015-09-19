package org.yood.springboot.mybatis.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtils.class);

    private RequestUtils() {
    }

    public static final String LOGIN = "/lo";

    public static String getRequestURIWithoutContextPath(HttpServletRequest request) {
        String requestURI = request.getRequestURI().replaceAll("/+", "/");
        String contextPath = request.getContextPath().replaceAll("/+", "/");
        if (requestURI.matches(".*/$")) {
            requestURI = requestURI.replaceAll(contextPath + "(.*)/$", "$1");
        } else {
            requestURI = requestURI.replaceAll(contextPath + "(.*)$", "$1");
        }
        return requestURI;
    }

    public static boolean isLogin(HttpServletRequest request) {
        LOGGER.info("contextPath = {}, requestURI = {}", request.getContextPath(), request.getRequestURI());
        return RequestMethod.POST.toString().equalsIgnoreCase(request.getMethod()) && LOGIN.equalsIgnoreCase(
                getRequestURIWithoutContextPath(request));
    }

}
