package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.collection.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	  public Customer findByFirstName(String firstName);
	  public List<Customer> findByLastName(String lastName);

	}