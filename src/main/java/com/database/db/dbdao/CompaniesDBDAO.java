package com.database.db.dbdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.database.beans.Company;
//import com.database.beans.Coupon;
import com.database.customExceptions.CompanyAlreadyExistsException;
import com.database.customExceptions.CompanyDoesntExistException;
import com.database.db.dao.CompaniesDAO;
import com.database.repositories.CompanyRepository;
//import com.database.repositories.CouponRepository;

@Component
public class CompaniesDBDAO implements CompaniesDAO {

	@Autowired
	private CompanyRepository companyrepo;
//	@Autowired
//	private CouponRepository couponrepo;

	/**
	 * This checks if a company exists in the database
	 */
	@Override
	public boolean isCompanyExists(String email, String password) {
		Company company = companyrepo.findByEmailAndPassword(email, password);
		if (company.getEmail().equals(email) && company.getPassword().equals(password))
			return true;
		else
			return false;
	}

	/**
	 * This method saves a company to database
	 * 
	 * @throws CompanyAlreadyExistsException
	 */
	@Override
	public int addCompany(Company company) throws CompanyAlreadyExistsException {
		try {
			if (!companyrepo.existsById(company.getId())) {
				companyrepo.save(company);
			}
		} catch (Exception e) {
			throw new CompanyAlreadyExistsException(company);
		}
		return company.getId();

	}

	/**
	 * This checks whether a company already exists or not if it exists it will
	 * update it if not it will throw an exception
	 * 
	 * @throws CompanyDoesntExistException
	 */
	@Override
	public int updateCompany(Company company) throws CompanyDoesntExistException {
		try {
			if (companyrepo.existsById(company.getId())) {
				companyrepo.save(company);
			}
		} catch (Exception e) {
			throw new CompanyDoesntExistException(company);
		}
		return company.getId();
	}

	/**
	 * This deletes a company from the database
	 * 
	 * @throws CompanyDoesntExistException
	 */
	@Override
	public int deleteCompany(int companyID) throws CompanyDoesntExistException {
//			throw new CompanyDoesntExistException(companyID);
		try {
			if (companyrepo.existsById(companyID)) {
				companyrepo.deleteById(companyID);
			}
			return companyID;
		} catch (Exception e) {
			throw new CompanyDoesntExistException(companyID);
		}
	}

	/**
	 * This gets all companies from the database
	 */
	@Override
	public List<Company> getAllcompanies() {
		return companyrepo.findAll();
	}

	/**
	 * This will get one company by id and return it if it isn't found it will
	 * return null
	 * 
	 * @throws CompanyDoesntExistException
	 */
	@Override
	public Company getOneCompany(int companyID) throws CompanyDoesntExistException {
		if (!companyrepo.existsById(companyID))
			throw new CompanyDoesntExistException(companyID);
		return companyrepo.findById(companyID).orElse(null);
	}

}
