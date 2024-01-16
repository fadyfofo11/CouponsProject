package com.database.web.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.database.beans.CategoryTypes;
import com.database.beans.Coupon;
import com.database.beans.Customer;
import com.database.facades.CustomerFacade;
import com.database.web.other.Session;

@RestController
@RequestMapping("customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

	@Autowired
	private Map<String, Session> sessionsMap;

	public CustomerController() {
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

	@GetMapping("/coupon/getallcoupons/{token}")
	public ResponseEntity<?> getAllCoupons(@PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CustomerFacade service = (CustomerFacade) session.getService();
			try {
				List<Coupon> coupons = service.getAllCoupons();
				return ResponseEntity.ok(coupons);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("failed to get all of the coupons!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login");
	}

	@PostMapping("/coupon/purchasecoupon/{token}/{id}")
	public ResponseEntity<?> purchaseCoupon(@PathVariable String token, @PathVariable int id) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CustomerFacade service = (CustomerFacade) session.getService();
			try {
				String purchaseMessage = service.purchaseCoupon(id);
				return ResponseEntity.ok(purchaseMessage);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("failed to purchase coupon!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login!");
	}

	@GetMapping("/coupon/getallcustomercoupons/{token}")
	public ResponseEntity<?> getAllCustomersCoupons(@PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CustomerFacade service = (CustomerFacade) session.getService();
			try {
				List<Coupon> customersCoupons = service.getAllCustomerCoupons();
				return ResponseEntity.ok(customersCoupons);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("Failed to get all of the customers coupons!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login!");
	}

	@GetMapping("/coupon/getcustomercouponsbycategory/{token}/{category}")
	public ResponseEntity<?> getCustomersCouponsByCategory(@PathVariable String token,
			@PathVariable CategoryTypes category) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CustomerFacade service = (CustomerFacade) session.getService();
			try {
				List<Coupon> couponsByCategory = service.getAllCustomerCouponsByCategory(category);
				return ResponseEntity.ok(couponsByCategory);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("failed to get coupons by category!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login!");
	}

	@GetMapping("/coupon/getcustomercouponsbymaxprice/{token}/{maxPrice}")
	public ResponseEntity<?> getCustomerCouponsByMaxPrice(@PathVariable String token, @PathVariable double maxPrice) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CustomerFacade service = (CustomerFacade) session.getService();
			try {
				List<Coupon> couponsByMaxPrice = service.getAllCustomerCouponsByMaxPrice(maxPrice);
				return ResponseEntity.ok(couponsByMaxPrice);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("Couldn't get all coupons by maxprice for customer!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login!");
	}

	@GetMapping("/coupon/getcouponsbycategory/{token}/{category}")
	public ResponseEntity<?> getCouponsByCategory(@PathVariable String token, @PathVariable CategoryTypes category) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CustomerFacade service = (CustomerFacade) session.getService();
			try {
				List<Coupon> couponsByCategory = service.getAllCouponsByCategory(category);
				return ResponseEntity.ok(couponsByCategory);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("failed to get coupons by category!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login!");
	}

	@GetMapping("/coupon/getcouponsbymaxprice/{token}/{maxPrice}")
	public ResponseEntity<?> getCouponsByMaxPrice(@PathVariable String token, @PathVariable double maxPrice) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CustomerFacade service = (CustomerFacade) session.getService();
			try {
				List<Coupon> couponsByMaxPrice = service.getAllCouponsByMaxPrice(maxPrice);
				return ResponseEntity.ok(couponsByMaxPrice);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("Couldn't get all coupons by maxprice for customer!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login!");
	}

	@GetMapping("/customer/getCustomerInformation/{token}")
	public ResponseEntity<?> getCustomerInformation(@PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeOut(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CustomerFacade service = (CustomerFacade) session.getService();
			try {
				Customer customerInfo = service.getCustomerInformation();
				return ResponseEntity.ok(customerInfo);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("Failed to get customer's information!");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized login!");
	}

}
