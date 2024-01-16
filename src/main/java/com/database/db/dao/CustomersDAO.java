package com.database.db.dao;

import java.util.List;

import com.database.beans.Customer;
import com.database.customExceptions.CustomerAlreadyExistsException;
import com.database.customExceptions.CustomerDoesntExistException;

public interface CustomersDAO {

	// this checks if the customer exists in the database
	boolean isCustomerExists(String email, String password);

	// this adds a new customer to the database and returns the customers id of the
	// customer that has been added
	int addCustomer(Customer customer) throws CustomerAlreadyExistsException;

	/**
	 * This updates a customer and returns the customer id
	 * 
	 * @param customer
	 * @return
	 * @throws CustomerDoesntExistException
	 */
	int updateCustomer(Customer customer) throws CustomerDoesntExistException;

	/**
	 * This deletes a customer and returns a message
	 * 
	 * @param customerID
	 * @return
	 * @throws CustomerDoesntExistException
	 */
	String deleteCustomer(int customerID) throws CustomerDoesntExistException;

	/**
	 * This returns all of the customers into a List
	 * 
	 * @return
	 */
	List<Customer> getAllCustomers();

	/**
	 * This return one customer by id
	 * 
	 * @param customerID
	 * @return
	 */
	Customer getOneCustomer(int customerID);

	/**
	 * This will return a customer by email and password
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	Customer getCustomerByLogin(String email, String password);

}