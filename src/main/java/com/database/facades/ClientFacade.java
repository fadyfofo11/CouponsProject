package com.database.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.database.db.dao.CompaniesDAO;
import com.database.db.dao.CouponsDAO;
import com.database.db.dao.CustomersDAO;

@Component
public abstract class ClientFacade {

	@Autowired
	protected CompaniesDAO companyDAO;
	@Autowired
	protected CustomersDAO customerDAO;
	@Autowired
	protected CouponsDAO couponDAO;

	abstract boolean login(String email, String password);
}
