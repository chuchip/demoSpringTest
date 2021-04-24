package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.collection.Customer;
import com.example.demo.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
public class Controlador1 {

	public final static String HELLOWORLD="Hello World!";

	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping
	public List<Customer> getAll()
	{
		log.info("--------------");
		log.info("I'm in getAll");
		log.info("--------------");
		
		return customerRepository.findAll();
	}
	@GetMapping("helloWorld")
	public String helloWorld()
	{
		log.info("--------------");
		log.info("I'm in helloWorld");
		log.info("--------------");

		return HELLOWORLD;
	}
	@PostMapping("/add")
	public Customer add(@RequestBody Customer cliente)
	{
		return customerRepository.save(cliente);
	}
}
