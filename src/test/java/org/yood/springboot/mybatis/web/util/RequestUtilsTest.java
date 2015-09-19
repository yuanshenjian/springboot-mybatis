package org.yood.springboot.mybatis.web.util;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.annotation.RequestMethod;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class RequestUtilsTest {

    private static final String CONTEXT_PATH = "/sbm";

    private MockHttpServletRequest request;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        request.setContextPath(CONTEXT_PATH);
    }


    @Test
    public void testGetRequestURIWithoutContextPath() throws Exception {
        request.setRequestURI("/sbm/users/");
        assertEquals("/users", RequestUtils.getRequestURIWithoutContextPath(request));
    }

    @Test
    public void testRootPath() throws Exception {
        request.setRequestURI("/sbm/");
        assertEquals("", RequestUtils.getRequestURIWithoutContextPath(request));
    }


    @Test
    public void testIsLogin() throws Exception {
        request.setRequestURI(CONTEXT_PATH + RequestUtils.LOGIN);
        request.setMethod(RequestMethod.POST.name());
        assertTrue(RequestUtils.isLogin(request));
    }

    @Test
    public void testIsLoginWithSlash() throws Exception {
        request.setRequestURI(CONTEXT_PATH + RequestUtils.LOGIN+"/");
        request.setMethod(RequestMethod.POST.name());
        assertTrue(RequestUtils.isLogin(request));
    }
}