package org.yood.springboot.mybatis;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.yood.springboot.mybatis.web.exception.GlobalExceptionHandlerControllerAdvice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(MockitoJUnitRunner.class)
public abstract class BasicMockMvcTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private MockMvc mockMvc;

    @Before
    public void mvcSetup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(injectController())
                .setHandlerExceptionResolvers(globalExceptionControllerAdvice())
                .build();
    }

    protected abstract Object injectController();

    protected ResultActions mockGet(String uri, MediaType mediaType, Object... urlVariables) throws Exception {
        return mockMvc.perform(get(uri, urlVariables).contentType(mediaType));
    }

    protected ResultActions mockPost(String uri, MediaType mediaType, String content,
                                     Object... urlVariables) throws Exception {
        return mockMvc.perform(post(uri, urlVariables).contentType(mediaType).content(content));
    }

    protected ResultActions mockPut(String uri, MediaType mediaType, String content,
                                    Object... urlVariables) throws Exception {
        return mockMvc.perform(put(uri, urlVariables).contentType(mediaType).content(content));
    }

    private HandlerExceptionResolver globalExceptionControllerAdvice() {
        StaticApplicationContext applicationContext = new StaticApplicationContext();
        applicationContext.registerSingleton("exceptionHandler", GlobalExceptionHandlerControllerAdvice.class);
        WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
        webMvcConfigurationSupport.setApplicationContext(applicationContext);
        return webMvcConfigurationSupport.handlerExceptionResolver();
    }
}
