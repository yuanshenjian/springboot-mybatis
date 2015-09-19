package org.yood.springboot.mybatis.web.controller;

import com.alibaba.fastjson.JSON;
import org.yood.springboot.mybatis.entity.Contact;
import org.yood.springboot.mybatis.service.ContactService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {

    @Mock
    private ContactService contactService;

    @InjectMocks
    private ContactController contactController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
    }

    @Test
    public void testGet() throws Exception {
        Contact contact = new Contact("Shenjian", "丈八六路", 25);
        when(contactService.queryById(anyInt())).thenReturn(contact);
        mockMvc.perform(get("/contacts/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Shenjian")));
    }

    @Test
    public void testGetAll() throws Exception {
        Contact contact1 = new Contact("ShenjianYuan1", "ShannXi Xi'an", 25);
        Contact contact2 = new Contact("ShenjianYuan2", "ShannXi Xi'an", 25);
        when(contactService.queryAll()).thenReturn(Arrays.asList(contact1, contact2));
        mockMvc.perform(get("/contacts")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("ShenjianYuan1")))
                .andExpect(jsonPath("$[1].name", is("ShenjianYuan2")));
    }


    @Test
    public void testAdd() throws Exception {
        Contact contact = new Contact("Shenjian,Yuan", "China Shannxi Xi'an", 25);
        contact.setUserId(1);
        mockMvc.perform(post("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(contact)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        Contact contact = new Contact("Shenjian,Yuan", "China Shannxi Xi'an", 28);
        contact.setId(1);
        mockMvc.perform(put("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(contact)))
                .andExpect(status().isOk());
    }
}