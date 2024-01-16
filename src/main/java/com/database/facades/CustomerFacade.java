package com.database.facades;

import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.database.beans.CategoryTypes;
import com.database.beans.Coupon;
import com.database.beans.Customer;
import com.database.customExceptions.CouponDoesntExistException;
import com.database.customExceptions.CouponHasAlreadyBeenPurchasedException;
import com.database.customExceptions.CustomerDoesntExistException;

@Service
public class CustomerFacade extends ClientFacade {

	private Customer customerLogin = null;

	public CustomerFacade() {
	}

	/**
	 * This checks if the customer exists or not if exists it will return true if
	 * not it will return false
	 */
	@Override
	public boolean login(String email, String password) {
		if (customerDAO.isCustomerExists(email, password)) {
			this.customerLogin = customerDAO.getCustomerByLogin(email, password);
			return true;
		}
		return false;
	}

	/**
	 * This will add a coupon purchase to the customer This checks weather the
	 * coupon amount is 0 if zero it won't purchase or if the end date if the end
	 * date comes before now
	 * 
	 * @param customerID
	 * @param couponID
	 * @return
	 * @throws CouponHasAlreadyBeenPurchasedException
	 */
	public String purchaseCoupon(int couponID) throws CouponHasAlreadyBeenPurchasedException {
		int customerID = customerLogin.getId();
		// this gets a coupon by id
		Coupon co;
		Customer cus = customerDAO.getOneCustomer(customerID);
		for (Coupon coupon : cus.getCoupons()) {
			if (coupon.getId() == couponID)
				return "Coupon with ID-'" + couponID + "' have already been purchased!";
		}
		try {
			co = couponDAO.getOneCoupon(couponID);
		} catch (CouponDoesntExistException e1) {
			return e1.getMessage();
		}

		// this create a calendar time for this moment
		Calendar cal = Calendar.getInstance();
		try {
			// this checks if the coupon amount or end date are invalid
			if (!((co.getAmount() <= 0) || (co.getEnd_date().before(new Date(cal.getTimeInMillis()))))) {
				couponDAO.addCouponPurchase(customerID, couponID);
				co.setAmount(co.getAmount() - 1);
				couponDAO.updateCoupon(co);
			}
		} catch (CouponHasAlreadyBeenPurchasedException e) {
			return e.getMessage();
		} catch (CouponDoesntExistException e) {
			return e.getMessage();
		} catch (CustomerDoesntExistException e) {
			return e.getMessage();
		}
		return "Coupon with ID-" + couponID + "' Have been Successfully Purchased!";
	}

	/**
	 * This will return all of the customers coupons
	 * 
	 * @return
	 */
	public List<Coupon> getAllCustomerCoupons() throws CouponDoesntExistException {
		List<Coupon> coupons = new LinkedList<Coupon>();
		Customer customer = customerDAO.getOneCustomer(customerLogin.getId());
		for (Coupon coupon : customer.getCoupons()) {
			coupons.add(coupon);
		}
		return coupons;
	}

	/**
	 * This will get customer coupons by category This will return a list of coupons
	 * by category for the customer
	 * 
	 * @param category
	 * @return
	 */
	public List<Coupon> getAllCustomerCouponsByCategory(CategoryTypes category) throws CouponDoesntExistException {
		List<Coupon> coupons = new LinkedList<Coupon>();
		Customer customer = customerDAO.getOneCustomer(customerLogin.getId());
		for (Coupon coupon : customer.getCoupons()) {
			if (coupon.getCategory_id().equals(category))
				coupons.add(coupon);
		}
		return coupons;
	}

	/**
	 * This will return all of the customer coupons by max price
	 * 
	 * @param maxPrice
	 * @return
	 */
	public List<Coupon> getAllCustomerCouponsByMaxPrice(double maxPrice) throws CouponDoesntExistException {
		List<Coupon> coupons = new LinkedList<Coupon>();
		Customer customer = customerDAO.getOneCustomer(customerLogin.getId());
		for (Coupon coupon : customer.getCoupons()) {
			if (coupon.getPrice() <= maxPrice)
				coupons.add(coupon);
		}
		return coupons;

	}

	/**
	 * This will return the customers information
	 * 
	 * @return
	 */
	public Customer getCustomerInformation() {
		return customerLogin;
	}

	public List<Coupon> getAllCoupons() throws CouponDoesntExistException {
		try {
			return couponDAO.getAllCoupons();
		} catch (Exception e) {
			throw new CouponDoesntExistException();
		}
	}

	public List<Coupon> getAllCouponsByCategory(CategoryTypes category) throws CouponDoesntExistException {
		try {
			return couponDAO.getCouponsByCategory(category);
		} catch (Exception e) {
			throw new CouponDoesntExistException();
		}
	}

	public List<Coupon> getAllCouponsByMaxPrice(double price) throws CouponDoesntExistException {
		try {
			return couponDAO.getCouponsByMaxPriceLessThan(price);
		} catch (CouponDoesntExistException e) {
			throw new CouponDoesntExistException();
		}
	}

}
