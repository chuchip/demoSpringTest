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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.ArrayList;
import java.util.List;

/**
 * The web application is not started. We mock everything.
 */
@ExtendWith(MockitoExtension.class) // Si no se pone esta etiqueta hay que poner la funcion setUp que esta comentada en este ejemplo.
public class MockitoTestApplication {

    @Mock
    CustomerRepository customerRepository; // Tendremos que especificar

    @InjectMocks
    Controlador1 controlador1;

//    @BeforeEach
//    public void setUp()
//    {
//        MockitoAnnotations.openMocks(this);
//    }

    /**
     * Inicializo las condiciones para mockito
     */
    @BeforeTestClass
    public void putWhen()
    {
        Mockito.when(customerRepository.findAll()).thenReturn(new ArrayList<Customer>());
    }

    /**
     * Realizo un test simple
     * Tener en cuenta que realmente no se esta utilizando la base de datos ni se esta inicializando la aplicacion
     */
    @Test
    @DisplayName("Controller using Mockito")
    void mockController()
    {
        List<Customer>  customers=controlador1.getAll();
        Assertions.assertEquals(0,customers.size());
    }
}
