package com.database.db.dao;

import java.util.List;

import com.database.beans.Company;
import com.database.customExceptions.CompanyAlreadyExistsException;
import com.database.customExceptions.CompanyDoesntExistException;

public interface CompaniesDAO {

	/**
	 * This checks if the customer exists or not and returns true if exists or false
	 * if not
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	boolean isCompanyExists(String email, String password);

	/**
	 * 
	 * This adds a new company nad returns the company id of the added company
	 * 
	 * @param company
	 * @return
	 * @throws CompanyAlreadyExistsException
	 */
	int addCompany(Company company) throws CompanyAlreadyExistsException;

	/**
	 * This updates a company and returns the company id of the updated company
	 * 
	 * @param company
	 * @return
	 * @throws CompanyDoesntExistException
	 */
	int updateCompany(Company company) throws CompanyDoesntExistException;

	/**
	 * This deletes a company and returns the id of the deleted company
	 * 
	 * @param companyID
	 * @return
	 * @throws CompanyDoesntExistException
	 */
	int deleteCompany(int companyID) throws CompanyDoesntExistException;

	/**
	 * This gets all of the companies in the database
	 * 
	 * @return
	 */
	List<Company> getAllcompanies();

	/**
	 * This returns one company by company id
	 * 
	 * @param companyID
	 * @return
	 * @throws CompanyDoesntExistException
	 */
	Company getOneCompany(int companyID) throws CompanyDoesntExistException;

}
