package com.database.customExceptions;

import com.database.beans.Coupon;
import com.database.beans.Customer;

public class CouponHasAlreadyBeenPurchasedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int couponID;
	private int customerID;
	private Coupon coupon;
	private Customer customer;

	public CouponHasAlreadyBeenPurchasedException(int couponID) {
		this.couponID = couponID;
	}

	public CouponHasAlreadyBeenPurchasedException() {

	}

	@Override
	public String getMessage() {
		return "Coupon with ID-'" + couponID
				+ "' has already been Purchased By the User You can't purchase more than one of this coupon!!";
	}
}
