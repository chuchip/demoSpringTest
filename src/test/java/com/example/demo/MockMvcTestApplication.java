package com.example.demo;

import com.example.demo.collection.Customer;
import com.example.demo.controller.Controlador1;
import com.example.demo.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * In this class I'm using MockMvc. I'm not using mongo but the whole application is running.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcTestApplication {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    CustomerRepository customerRepository;

    @BeforeTestClass
    public void putWhen()
    {
        Mockito.when(customerRepository.findAll()).thenReturn(new ArrayList<Customer>());
    }
    @Test
    @DisplayName("Calling HelloWorld")
    public void call() throws Exception {
        this.mockMvc.perform(get("/helloWorld")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(Controlador1.HELLOWORLD)));
    }
    @Test
    @DisplayName("Calling / we mock connection to db")
    void mockController() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("[]"))); // No habra ningun registro por lo cual devolvera []

    }
}
