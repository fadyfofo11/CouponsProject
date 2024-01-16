package com.database.deletingExpiredCoupons;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.database.beans.Coupon;
import com.database.repositories.CouponRepository;

@Component
public class DeletingExpiredCouponsDailyJob implements Runnable {

	@Autowired
	private CouponRepository couponrepo;

	@Override
	public void run() {

		/**
		 * This delays the job by 12 hours every day it will check the coupons by there
		 * end date and delete them if expired it checks the coupons twice a day
		 */
		try {
			Thread.sleep(1000 * 60 /** 60 * 12 */
			);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

		String deletingMessage = deletingCoupons();

		// this is just for debugging the print in the console
		System.out.println(deletingMessage);

	}

	/**
	 * this method if called will delete coupons by there end date
	 * 
	 * @return
	 */
	private String deletingCoupons() {
		// this create a time for now this moment
		Calendar cal = Calendar.getInstance();
		Date endDate = new Date(cal.getTimeInMillis());

//		 ======================================================
//		List<Coupon> expiredCoupons = couponrepo.findAll();
		List<Coupon> expiredCoupons = couponrepo.findByEndDateBefore(endDate);
		for (Coupon coupon : expiredCoupons) {
			if (coupon.getEnd_date().before(endDate)) {
				couponrepo.delete(coupon);
				System.out.println(coupon);
			}
		}

//		 ======================================================
		try {
			if (expiredCoupons.isEmpty())
				throw new NullPointerException();
//		couponrepo.removeByEndDateBefore(new Date(cal.getTimeInMillis()));
			return "'" + expiredCoupons.size() + "' Coupons have been succesfully deleted at - '" + cal.getTime()
					+ "' today!";
		} catch (Exception e) {
			return "No coupons To be deleted all coupons are UpToDate!";
		}
	}

}
