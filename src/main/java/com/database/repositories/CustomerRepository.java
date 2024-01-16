package com.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.database.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	boolean findCustomerByEmailAndPassword(String email, String password);

	Customer findByEmailAndPassword(String email, String password);
}
