package com.react.example.cardatabase.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAuthenticationWitValidCredentials() throws Exception{
        this.mockMvc.perform(post("/login")
                .content("{\"username\":\"admin\", \"password\":\"admin\"}"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testAuthenticationWitInvalidCredentials() throws Exception{
        this.mockMvc.perform(post("/login")
                .content("{\"username\":\"admin\", \"password\":\"ksala\"}"))
                .andDo(print()).andExpect(status().is4xxClientError());
    }
}
