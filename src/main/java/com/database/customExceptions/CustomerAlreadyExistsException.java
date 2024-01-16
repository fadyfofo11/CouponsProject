package com.database.customExceptions;

import com.database.beans.Customer;

public class CustomerAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int customerID;
	private Customer customer;

	public CustomerAlreadyExistsException() {

	}

	public CustomerAlreadyExistsException(int customerID) {
		super();
		this.customerID = customerID;
	}

	public CustomerAlreadyExistsException(Customer customer) {
		super();
		this.customer = customer;
	}

	/**
	 * This will return customer message with customer ID as integer
	 */
	@Override
	public String getMessage() {
		return "Customer with ID-" + customerID + " already exists!";
	}

	/**
	 * This will return already exists message with customer object
	 * 
	 * @return
	 */
	public String getWithObject() {
		return "Customer with ID-" + customer.getId() + " already exists!";
	}
}
