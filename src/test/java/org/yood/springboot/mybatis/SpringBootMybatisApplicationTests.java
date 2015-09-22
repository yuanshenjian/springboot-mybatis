package org.yood.springboot.mybatis;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;


public class SpringBootMybatisApplicationTests extends BasicIntegrationTest {

    @Test
    public void testHome() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        ResponseEntity<String> entity = get("", headers, String.class);
        assertEquals(HttpStatus.FOUND, entity.getStatusCode());
    }

    @Test
    public void testLoginPage() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        ResponseEntity<String> entity = get("/login", headers, String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertTrue("Wrong content:\n" + entity.getBody(), entity.getBody().contains("_csrf"));
    }

    @Test
    public void testLogin() throws Exception {
        HttpHeaders headers = getHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.set("username", "admin");
        form.set("password", "000");
        ResponseEntity<String> entity = post("/login", form, headers, String.class);
        assertEquals(HttpStatus.FOUND, entity.getStatusCode());
        assertTrue("Wrong location:\n" + entity.getHeaders(),
                   entity.getHeaders().getLocation().toString().endsWith("/"));
        assertNotNull("Missing cookie:\n" + entity.getHeaders(), entity.getHeaders().get("Set-Cookie"));
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<String> page = getForEntity("/login", String.class);
        assertEquals(HttpStatus.OK, page.getStatusCode());
        String cookie = page.getHeaders().getFirst("Set-Cookie");
        headers.set("Cookie", cookie);
        Matcher matcher = Pattern.compile("(?s).*name=\"_csrf\".*?value=\"([^\"]+).*").matcher(page.getBody());
        assertTrue("No csrf token: " + page.getBody(), matcher.matches());
        headers.set("X-CSRF-TOKEN", matcher.group(1));
        return headers;
    }

    @Test
    public void testCss() throws Exception {
        ResponseEntity<String> entity = getForEntity("/css/bootstrap.min.css", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertTrue("Wrong body:\n" + entity.getBody(), entity.getBody().contains("body"));
    }

}
