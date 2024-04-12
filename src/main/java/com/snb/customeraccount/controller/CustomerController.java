package com.snb.customeraccount.controller;

import com.snb.customeraccount.dto.CustomerDTO;
import com.snb.customeraccount.service.CustomerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Controller to handle customer information
 */
@RestController("CustomerController")
@RequestMapping(path = "/api/v1/customers")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;
    
    /**
	 * @param customerService
	 */
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}


	@GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> fetchCustomerInfo(@Validated @PathVariable("id") Long id) {
        LOGGER.debug("fetchCustomerInfo - id:{}", id);

            CustomerDTO response = customerService.fetchCustomerInfo(id);
            return ok().body(response);
    }
}