package com.database.facades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.database.beans.Company;
import com.database.beans.Coupon;
import com.database.beans.Customer;
import com.database.beans.Purchase;
import com.database.customExceptions.CompanyAlreadyExistsException;
import com.database.customExceptions.CompanyDoesntExistException;
import com.database.customExceptions.CustomerAlreadyExistsException;
import com.database.customExceptions.CustomerDoesntExistException;
import com.database.repositories.PurchasesRepository;

@Service
public class AdminFacade extends ClientFacade {

	@Autowired
	private PurchasesRepository purchrepo;

	/**
	 * This checks if you are an admin and returns true if email and password match
	 * else it will return false
	 */
	@Override
	public boolean login(String email, String password) {
		if (email.equals("admin@admin.com") && password.equals("admin"))
			return true;
		return false;
	}

	/**
	 * This method if called will add a new company to the database
	 * 
	 * @param company
	 * @return
	 */
	public String addNewCompany(Company company) {
		try {
			companyDAO.addCompany(company);
		} catch (CompanyAlreadyExistsException e) {
			return e.getMessage();
		}
		return "Company '" + company.getName() + "' Has been Succsesfully added!";
	}

	/**
	 * This method if called will update an existing company if the company doesn't
	 * exist it will return an exception
	 * 
	 * @param company
	 * @return
	 */
	public String updateCompany(Company company) {
		try {
			companyDAO.updateCompany(company);
		} catch (CompanyDoesntExistException e) {
			return e.getMessage();
		}
		return "Company '" + company.getName() + "' Has been Successfully updated!";
	}

	/**
	 * This method if called will delete an existing company from the database
	 * 
	 * @param companyID
	 * @return
	 */
	public String deleteCompany(int companyID) {
		try {
			Company comp = companyDAO.getOneCompany(companyID);
//			couponDAO.deleteCouponPurchase(coupon.getId());
			for (Coupon coupon : comp.getCoupon()) {
				List<Purchase> pur = purchrepo.findByCouponID(coupon.getId());
				for (Purchase purchase : pur) {

					couponDAO.deleteCouponPurchase(purchase);
				}
//				couponDAO.deleteCoupon(coupon.getId());
			}
			companyDAO.deleteCompany(companyID);
		} catch (CompanyDoesntExistException e) {
			return e.getMessage();
		} catch (CustomerDoesntExistException e1) {
			return e1.getMessage();

		}
//			return e.getMessage();
//		}
		return "Company With ID-'" + companyID + "' Has been Successfully deleted!";
	}

	/**
	 * This method if called will return all the companies in the database
	 * 
	 * @return
	 */
	public List<Company> getAllCompanies() {
		return companyDAO.getAllcompanies();
	}

	/**
	 * This method if called will return a company by company id
	 * 
	 * @param companyID
	 * @return
	 * @throws CompanyDoesntExistException
	 */
	public Company getCompanyById(int companyID) throws CompanyDoesntExistException {
		try {
			return companyDAO.getOneCompany(companyID);
		} catch (CompanyDoesntExistException e) {
			throw new CompanyDoesntExistException(companyID);
		}

	}

	/**
	 * This method if called will add a new customer to the database
	 * 
	 * @param customer
	 * @return
	 */
	public String addNewCustomer(Customer customer) {
		try {
			customerDAO.addCustomer(customer);
		} catch (CustomerAlreadyExistsException e) {
			return e.getMessage();
		}
		return "Customer with email-'" + customer.getEmail() + "' Have Been Successfully added!";
	}

	/**
	 * This method if called will update a existing customer
	 * 
	 * @param customer
	 * @return
	 */
	public String updateCustomer(Customer customer) {
		try {
			customerDAO.updateCustomer(customer);
		} catch (CustomerDoesntExistException e) {
			return e.getMessage();
		}
		return "Customer with ID-'" + customer.getId() + "' Have been Successfully updated!";
	}

	/**
	 * This method if called will delete a customer by id
	 * 
	 * @param customerID
	 * @return
	 */
	public String deleteCustomer(int customerID) {
		try {
			customerDAO.deleteCustomer(customerID);
		} catch (CustomerDoesntExistException e) {
			return e.getMessage();
		}
		return "Customer with ID-'" + customerID + "' Have been Successfully Deleted!";
	}

	/**
	 * This method if called will return all of the customers in the database
	 * 
	 * @return
	 */
	public List<Customer> getAllCustomers() {
		return customerDAO.getAllCustomers();
	}

	/**
	 * This method if called will return a customer by id
	 * 
	 * @param customerID
	 * @return
	 * @throws CustomerDoesntExistException
	 */
	public Customer getCustomerById(int customerID) throws CustomerDoesntExistException {
		try {

			return customerDAO.getOneCustomer(customerID);
		} catch (Exception e) {
			throw new CustomerDoesntExistException(customerID);
		}
	}
}
