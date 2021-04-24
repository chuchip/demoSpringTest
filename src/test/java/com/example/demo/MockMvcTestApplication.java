package com.example.demo;

import com.example.demo.collection.Customer;
import com.example.demo.controller.Controlador1;
import com.example.demo.repository.CustomerRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * In this class I'm using MockMvc. I'm  using mongo and the whole application is running.
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // I'm using this because I want the function starting to not be  static
public class MockMvcTestApplication {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Data loading into embedded MongoDB.
     * When we run this test the application is started again, so we have the database empty although in the previous test we have loaded data.
     */
    @BeforeAll
    public  void starting()
    {
        Customer customer=new Customer();
        customer.setId("1");
        customer.setFirstName(DemoSpringTestApplicationTests.FIRSTNAME);
        customer.setLastName("Test Last Name");
        customerRepository.save(customer);
    }

    @Test
    @DisplayName("Calling HelloWorld")
    public void call() throws Exception {
        this.mockMvc.perform(get("/helloWorld")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(Controlador1.HELLOWORLD)));
    }
    @Test
    @DisplayName("Calling getAll with mockMVC")
    void mockController() throws Exception {
        MvcResult resultado  = this.mockMvc.perform(get("/")).andExpect(status().isOk()).andReturn();
        String contenido= resultado.getResponse().getContentAsString();

        List<Customer> customers= new ObjectMapper().readValue(contenido, new TypeReference<List<Customer>>() {   }); // Use TypeReference to map the List.

        Assertions.assertEquals(customers.size(), 1);
        Assertions.assertEquals(customers.get(0).getFirstName(),DemoSpringTestApplicationTests.FIRSTNAME);
    }
}
