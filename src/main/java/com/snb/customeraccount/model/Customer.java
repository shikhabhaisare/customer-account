package com.snb.customeraccount.model;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * The customer entity model representing the related DB table
 */

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 50, message
            = "Customer name must be between 3 and 50 characters")
    private String name;

    @Size(min = 3, max = 50, message
            = "Customer surname must be between 3 and 50 characters")
    private String surname;

    @OneToMany()
    @JoinColumn(name = "customer_id")
    private List<Account> accounts;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the accounts
	 */
	public List<Account> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", surname=" + surname + ", accounts=" + accounts + "]";
	}
    
	
    
}

