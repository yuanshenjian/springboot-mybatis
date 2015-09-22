package org.yood.springboot.mybatis;


import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootMybatisApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port:0", "server.context-path:/sbm"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class BasicIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicIntegrationTest.class);

    private static long beginSecond;

    @Value("${local.server.port}")
    private int port;

    @Value("${server.context-path}")
    private String contextPath;

    private TestRestTemplate testRestTemplate;

    @BeforeClass
    public static void start() {
        beginSecond = System.currentTimeMillis();
    }

    @AfterClass
    public static void finish() {
        LOGGER.info("Total time = {}s", (System.currentTimeMillis() - beginSecond) / 1000.0);
    }

    @Before
    public void integrationSetup() {
        testRestTemplate = new TestRestTemplate();
    }

    @Test
    public void monitor() {
        LOGGER.info("Integration test is running. [port = {}, contextPath = {}]", port, contextPath);
    }

    protected <T> ResponseEntity<T> post(String uri, Object body, HttpHeaders httpHeaders, Class<T> responseType,
                                         Object... uriVariables) {
        return testRestTemplate.exchange(makeURL(uri), HttpMethod.POST, new HttpEntity<>(body, httpHeaders),
                                         responseType, uriVariables);
    }

    protected <T> ResponseEntity<T> get(String uri, HttpHeaders httpHeaders, Class<T> responseType,
                                        Object... uriVariables) {
        return testRestTemplate.exchange(makeURL(uri), HttpMethod.GET, new HttpEntity<Void>(httpHeaders), responseType,
                                         uriVariables);
    }

    protected <T> ResponseEntity<T> getForEntity(String uri, Class<T> responseType, Object... uriVariables) {
        return testRestTemplate.getForEntity(makeURL(uri), responseType, uriVariables);
    }

    public String makeURL(String uri) {
        return "http://localhost:" + port + contextPath + uri;
    }
}
