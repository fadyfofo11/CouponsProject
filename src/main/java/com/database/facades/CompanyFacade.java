package com.database.facades;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.database.beans.CategoryTypes;
import com.database.beans.Company;
import com.database.beans.Coupon;
import com.database.customExceptions.CompanyDoesntExistException;
import com.database.customExceptions.CouponAlreadyExistsException;
import com.database.customExceptions.CouponDoesntExistException;
import com.database.repositories.CompanyRepository;

@Service
public class CompanyFacade extends ClientFacade {

	@Autowired
	private CompanyRepository comprepo;

	private Company companyLogin = null;

	/**
	 * This method checks weather a company exists or not
	 */
	@Override
	public boolean login(String email, String password) {
		if (companyDAO.isCompanyExists(email, password)) {
			this.companyLogin = comprepo.findCompanyByEmailAndPassword(email, password);
			return true;
		}
		return false;
	}

	/**
	 * This method if called will add a new coupon to the database
	 * 
	 * @param coupon
	 * @return
	 * @throws CompanyDoesntExistException
	 */
	public String addNewCoupon(Coupon coupon) throws CouponAlreadyExistsException, CompanyDoesntExistException {
		coupon.setCompany_id(companyLogin);
		try {
			couponDAO.addCoupon(coupon);
		} catch (CouponAlreadyExistsException e) {
			return e.getMessage();
		}
		return "Coupon '" + coupon.getTitle() + "' Has Been Successfully Added!";
	}

	/**
	 * This method if called will update an existing coupon from the database
	 * 
	 * @param coupon
	 * @return
	 * @throws CompanyDoesntExistException
	 */
	public String updateCoupon(Coupon coupon) throws CompanyDoesntExistException {
		try {
			coupon.setCompany_id(comprepo.findById(companyLogin.getId()).orElse(null));
			couponDAO.updateCoupon(coupon);
		} catch (CouponDoesntExistException e) {
			return e.getMessage();
		} catch (NoSuchElementException e) {
			throw new CompanyDoesntExistException(companyLogin);
		}
		return "Coupon '" + coupon.getTitle() + "' Has Been Successfully Updated!";
	}

	/**
	 * This method if called will delete a coupon from the database
	 * 
	 * @param couponID
	 * @return
	 */
	public String deleteCoupon(int couponID) {

		try {
			Coupon co = couponDAO.getCouponByCompanyAndCouponID(couponID, companyLogin);
			if (!co.getCompany_id().equals(companyLogin)) {
				throw new CouponDoesntExistException(couponID);
			}
			couponDAO.deleteCoupon(couponID);
			return "Coupon with ID-'" + couponID + "' Has been Successfully Deleted!";
		} catch (CouponDoesntExistException e) {
			return e.getMessage();
		}
	}

	/**
	 * This method if called will return the coupons by company id
	 * 
	 * @param companyID
	 * @return
	 */
	public List<Coupon> getAllCompanyCoupons() {
		return couponDAO.getCouponsByCompanyId(companyLogin);

	}

	/**
	 * This will return all the coupons by a specific category
	 * 
	 * @param category
	 * @return
	 */
	public List<Coupon> getAllCouponsByCategory(CategoryTypes category) {
		return couponDAO.getCouponsByCategory(category);
	}

	/**
	 * This will return all coupons by maxprice
	 * 
	 * @param companyID
	 * @param maxPrice
	 * @return
	 */
	public List<Coupon> getCouponsByMaxPrice(double maxPrice) {
		return couponDAO.getCouponsByMaxPrice(companyLogin, maxPrice);
	}

	/**
	 * This will return one company the company information
	 * 
	 * @param companyID
	 * @return
	 * @throws CompanyDoesntExistException
	 */
	public Company getCompanyInformation() throws CompanyDoesntExistException {
		try {
			return companyDAO.getOneCompany(companyLogin.getId());
		} catch (CompanyDoesntExistException e) {
			throw new CompanyDoesntExistException(companyLogin.getId());
		}
	}

	public Coupon getCouponByCompanyAndCoupon(int couponID) throws CouponDoesntExistException {
		try {
			return couponDAO.getCouponByCompanyAndCouponID(couponID, companyLogin);
		} catch (CouponDoesntExistException e) {
			throw new CouponDoesntExistException(couponID);
		}

	}

}
