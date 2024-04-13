package com.snb.customeraccount.dto.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerRequestDTO {
	
		@JsonProperty("customer_name")
	    @NotNull
		private String name;
		@JsonProperty("customer_surname")
	    @NotNull
	    private String surname;

	    // Default constructor (required by Jackson for JSON deserialization)
	    public CustomerRequestDTO() {}

	    // Constructor with fields
	    public CustomerRequestDTO(String name, String surname) {
	        this.name = name;
	        this.surname = surname;
	    }

	    // Getters and setters
	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getSurname() {
	        return surname;
	    }

	    public void setSurname(String surname) {
	        this.surname = surname;
	    }
	}

