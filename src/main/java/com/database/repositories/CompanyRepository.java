package com.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.database.beans.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
	Company findByEmailAndPassword(String email, String password);

	boolean existsByEmailAndPassword(String email, String password);

	Company findCompanyByEmailAndPassword(String email, String password);
}
