package com.database.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.database.beans.CategoryTypes;
import com.database.beans.Company;
import com.database.beans.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
	List<Coupon> findByCompany(Company company);

	List<Coupon> findByCategoryID(CategoryTypes categoryID);

	List<Coupon> findByCompanyAndPriceLessThan(Company company, double price);

	List<Coupon> removeByEndDateBefore(Date endDate);

	List<Coupon> findByEndDateBefore(Date endDate);

	List<Coupon> findByCategoryIDAndId(CategoryTypes category, int id);

	Coupon findByIdAndCompany(int couponID, Company company);

	List<Coupon> findByPriceLessThan(double price);

}
