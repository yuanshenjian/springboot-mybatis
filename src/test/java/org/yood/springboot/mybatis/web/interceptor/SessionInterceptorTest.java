package org.yood.springboot.mybatis.web.interceptor;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.yood.springboot.mybatis.BasicIntegrationTest;

import static org.junit.Assert.assertEquals;

public class SessionInterceptorTest extends BasicIntegrationTest {


    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @Test
    public void testPreHandle() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/users");
        request.setMethod(HttpMethod.GET.name());
        MockHttpServletResponse response = new MockHttpServletResponse();

        HandlerExecutionChain executionChain = handlerMapping.getHandler(request);
        HandlerInterceptor[] interceptors = executionChain.getInterceptors();
        for (HandlerInterceptor interceptor : interceptors) {
            interceptor.preHandle(request, response, executionChain.getHandler());
        }
        ModelAndView modelAndView = handlerAdapter.handle(request, response, executionChain.getHandler());
        for (HandlerInterceptor interceptor : interceptors) {
            interceptor.postHandle(request, response, executionChain.getHandler(), modelAndView);
        }
        assertEquals(HttpStatus.SC_REQUEST_TIMEOUT, response.getStatus());
    }
}