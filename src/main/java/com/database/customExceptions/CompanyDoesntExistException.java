package com.database.customExceptions;

import com.database.beans.Company;

public class CompanyDoesntExistException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int companyID;
	private Company company;

	/**
	 * This is a constructor that accepts the companyID
	 * 
	 * @param companyID
	 */
	public CompanyDoesntExistException(int companyID) {
		this.companyID = companyID;
	}

	/**
	 * This is a constructor that accepts the company object
	 * 
	 * @param company
	 */
	public CompanyDoesntExistException(Company company) {
		super();
		this.company = company;
	}

	/**
	 * This is an empty constructor
	 */
	public CompanyDoesntExistException() {

	}

	/**
	 * This will return the company with id as identification
	 */
	@Override
	public String getMessage() {

		return "Company with ID-'" + companyID + "' doesn't exist!";
	}

	/**
	 * This will get the message with no identification.
	 * 
	 * 
	 * @return
	 */
	public String emptyMessage() {
		return "Company doesn't exist!";
	}

	/**
	 * This will return the message with company name as identification
	 * 
	 * @return
	 */
	public String companyName() {
		return "Company '" + company.getName() + "' doesn't exist!";
	}
}
