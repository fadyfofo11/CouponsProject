package com.database.web.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.database.beans.Company;
import com.database.beans.Customer;
import com.database.facades.AdminFacade;
import com.database.web.other.Session;

import io.swagger.models.Response;

@RestController
@RequestMapping(name = "admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	@Autowired
	Map<String, Session> sessionsMap;

	public AdminController() {

	}

	private void isTimeOut(Session session, String token) {
		if (session != null) {
			long timeSession = session.getLastAccessed();
			long timeNow = System.currentTimeMillis();
			if ((timeNow - timeSession) > 1000 * 60 * 30) {
				sessionsMap.remove(token);
				session = null;
			}
		}
	}

	/**
	 * This method is called by a client to add a new company
	 * 
	 * @param token
	 * @param company
	 * @return
	 */
	@PostMapping("/company/{token}")
	public ResponseEntity<?> addCompany(@PathVariable String token, @RequestBody Company company) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			AdminFacade service = (AdminFacade) session.getService();
			try {
				String compMessage = service.addNewCompany(company);
				return ResponseEntity.ok(compMessage);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("Company already exists!");
			}
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login");
	}

	/**
	 * This method is called by the client to update a company
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param token
	 * @param company
	 * @return
	 */
	@PutMapping("/company/update/{token}")
	public ResponseEntity<?> updateCompany(@PathVariable String token, @RequestBody Company company) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			AdminFacade service = (AdminFacade) session.getService();
			try {
				String compMessage = service.updateCompany(company);
				return ResponseEntity.ok(compMessage);
			} catch (Exception e) {

				return ResponseEntity.badRequest().body("Failed to update company!");
			}

		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized login");
	}

	/**
	 * This method if called by the client it will delete a company
	 * 
	 * @param companyId
	 * @param token
	 * @return
	 */
	@DeleteMapping("/company/delete/{token}/{id}")
	public ResponseEntity<?> deleteCompany(@PathVariable String token, @PathVariable int id) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			AdminFacade service = (AdminFacade) session.getService();
			try {
				String compMessage = service.deleteCompany(id);
				return ResponseEntity.ok(compMessage);
			} catch (Exception e) {
				return ResponseEntity.badRequest()
						.body("failed to delete the company eather because it doesn't exist or something else!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login!");
	}

	/**
	 * This method if called by the client it will return all of the companies in
	 * the database
	 * 
	 * @param token
	 * @return
	 */
	@GetMapping("/company/getallcompanies/{token}")
	public ResponseEntity<?> getAllCompanies(@PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			AdminFacade service = (AdminFacade) session.getService();
			try {
				List<Company> companies = service.getAllCompanies();
				return ResponseEntity.ok(companies);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("Failed to get all of the companies!");

			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login");
	}

	/**
	 * If this method is called by a client it will return a company by id
	 * 
	 * @param companyId
	 * @param token
	 * @return
	 */
	@GetMapping("/company/getOneCompany/{token}/{companyId}")
	public ResponseEntity<?> getOneCompany(@PathVariable String token, @PathVariable int companyId) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			AdminFacade service = (AdminFacade) session.getService();
			try {
				Company company = service.getCompanyById(companyId);
				return ResponseEntity.ok(company);
			} catch (Exception e) {
				return ResponseEntity.badRequest()
						.body("failed to get the company eather because it doesn't exist or something else!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized Login!");
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	/**
	 * This method if called by a client it will add a new customer
	 * 
	 * @param token
	 * @param customer
	 * @return
	 */
	@PostMapping("/customer/addcustomer/{token}")
	public ResponseEntity<?> addCustomer(@PathVariable String token, @RequestBody Customer customer) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			AdminFacade service = (AdminFacade) session.getService();
			try {
				String customerMessage = service.addNewCustomer(customer);
				return ResponseEntity.ok(customerMessage);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("failed to add a customer!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login!");
	}

	/**
	 * if this method is called by a client it will update a customer
	 * 
	 * @param token
	 * @param customer
	 * @return
	 */
	@PutMapping("/customer/updatecustomer/{token}")
	public ResponseEntity<?> updateCustomer(@PathVariable String token, @RequestBody Customer customer) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			AdminFacade service = (AdminFacade) session.getService();
			try {
				String customerMessage = service.updateCustomer(customer);
				return ResponseEntity.ok(customerMessage);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("failed to update a customer!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login!");
	}

	/**
	 * if this method is called by a client it will delete a custoemr by id
	 * 
	 * @param token
	 * @param customerId
	 * @return
	 */
	@DeleteMapping("/customer/deletecustomer/{token}/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable String token, @PathVariable int customerId) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			AdminFacade service = (AdminFacade) session.getService();
			try {
				String customerMessage = service.deleteCustomer(customerId);
				return ResponseEntity.ok(customerMessage);
			} catch (Exception e) {

				return ResponseEntity.badRequest().body("failed to delete a customer!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login!");
	}

	/**
	 * if this method is called by a client it will get all of the customers in the
	 * database
	 * 
	 * @param token
	 * @return
	 */
	@GetMapping("/customer/getallcustomer/{token}")
	public ResponseEntity<?> getAllCustomers(@PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			AdminFacade service = (AdminFacade) session.getService();
			try {
				List<Customer> customers = service.getAllCustomers();
				return ResponseEntity.ok(customers);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("failed to get all of the customers!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login!");
	}

	/**
	 * if this method is called by a client it will get a customer by id
	 * 
	 * @param token
	 * @param id
	 * @return
	 */
	@GetMapping("/customer/getonecustomer/{token}/{id}")
	public ResponseEntity<?> getOneCustomer(@PathVariable String token, @PathVariable int id) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			AdminFacade service = (AdminFacade) session.getService();
			try {
				Customer customer = service.getCustomerById(id);
				return ResponseEntity.ok(customer);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("Failed to get a customer!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login!");
	}

}
