package com.database.customExceptions;

import com.database.beans.Coupon;

public class CouponAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int couponID;
	private Coupon coupon;

	public CouponAlreadyExistsException() {

	}

	public CouponAlreadyExistsException(Coupon coupon) {
		this.coupon = coupon;
	}

	public CouponAlreadyExistsException(int couponID) {
		this.couponID = couponID;
	}

	/**
	 * This coupon already exists exception with the coupon title
	 */
	@Override
	public String getMessage() {
		return "Coupon '" + coupon.getTitle() + "' already exists!";
	}

	/**
	 * This coupon already exits by couponID exception
	 * 
	 * @return
	 */
	public String getCouponID() {
		return "Coupon with ID-'" + couponID + "' already exists!";
	}
}
