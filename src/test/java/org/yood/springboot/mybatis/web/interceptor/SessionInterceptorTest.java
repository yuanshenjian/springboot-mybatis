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

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SessionInterceptorTest extends BasicIntegrationTest {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @Test
    public void testPreHandle() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest(HttpMethod.GET.name(), "/users");
        MockHttpServletResponse response = new MockHttpServletResponse();

        HandlerExecutionChain executionChain = handlerMapping.getHandler(request);
        HandlerInterceptor[] interceptors = executionChain.getInterceptors();

        Optional<HandlerInterceptor> containInterceptor = Arrays.asList(interceptors)
                .stream()
                .filter(interceptor -> SessionInterceptor.class == interceptor.getClass())
                .findFirst();
        containInterceptor.get().preHandle(request, response, executionChain.getHandler());
        ModelAndView modelAndView = handlerAdapter.handle(request, response, executionChain.getHandler());
        containInterceptor.get().postHandle(request, response, executionChain.getHandler(), modelAndView);
        assertTrue(containInterceptor.isPresent());
        assertEquals(HttpStatus.SC_REQUEST_TIMEOUT, response.getStatus());
    }
}