package com.example.demo;

import com.example.demo.collection.Customer;
import com.example.demo.collection.Operaciones;
import com.example.demo.controller.Controlador1;
import com.example.demo.repository.CustomerRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Run the whole application but we don't use Mongo. We mock the Repository.
 * We are mocking the connection to Mongo con the label @Mock
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MockitoWebTestApplication {

    @Mock
    CustomerRepository customerRepository; // Tendremos que especificar valores a retornar.

    @InjectMocks
    Controlador1 controlador1; // Es como hacer un autowired. Llamaremos a nuestra clase de verdad.

    @Mock
    Operaciones operaciones;
    /**
     * Inicializo las condiciones para mockito
     */
    @BeforeAll
    public void putWhen()
    {
        Mockito.when(customerRepository.findAll()).thenReturn(new ArrayList<Customer>());
        Mockito.when(operaciones.sumar(3,3)).thenReturn(4);
    }

    /**
     * Realizo un test simple
     * Tener en cuenta que realmente no se esta utilizando la base de datos ni se esta inicializando la aplicacion
     */
    @Test
    @DisplayName("Run web application using Mockito")
    void mockController() throws URISyntaxException {
        List<Customer>  customers=controlador1.getAll();
        Assertions.assertEquals(customers.size(),0);

    }
}
