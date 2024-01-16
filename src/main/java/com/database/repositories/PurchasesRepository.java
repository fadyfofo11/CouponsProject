package com.database.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.database.beans.Purchase;

public interface PurchasesRepository extends JpaRepository<Purchase, Long> {

	String deleteByCouponID(int couponID);

	String deleteByCustomerID(int customerID);

	List<Purchase> findByCustomerID(int customerID);

	List<Purchase> findByCouponID(int couponID);

	Purchase findByCustomerIDAndCouponID(int customerID, int couponID);

	String deleteByCustomerIDAndCouponID(int customerID, int couponID);
}
