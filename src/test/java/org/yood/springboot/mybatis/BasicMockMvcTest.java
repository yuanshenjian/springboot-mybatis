package org.yood.springboot.mybatis;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.yood.springboot.mybatis.web.exception.GlobalExceptionHandlerControllerAdvice;

@RunWith(MockitoJUnitRunner.class)
public abstract class BasicMockMvcTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    protected MockMvc mockMvc;

    @Before
    public void mockSetup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(injectController())
                .setHandlerExceptionResolvers(globalExceptionControllerAdvice())
                .build();
    }

    protected abstract Object injectController();

    private HandlerExceptionResolver globalExceptionControllerAdvice() {
        StaticApplicationContext applicationContext = new StaticApplicationContext();
        applicationContext.registerSingleton("exceptionHandler", GlobalExceptionHandlerControllerAdvice.class);
        WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
        webMvcConfigurationSupport.setApplicationContext(applicationContext);
        return webMvcConfigurationSupport.handlerExceptionResolver();
    }
}
