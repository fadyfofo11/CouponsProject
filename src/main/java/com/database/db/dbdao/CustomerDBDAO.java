package com.database.db.dbdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.database.beans.Customer;
import com.database.customExceptions.CustomerAlreadyExistsException;
import com.database.customExceptions.CustomerDoesntExistException;
import com.database.db.dao.CustomersDAO;
import com.database.repositories.CustomerRepository;

@Component
public class CustomerDBDAO implements CustomersDAO {

	@Autowired
	private CustomerRepository customerrepo;

	/**
	 * This finds the customer by email and password and return true if found and
	 * false if not
	 */
	@Override
	public boolean isCustomerExists(String email, String password) {
		Customer customer = customerrepo.findByEmailAndPassword(email, password);
		if (customer.getEmail().equals(email) && customer.getPassword().equals(password))
			return true;
		else
			return false;
	}

	/**
	 * This will save a customer to the database
	 * 
	 * @throws CustomerAlreadyExistsException
	 */
	@Override
	public int addCustomer(Customer customer) throws CustomerAlreadyExistsException {
		if (customerrepo.existsById(customer.getId()))
			throw new CustomerAlreadyExistsException(customer);
		try {
			customerrepo.save(customer);
			return customer.getId();
		} catch (Exception e) {
			throw new CustomerAlreadyExistsException(customer);
		}
	}

	/**
	 * this will update an already existing customer if the customer is not found
	 * and exception will be thrown
	 */
	@Override
	public int updateCustomer(Customer customer) throws CustomerDoesntExistException {
		try {
			if (customerrepo.existsById(customer.getId())) {
				customerrepo.save(customer);
				return customer.getId();
			}
		} catch (Exception e) {
			throw new CustomerDoesntExistException(customer);
		}
		throw new CustomerDoesntExistException(customer);

	}

	/**
	 * This will delete a customer and return a message
	 * 
	 * @throws CustomerDoesntExistException
	 */
	@Override
	public String deleteCustomer(int customerID) throws CustomerDoesntExistException {
		try {
			customerrepo.deleteById(customerID);
			return "Customer With id " + customerID + " have been Succssesfully Deleted!";
		} catch (Exception e) {
			throw new CustomerDoesntExistException(customerID);
		}
	}

	/**
	 * This will return all of the customers in the database
	 */
	@Override
	public List<Customer> getAllCustomers() {
		return customerrepo.findAll();
	}

	/**
	 * This will return a customer from the database
	 */
	@Override
	public Customer getOneCustomer(int customerID) {
		return customerrepo.findById(customerID).orElse(null);
	}

	/**
	 * This will find a customer by email and password
	 */
	@Override
	public Customer getCustomerByLogin(String email, String password) {
		return customerrepo.findByEmailAndPassword(email, password);
	}

}
