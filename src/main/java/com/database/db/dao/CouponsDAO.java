package com.database.db.dao;

import java.util.List;

import com.database.beans.CategoryTypes;
import com.database.beans.Company;
import com.database.beans.Coupon;
import com.database.beans.Purchase;
import com.database.customExceptions.CouponAlreadyExistsException;
import com.database.customExceptions.CouponDoesntExistException;
import com.database.customExceptions.CouponHasAlreadyBeenPurchasedException;
import com.database.customExceptions.CustomerDoesntExistException;

public interface CouponsDAO {
	/**
	 * This will add a new coupon to the database and return the id of the coupon
	 * that has been added
	 * 
	 * @param coupon
	 * @return
	 * @throws CouponAlreadyExistsException
	 */
	String addCoupon(Coupon coupon) throws CouponAlreadyExistsException;

	/**
	 * This updates a coupon and returns it's id
	 * 
	 * @param coupon
	 * @return
	 * @throws CouponDoesntExistException
	 */
	String updateCoupon(Coupon coupon) throws CouponDoesntExistException;

	/**
	 * This will delete a coupon and return it's id
	 * 
	 * @param couponID
	 * @return
	 * @throws CouponDoesntExistException
	 */
	int deleteCoupon(int couponID) throws CouponDoesntExistException;

	/**
	 * This will return all of the coupons in the database
	 * 
	 * @return
	 */
	List<Coupon> getAllCoupons();

	/**
	 * This will return one coupon by couponID
	 * 
	 * @param couponID
	 * @return
	 * @throws CouponDoesntExistException
	 */
	Coupon getOneCoupon(int couponID) throws CouponDoesntExistException;

	/**
	 * This will add a coupon purchase by customer id and coupon id
	 * 
	 * @param customerID
	 * @param couponID
	 * @return
	 * @throws CouponHasAlreadyBeenPurchasedException
	 * @throws CouponDoesntExistException
	 * @throws CustomerDoesntExistException
	 */
	String addCouponPurchase(int customerID, int couponID)
			throws CouponHasAlreadyBeenPurchasedException, CouponDoesntExistException, CustomerDoesntExistException;

	/**
	 * This will delete a coupon purchase history by customer id and coupon id
	 * 
	 * @param customerID
	 * @param couponID
	 * @return
	 * @throws CustomerDoesntExistException
	 */
	String deleteCouponPurchase(int customerID, int couponID) throws CustomerDoesntExistException;

	/**
	 * This method will return all coupons by company id
	 * 
	 * @param companyID
	 * @return
	 */
	List<Coupon> getCouponsByCompanyId(Company company);

	/**
	 * This will return all the coupons by category
	 * 
	 * @param category
	 * @return
	 */
	List<Coupon> getCouponsByCategory(CategoryTypes category);

	/**
	 * This will return all the coupons by max price
	 * 
	 * @param companyID
	 * @param maxPrice
	 * @return
	 */
	List<Coupon> getCouponsByMaxPrice(Company company, double maxPrice);

	List<Coupon> getCouponsByCategory(int category);

	Coupon getCouponByCompanyAndCouponID(int couponID, Company company) throws CouponDoesntExistException;

	List<Coupon> getCouponsByMaxPriceLessThan(double price) throws CouponDoesntExistException;

	String deleteCouponPurchase(int couponID) throws CustomerDoesntExistException;

	String deleteCouponPurchase(Purchase purchase) throws CustomerDoesntExistException;
}
