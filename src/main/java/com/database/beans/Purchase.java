package com.database.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(value = PurchaseID.class)
@Table(name = "customer_coupons")
public class Purchase {

	@Id
	@Column(name = "coupons_id")
	private int couponID;
	@Id
	@Column(name = "customer_id")
	private int customerID;

	public Purchase() {
		// this is for Hibernate
	}

	public Purchase(int customerID, int couponID) {
		super();
		this.customerID = customerID;
		this.couponID = couponID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getCouponID() {
		return couponID;
	}

	public void setCouponID(int couponID) {
		this.couponID = couponID;
	}

	@Override
	public String toString() {
		return "Purchase [customerID=" + customerID + ", couponID=" + couponID + "]";
	}

}
