package com.database.customExceptions;

import com.database.beans.Customer;

public class CustomerDoesntExistException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Customer customer;
	private int customerID;

	public CustomerDoesntExistException(int customerID) {
		this.customerID = customerID;
	}

	public CustomerDoesntExistException() {

	}

	public CustomerDoesntExistException(Customer customer) {
		this.customer = customer;
	}

	public String getMessage() {
		return "Customer with id " + customerID + " Doesn't exist!";
	}

	public String getCustomer() {
		return "Customer with id " + customer.getId() + " Doesn't exist!";
	}

}
