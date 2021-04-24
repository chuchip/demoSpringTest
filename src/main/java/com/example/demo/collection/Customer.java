package com.example.demo.collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "customer")
public class Customer {

	  @Id
	  public String id;

	  public String firstName;
	  public String lastName;

	  public Customer() {}

	  public Customer(String firstName, String lastName) {
	    this.firstName = firstName;
	    this.lastName = lastName;
	  }

	
	}