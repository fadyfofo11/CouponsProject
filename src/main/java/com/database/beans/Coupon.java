package com.database.beans;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "coupons")
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private Company company;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "category_id")
	private CategoryTypes categoryID;
	@Column(unique = true)
	private String title;
	@Column
	private String description;
	@DateTimeFormat
	@Column(name = "start_date")
	private Date startDate;
	@DateTimeFormat
	@Column(name = "end_date")
	private Date endDate;
	@Column
	private int amount;
	@Column
	private double price;
	@Column
	private String image;

	/**
	 * This constructor comes with the coupon id
	 * 
	 * @param id
	 * @param company_id
	 * @param category_id
	 * @param title
	 * @param description
	 * @param start_date
	 * @param end_date
	 * @param amount
	 * @param price
	 * @param image
	 */
	public Coupon(int id, Company company_id, CategoryTypes category_id, String title, String description,
			Date start_date, Date end_date, int amount, double price, String image) {
		super();
		this.id = id;
		this.company = company_id;
		this.categoryID = category_id;
		this.title = title;
		this.description = description;
		this.startDate = start_date;
		this.endDate = end_date;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	public Coupon(Company company_id, CategoryTypes category_id, String title, String description, Date start_date,
			Date end_date, int amount, double price, String image) {
		super();
		this.company = company_id;
		this.categoryID = category_id;
		this.title = title;
		this.description = description;
		this.startDate = start_date;
		this.endDate = end_date;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	public Coupon(CategoryTypes categoryID, String title, String description, Date startDate, Date endDate, int amount,
			double price, String image) {
		super();
		this.categoryID = categoryID;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	public Coupon() {
		// this is used by Hibernate
	}

	public Company getCompany_id() {
		return company;
	}

	public void setCompany_id(Company company_id) {
		this.company = company_id;
	}

	public CategoryTypes getCategory_id() {
		return categoryID;
	}

	public void setCategory_id(CategoryTypes category_id) {
		this.categoryID = category_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStart_date() {
		return startDate;
	}

	public void setStart_date(Date start_date) {
		this.startDate = start_date;
	}

	public Date getEnd_date() {
		return endDate;
	}

	public void setEnd_date(Date end_date) {
		this.endDate = end_date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}

	/**
	 * Call this method to print the coupon details on the screen
	 */
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", company_id=" + company + ", category_id=" + categoryID + ", title=" + title
				+ ", description=" + description + ", start_date=" + startDate + ", end_date=" + endDate + ", amount="
				+ amount + ", price=" + price + ", image=" + image + "]";
	}

}
