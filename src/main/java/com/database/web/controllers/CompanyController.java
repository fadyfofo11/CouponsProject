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

import com.database.beans.CategoryTypes;
import com.database.beans.Company;
import com.database.beans.Coupon;
import com.database.facades.CompanyFacade;
import com.database.web.other.Session;

@RestController
@RequestMapping("company")
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyController {

	@Autowired
	private Map<String, Session> sessionsMap;

	public CompanyController() {
		// TODO Auto-generated constructor stub
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
	 * if this method is called by a client it will add a new coupon
	 * 
	 * @param token
	 * @param coupon
	 * @return
	 */
	@PostMapping("/coupon/addcoupon/{token}")
	public ResponseEntity<?> addCoupon(@PathVariable String token, @RequestBody Coupon coupon) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CompanyFacade service = (CompanyFacade) session.getService();
			try {
				String couponMessage = service.addNewCoupon(coupon);
				return ResponseEntity.ok(couponMessage);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("Failed to add a coupon!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login");
	}

	
	/**
	 * if this method is called by a client it will update a coupon
	 * @param token
	 * @param coupon
	 * @return
	 */
	@PutMapping("/coupon/updatecoupon/{token}")
	public ResponseEntity<?> updateCoupon(@PathVariable String token, @RequestBody Coupon coupon) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CompanyFacade service = (CompanyFacade) session.getService();
			try {
				String couponMessage = service.updateCoupon(coupon);
				return ResponseEntity.ok(couponMessage);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("failed to update a coupon!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login");
	}

	
	/**
	 * if this method is called it will delete a coupon by id
	 * @param token
	 * @param id
	 * @return
	 */
	@DeleteMapping("/coupon/deletecoupon/{token}/{id}")
	public ResponseEntity<?> deleteCoupon(@PathVariable String token, @PathVariable int id) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CompanyFacade service = (CompanyFacade) session.getService();
			try {
				String couponMessage = service.deleteCoupon(id);
				return ResponseEntity.ok(couponMessage);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("failed to delete a coupon!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login");
	}

	
	/**
	 * if this method is called by a client it will get all of the company's coupons
	 * @param token
	 * @return
	 */
	@GetMapping("/coupon/getcompanycoupons/{token}")
	public ResponseEntity<?> getAllCompanyCoupons(@PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CompanyFacade service = (CompanyFacade) session.getService();
			try {
				List<Coupon> companyCoupons = service.getAllCompanyCoupons();
				return ResponseEntity.ok(companyCoupons);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("Failed to get company coupons!");

			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login");
	}

	/**
	 * if this method is called by a client it will return all of the company's couons by category
	 * @param token
	 * @param category
	 * @return
	 */
	@GetMapping("/coupon/getCompanyCouponsByCategory/{token}/{category}")
	public ResponseEntity<?> getCompanyCouponsByCategory(@PathVariable String token,
			@PathVariable CategoryTypes category) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CompanyFacade service = (CompanyFacade) session.getService();
			try {
				List<Coupon> couponsByCategory = service.getAllCouponsByCategory(category);
				return ResponseEntity.ok(couponsByCategory);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("failed to get coupons by category!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login");
	}

	
	@GetMapping("/coupon/getCouponsByMaxPrice/{token}/{maxPrice}")
	public ResponseEntity<?> getCoupnsByMaxPrice(@PathVariable String token, @PathVariable double maxPrice) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CompanyFacade service = (CompanyFacade) session.getService();
			try {
				List<Coupon> couponsByMaxPrice = service.getCouponsByMaxPrice(maxPrice);
				return ResponseEntity.ok(couponsByMaxPrice);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("failed to get coupons by maxprice!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login");
	}

	@GetMapping("/company/getcompanyinformation/{token}")
	public ResponseEntity<?> getCompanyInformation(@PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CompanyFacade service = (CompanyFacade) session.getService();
			try {
				Company companyInfo = service.getCompanyInformation();
				return ResponseEntity.ok(companyInfo);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("failed to get company information!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login");
	}
}
