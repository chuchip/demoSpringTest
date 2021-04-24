package com.example.demo;

import com.example.demo.collection.Customer;
import com.example.demo.controller.Controlador1;
import com.example.demo.repository.CustomerRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MockitoTestApplication {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    Controlador1 controlador1;

//    @BeforeEach
//    public void setUp()
//    {
//        MockitoAnnotations.openMocks(this);
//    }
    @Test
    @DisplayName("Controller using Mockito")
    void mockController()
    {
        Mockito.when(customerRepository.findAll()).thenReturn(new ArrayList<Customer>());
        List<Customer>  customers=controlador1.getAll();
        Assertions.assertEquals(customers.size(),0);
    }
}
