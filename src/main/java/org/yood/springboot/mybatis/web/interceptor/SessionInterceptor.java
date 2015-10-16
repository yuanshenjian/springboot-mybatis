package org.yood.springboot.mybatis.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.yood.springboot.mybatis.web.util.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionInterceptor.class);

    @Value("${cors.origin}")
    private String clientOrigin;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        LOGGER.info("client origin = {}", clientOrigin);

        HttpSession session = request.getSession(RequestUtils.isLogin(request));
        String requestedSessionId = request.getRequestedSessionId();

        LOGGER.info("Requested Session = {}, Request selectById session = {}", requestedSessionId,
                    session == null ? null : session.getId());
        if (null == session) {
            LOGGER.info("!!!Invalid session  : {}", requestedSessionId);
            response.reset();
            response.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
            return true;
        }

        LOGGER.info("Valid session = {}, isNew = {}, Continue...", session.getId(), session.isNew());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
