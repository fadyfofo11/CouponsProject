package com.database.loginManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import com.database.facades.AdminFacade;
import com.database.facades.ClientFacade;
import com.database.facades.ClientType;
import com.database.facades.CompanyFacade;
import com.database.facades.CustomerFacade;

@Component
public class LoginManager {

	@Autowired
	ConfigurableApplicationContext ctx;

	public LoginManager() {

	}

	/**
	 * This returns a specific type
	 * 
	 * @param email
	 * @param password
	 * @param categoryType
	 * @return
	 */
	public ClientFacade login(String email, String password, ClientType clientType) {
		CustomerFacade customerFacade = ctx.getBean(CustomerFacade.class);
		CompanyFacade companyFacade = ctx.getBean(CompanyFacade.class);
		AdminFacade adminFacade = ctx.getBean(AdminFacade.class);

		switch (clientType) {
		case Admin:
			if (adminFacade.login(email, password))
				return adminFacade;
			else
				return null;
		case Company:
			if (companyFacade.login(email, password))
				return companyFacade;
			else
				return null;
		case Customer:
			if (customerFacade.login(email, password))
				return customerFacade;
			else
				return null;
		default:
			return null;
		}
	}
}
