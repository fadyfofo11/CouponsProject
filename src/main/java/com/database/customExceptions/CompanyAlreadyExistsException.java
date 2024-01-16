package com.database.customExceptions;

import com.database.beans.Company;

public class CompanyAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int companyID;
	private Company company;

	public CompanyAlreadyExistsException() {
		// TODO Auto-generated constructor stub
	}

	public CompanyAlreadyExistsException(int companyID) {
		super();
		this.companyID = companyID;
	}

	public CompanyAlreadyExistsException(Company company) {
		super();
		this.company = company;
	}

	public CompanyAlreadyExistsException(String message) {
		super(message);
	}

	/**
	 * This returns the company exception with company name
	 */
	@Override
	public String getMessage() {
		return "Company '" + company.getName() + "' already exists!";
	}

	/**
	 * This returns with company id
	 * 
	 * @return
	 */
	public String getCompany() {
		return "Company wtih ID-'" + companyID + "' already exits!";
	}

	/**
	 * Get a message that is empty
	 * 
	 * @return
	 */
	public String getMessageEmpty() {
		return "This company already exists!";
	}
}
