package com.database.customExceptions;

import com.database.beans.CategoryTypes;
import com.database.beans.Coupon;

public class CouponDoesntExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int couponID;
	private Coupon coupon;
	private CategoryTypes category;

	public CouponDoesntExistException(int couponID) {
		this.couponID = couponID;
	}

	public CouponDoesntExistException(Coupon coupon) {
		super();
		this.coupon = coupon;
	}

	public CouponDoesntExistException(CategoryTypes category) {
		super();
		this.category = category;
	}

	public CouponDoesntExistException() {

	}

	/**
	 * This will return a message with coupon id
	 */
	@Override
	public String getMessage() {
		return "Coupon with ID-'" + couponID + "' doesn't exist!";
	}

	/**
	 * This will return a message with coupon title
	 * 
	 * @return
	 */
	public String getCouponMessage() {
		return "Coupon '" + coupon.getTitle() + "' doesn't exist!";
	}

	/**
	 * This will return a message without coupon id or title
	 * 
	 * @return
	 */
	public String getCouponEmptyMessage() {
		return "Coupon doesn't exist!";
	}

	public String getCategoryErrorMessage() {
		return "Coupons by Category-'" + category + "' doesn't exist!";
	}
}
