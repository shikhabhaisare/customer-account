package com.snb.customeraccount.service;

import java.util.List;

import com.snb.customeraccount.dto.CustomerDTO;

/**
 * Customer services
 *
 */
public interface CustomerService {

    /**
     * Service to fetch customer info
     *
     * @param id The customer ID
     * @return the customer info
     */
	
	
    CustomerDTO fetchCustomerInfo(Long id);

    /**
     * Service to fetch all customers
     *
     * @return the list of customers
     */
    
    
    List<CustomerDTO> fetchAllCustomers();
    
    /**
	 * @param name
	 * @param surname
	 * @return
	 */
    
	CustomerDTO saveCustomer(String name, String surname);
}
