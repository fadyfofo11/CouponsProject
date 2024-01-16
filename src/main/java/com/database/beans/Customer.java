package com.database.beans;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(unique = true)
	private String email;
	@Column
	private String password;
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Coupon> coupons;

	public Customer() {

	}

	/**
	 * This is a constructor with all of the variables in it
	 * 
	 * @param id
	 * @param first_name
	 * @param last_name
	 * @param email
	 * @param password
	 * @param coupon
	 */
	public Customer(int id, String first_name, String last_name, String email, String password) {
		super();
		this.id = id;
		this.firstName = first_name;
		this.lastName = last_name;
		this.email = email;
		this.password = password;
//		this.coupon = coupon;
	}

	/**
	 * 
	 * This is a constructor without the id and coupon variables
	 * 
	 * @param first_name
	 * @param last_name
	 * @param email
	 * @param password
	 */
	public Customer(String first_name, String last_name, String email, String password) {
		super();
		this.firstName = first_name;
		this.lastName = last_name;
		this.email = email;
		this.password = password;
	}

	public String getFirst_name() {
		return firstName;
	}

	public void setFirst_name(String first_name) {
		this.firstName = first_name;
	}

	public String getLast_name() {
		return lastName;
	}

	public void setLast_name(String last_name) {
		this.lastName = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public Set<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(Set<Coupon> coupons) {
		this.coupons = coupons;
	}

	public void addCouponToSet(Coupon coupon) {
		coupons.add(coupon);
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", first_name=" + firstName + ", last_name=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}

}
