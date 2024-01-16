package com.database.db.dbdao;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.database.beans.CategoryTypes;
import com.database.beans.Company;
import com.database.beans.Coupon;
import com.database.beans.Customer;
import com.database.beans.Purchase;
import com.database.customExceptions.CouponAlreadyExistsException;
import com.database.customExceptions.CouponDoesntExistException;
import com.database.customExceptions.CouponHasAlreadyBeenPurchasedException;
import com.database.customExceptions.CustomerDoesntExistException;
import com.database.db.dao.CouponsDAO;
import com.database.repositories.CouponRepository;
import com.database.repositories.CustomerRepository;
import com.database.repositories.PurchasesRepository;

@Component
public class CouponDBDAO implements CouponsDAO {

	@Autowired
	private CouponRepository couponrepo;
	@Autowired
	private CustomerRepository customerrepo;
	@Autowired
	private PurchasesRepository purchrepo;

	/**
	 * This will add a coupon to the database
	 * 
	 * @throws CouponAlreadyExistsException
	 */
	@Override
	public String addCoupon(Coupon coupon) throws CouponAlreadyExistsException {
		if (couponrepo.existsById(coupon.getId()))
			throw new CouponAlreadyExistsException(coupon);
		try {
			couponrepo.save(coupon);
			return "Coupon with id " + coupon.getId() + " has been Succsessfully added!";
		} catch (Exception e) {
			throw new CouponAlreadyExistsException(coupon);
		}
	}

	/**
	 * This will update a coupon from the database if the coupon doesn't exist it
	 * will throw an exception
	 */
	@Override
	public String updateCoupon(Coupon coupon) throws CouponDoesntExistException {
		if (couponrepo.existsById(coupon.getId())) {
			couponrepo.save(coupon);
			return "Coupon with id " + coupon.getId() + " have been updated Succsesfully!";
		} else
			throw new CouponDoesntExistException(coupon.getId());

	}

	/**
	 * This will delete a coupon from the database if the coupon doesn't exist it
	 * will throw an exception
	 */
	@Override
	public int deleteCoupon(int couponID) throws CouponDoesntExistException {
		if (couponrepo.existsById(couponID)) {
			try {
				couponrepo.deleteById(couponID);
			} catch (Exception e) {
				throw new CouponDoesntExistException(couponID);
			}
		}
		return couponID;

	}

	/**
	 * This will return all of the coupons in the database
	 */
	@Override
	public List<Coupon> getAllCoupons() {
		return couponrepo.findAll();
	}

	/**
	 * This will find one coupon by id and return null if the coupon doesn't exist
	 * 
	 * @throws CouponDoesntExistException
	 */
	@Override
	public Coupon getOneCoupon(int couponID) throws CouponDoesntExistException {
		if (!couponrepo.existsById(couponID))
			throw new CouponDoesntExistException(couponID);
		return couponrepo.findById(couponID).orElse(null);
	}

	/**
	 * This will add a coupon purchase history to the user
	 * 
	 * @throws CouponDoesntExistException
	 * @throws CustomerDoesntExistException
	 */
	@Override
	public String addCouponPurchase(int customerID, int couponID)
			throws CouponHasAlreadyBeenPurchasedException, CouponDoesntExistException, CustomerDoesntExistException {
		Customer customer = null;
		Coupon coupon = null;
		try {
			customer = customerrepo.findById(customerID).orElse(null);
		} catch (NoSuchElementException e) {
			throw new CustomerDoesntExistException(customerID);
		}
		try {
			coupon = couponrepo.findById(couponID).orElse(null);
			customer.addCouponToSet(coupon);
		} catch (NoSuchElementException e) {
			throw new CouponDoesntExistException(couponID);
		}
		customerrepo.save(customer);
		return "Coupon with title-'" + coupon.getTitle() + "' have been successfully purchased!";
	}

	/**
	 * This will delete a customer purchase history
	 */
	@Override
	public String deleteCouponPurchase(int couponID) throws CustomerDoesntExistException {
		purchrepo.deleteByCouponID(couponID);
		return "Purchase with couponID-" + couponID + " have been deleted successfully!";

	}

	/**
	 * This will return all the coupons of a specific company
	 * 
	 * @param companyID
	 * @return
	 */
	@Override
	public List<Coupon> getCouponsByCompanyId(Company company) {
		return couponrepo.findByCompany(company);
	}

	/**
	 * This will return all coupons by a specific category
	 * 
	 * @param category
	 * @return
	 */
	@Override
	public List<Coupon> getCouponsByCategory(int category) {
		return null;
	}

	/**
	 * This will return all the coupons by company id and the coupons where there
	 * max price is less than max price
	 */
	@Override
	public List<Coupon> getCouponsByMaxPrice(Company company, double maxPrice) {
		return couponrepo.findByCompanyAndPriceLessThan(company, maxPrice);
	}

	/**
	 * This will find coupons by category
	 */
	@Override
	public List<Coupon> getCouponsByCategory(CategoryTypes category) {
		return couponrepo.findByCategoryID(category);
	}

	/*
	 * This will find a coupon by company and coupon id
	 */
	@Override
	public Coupon getCouponByCompanyAndCouponID(int couponID, Company company) throws CouponDoesntExistException {
		try {
			return couponrepo.findByIdAndCompany(couponID, company);
		} catch (Exception e) {
			throw new CouponDoesntExistException(couponID);
		}
	}

	public List<Coupon> getCouponsByMaxPriceLessThan(double price) throws CouponDoesntExistException {
		try {
			return couponrepo.findByPriceLessThan(price);
		} catch (Exception e) {
			throw new CouponDoesntExistException();
		}

	}

	@Override
	public String deleteCouponPurchase(Purchase purchase) throws CustomerDoesntExistException {
		try {
			purchrepo.delete(purchase);
		} catch (Exception e) {
			throw new CustomerDoesntExistException(purchase.getCustomerID());
		}
		return "Successfully deleted!";
	}

	@Override
	public String deleteCouponPurchase(int customerID, int couponID) throws CustomerDoesntExistException {
		// TODO Auto-generated method stub
		return null;
	}

}
