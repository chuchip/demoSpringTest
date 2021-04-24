package com.example.demo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.example.demo.collection.Customer;
import com.example.demo.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;


/**
 * We are using @SpringBootTest, so SpringBoot run the whole application and listen in a random port.
 * 
 * We can send requests to our controller, using HTTP requests using TestRestTemplate
 * We don't use  Mockito.
 * 
 * @author ProfesorP
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // I'm using this because I want the function starting to not be  static
@Slf4j
class DemoSpringTestApplicationTests {
	@LocalServerPort
	int puerto; // Puerto donde correra nuestra aplicación.
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private CustomerRepository customerRepository;

	final  static public String FIRSTNAME="Test First Name";

	/**
	 * Loading Initial data for MongoDB database.
	 */
	@BeforeAll
	public  void starting()
	{
		Customer customer=new Customer();
		customer.setId("1");
		customer.setFirstName(FIRSTNAME);
		customer.setLastName("Test Last Name");
		customerRepository.save(customer);
	}

	@Test
	@DisplayName("Running app wihtout mockito")
	void contextLoads() throws URISyntaxException {
	RequestEntity<Void> request= RequestEntity.get(new URI("http://localhost:"+puerto)) // Creamos la URL de conexion.
			     .accept(MediaType.APPLICATION_JSON).build();

		ParameterizedTypeReference<List<Customer>> myList =
			     new ParameterizedTypeReference<List<Customer>>() {}; // Use this so it can return a List
		ResponseEntity<List<Customer>> responseEntity= restTemplate.exchange(request, myList);

		Assertions.assertEquals(responseEntity.getStatusCodeValue(),200);
		var respuesta=responseEntity.getBody();
		Assertions.assertEquals(respuesta.size(),1);
		Assertions.assertEquals(respuesta.get(0).getFirstName(),FIRSTNAME);
	}

}
