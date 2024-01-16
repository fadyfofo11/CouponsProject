package com.database.tests;

import java.sql.Date;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.database.beans.Category;
import com.database.beans.CategoryTypes;
import com.database.beans.Company;
import com.database.beans.Coupon;
import com.database.beans.Customer;
import com.database.customExceptions.CompanyAlreadyExistsException;
import com.database.customExceptions.CompanyDoesntExistException;
import com.database.customExceptions.CouponAlreadyExistsException;
import com.database.customExceptions.CouponDoesntExistException;
import com.database.customExceptions.CustomerAlreadyExistsException;
import com.database.customExceptions.CustomerDoesntExistException;
import com.database.db.dbdao.CompaniesDBDAO;
import com.database.db.dbdao.CouponDBDAO;
import com.database.db.dbdao.CustomerDBDAO;
import com.database.deletingExpiredCoupons.AddningCategoryList;
import com.database.deletingExpiredCoupons.DeletingExpiredCouponsDailyJob;
import com.database.facades.AdminFacade;
import com.database.facades.ClientFacade;
import com.database.facades.ClientType;
import com.database.facades.CompanyFacade;
import com.database.facades.CustomerFacade;
import com.database.loginManager.LoginManager;

@Component
public class TestAll {

	@Autowired
	private ConfigurableApplicationContext ctx;

	/*
	 * When you run this constructor it will test all
	 */
	public void Test() {
		/*
		 * CompaniesDBDAO comb = ctx.getBean(CompaniesDBDAO.class); CustomerDBDAO cust =
		 * ctx.getBean(CustomerDBDAO.class); CouponDBDAO coup =
		 * ctx.getBean(CouponDBDAO.class);
		 * 
		 * // why it doesn't work ClientFacade client = ctx.getBean(AdminFacade.class);
		 * // it works AdminFacade admin = ctx.getBean(AdminFacade.class);
		 * 
		 * Thread deleting = new
		 * Thread(ctx.getBean(DeletingExpiredCouponsDailyJob.class)); Thread
		 * addingCategories = new Thread(ctx.getBean(AddningCategoryList.class));
		 * deleting.start(); addingCategories.start();
		 * 
		 * Company company = new Company("test", "test@test.com", "password");
		 * 
		 * try { comb.addCompany(company); } catch (CompanyAlreadyExistsException e) {
		 * System.out.println(e.getMessage()); } catch (Exception e) { // TODO: handle
		 * exception }
		 * 
		 * Customer customer = new Customer("customer", "Customer",
		 * "customer@customer.com", "password");
		 * 
		 * Calendar cal = Calendar.getInstance(); Date date = new
		 * Date(cal.getTimeInMillis()); try { cust.addCustomer(customer); } catch
		 * (CustomerAlreadyExistsException e1) { System.out.println(e1.getMessage()); }
		 * List<Customer> customers = new LinkedList<Customer>();
		 * System.out.println(customers.add(customer));
		 * 
		 * Company thiss = null; try { thiss = comb.getOneCompany(1); } catch
		 * (CompanyDoesntExistException e) { System.out.println(e.getMessage()); }
		 * 
		 * Coupon coupon = new Coupon(thiss, CategoryTypes.FOOD, "food", "Working",
		 * date, date, 10, 5.5, "hello");
		 * 
		 * try { String excMessage = coup.addCoupon(coupon);
		 * System.out.println(excMessage); } catch (CouponAlreadyExistsException e) {
		 * System.out.println(e.getMessage()); }
		 * 
		 * try { cust.updateCustomer(customer); } catch (CustomerDoesntExistException e)
		 * { System.out.println(e.getMessage()); }
		 * 
		 * /** Those are the facades
		 */
		/*
		 * AdminFacade adminFacade = null; CustomerFacade customerFacade = null;
		 * CompanyFacade companyFacade = null;
		 * 
		 * /** This is a login manager
		 */
		/*
		 * LoginManager login = ctx.getBean(LoginManager.class); ClientType clientType =
		 * ClientType.Admin;
		 * 
		 * switch (clientType) { case Admin: adminFacade = (AdminFacade)
		 * login.login("admin@admin.com", "admin", ClientType.Admin); break; case
		 * Customer: customerFacade = (CustomerFacade) login.login("", "",
		 * ClientType.Customer); case Company: companyFacade = (CompanyFacade)
		 * login.login("", "", ClientType.Company); }
		 * 
		 * // adminFacade.addNewCompany(company); try { deleting.join(); } catch
		 * (InterruptedException e) { System.out.println(e.getMessage()); }
		 * deleting.stop(); addingCategories.stop(); // ctx.getBean(LoginManager.class)
		 */
	}

	public void mainTest() {
		Thread deletingExpiredCoupons = new Thread(ctx.getBean(DeletingExpiredCouponsDailyJob.class));
		Thread addingCategories = new Thread(ctx.getBean(AddningCategoryList.class));
		deletingExpiredCoupons.start();
		addingCategories.start();
		try {
			addingCategories.join();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

		Random rand = new Random();
		// this is login manager
		LoginManager login = ctx.getBean(LoginManager.class);

		// enter the email here
		String email = "admin@admin.com";
		// enter the password here
		String password = "admin";
		// enter the login type here
		ClientType clientType = ClientType.Admin;

		switch (clientType) {
		case Admin:
			AdminFacade adminFacade = null;

			try {
				System.out.println("================Logging!!!=============================");
				adminFacade = (AdminFacade) login.login(email, password, clientType);

				if (adminFacade != null) {
					System.out.println("you have successfully loggedin!");
					System.out.println("=============================================");
					System.out.println();
					// ========================================================================================================================================================
					System.out.println(
							"=================================Adding Companies!=====================================");
					for (int i = 0; i < 5; i++) {
						String addingCompanyMessage = adminFacade
								.addNewCompany(new Company("test" + i, "test" + i + "@test.com", "test" + i));
						System.out.println(addingCompanyMessage);
					}
					System.out.println(
							"=================================finished adding companies!=====================================");
					System.out.println("");
					System.out.println(
							"==================================Updating Company====================================");
					Company updatedCompany = null;
					try {
						updatedCompany = adminFacade.getCompanyById(3);
						updatedCompany.setEmail("MyEmailGotUpdated@gmail.com");
						updatedCompany.setPassword("i changed");
						updatedCompany.setName("hello");
						System.out.println(updatedCompany.getId());
						String updatingCompanyMessage = adminFacade.updateCompany(updatedCompany);
						System.out.println(updatingCompanyMessage);
					} catch (CompanyDoesntExistException e) {
						System.out.println(e.getMessage());
					}
					System.out.println(
							"==================================finished Updating the company!====================================");
					// ========================================================================================================================================================
					System.out.println();
					System.out.println(
							"==================================Deleting the company!====================================");
					String deletingCompanyMessage = adminFacade.deleteCompany(8);
					System.out.println(deletingCompanyMessage);
					System.out.println(
							"==================================finished Deleting the company!====================================");
					System.out.println();
					System.out.println(
							"==================================Getting all the companies!!====================================");

					for (Company c : adminFacade.getAllCompanies()) {
						System.out.println(c);
					}
					System.out.println(
							"==================================finished Getting all the companies!!====================================");
					System.out.println();
					System.out.println(
							"=======================Getting company by id!===============================================");
					Company getOneByAdmin = null;
					try {
						getOneByAdmin = adminFacade.getCompanyById(2);
					} catch (CompanyDoesntExistException e) {
						System.out.println(e.getMessage());
					}
					System.out.println(getOneByAdmin);
					System.out.println(
							"========================finished Getting one company by id!==============================================");
					System.out.println();
					System.out.println(
							"========================Adding new Customers!==============================================");
					for (int i = 0; i < 5; i++) {
						String addingNewCustomerMessage = adminFacade.addNewCustomer(new Customer("customer" + i,
								"google" + i, "customer" + i + "@customer.com", "customer" + i));
						System.out.println(addingNewCustomerMessage);
					}
					System.out.println(
							"====================Finished adding customers!==================================================");
					System.out.println();

					System.out.println(
							"=================================Updating an existing customer!=====================================");
					Customer updatedCustomer = null;
					try {
						updatedCustomer = adminFacade.getCustomerById(3);
						updatedCustomer.setEmail("IChanged@customer.com");
						updatedCustomer.setFirst_name("I am a changed first name");
					} catch (CustomerDoesntExistException e) {
						System.out.println(e.getMessage());
					}
					String updaingCustomerMessage = adminFacade.updateCustomer(updatedCustomer);
					System.out.println(updaingCustomerMessage);
					System.out.println(
							"======================Finished updating Customer!================================================");
					System.out.println();
					System.out.println(
							"========================Deleting a customer!==============================================");
					String deletingCustomerMessage = adminFacade.deleteCustomer(1);
					System.out.println(deletingCustomerMessage);
					System.out.println(
							"========================Finished Deleting Customer!==============================================");
					System.out.println();
					System.out.println(
							"=========================Getting all customers!=============================================");
					for (Customer c : adminFacade.getAllCustomers()) {
						System.out.println(c);
					}
					System.out.println(
							"===========================Finished Getting all Customers!===========================================");
					System.out.println();
					System.out.println(
							"============================Getting Customer by id!==========================================");
					Customer getByID = null;
					try {
						getByID = adminFacade.getCustomerById(5);
					} catch (CustomerDoesntExistException e) {
						System.out.println(e.getMessage());
					}
					System.out.println(getByID);
					System.out.println(
							"============================Finished Getting customer by id!==========================================");
					System.out.println();
				} else {
					System.out.println("=============================================");
					System.out.println("password or email are wrong!");
					System.out.println("try again!");
					System.out.println("=============================================");
				}
			} catch (NullPointerException e) {
				System.out.println("=============================================");
				System.out.println("password or email are wrong!");
				System.out.println("try again!");
				System.out.println("=============================================");
			}
			break;
		case Company:
			CompanyFacade companyFacade = null;
			Calendar cal = Calendar.getInstance();
			Calendar endCal = Calendar.getInstance();
			endCal.add(Calendar.DAY_OF_MONTH, -5);

			Calendar endCal2 = Calendar.getInstance();
			endCal2.add(Calendar.DAY_OF_MONTH, +5);
			try {
				System.out.println("==================Logging!!===========================");
				companyFacade = (CompanyFacade) login.login(email, password, clientType);
				if (companyFacade != null) {
					System.out.println("you have successfully logged in!");
					System.out.println("=============================================");
					System.out.println();

					System.out.println("=====================Adding new Coupons!========================");
					for (int i = 0; i < 5; i++) {
						String couponAddingMessage = companyFacade.addNewCoupon(new Coupon(CategoryTypes.RESTRAUNT,
								"couponCEO" + i + rand.nextInt(100), "i am a coupon", new Date(cal.getTimeInMillis()),
								new Date(endCal2.getTimeInMillis()), rand.nextInt(100), rand.nextDouble() * 100,
								"location" + i));
						System.out.println(couponAddingMessage);
					}
					System.out.println("=====================Finished Adding Coupons!========================");
					System.out.println();
					System.out.println("=====================Updating Coupon!========================");
					Coupon companiesCoupon = null;
					try {
						companiesCoupon = companyFacade.getCouponByCompanyAndCoupon(1);
						companiesCoupon.setEnd_date(new Date(endCal2.getTimeInMillis()));
						companiesCoupon.setAmount(16);
						String couponUpdateMessage = companyFacade.updateCoupon(companiesCoupon);
						System.out.println(couponUpdateMessage);
					} catch (Exception e) {
						System.out.println("Something updating happened");

					}
					System.out.println("=====================Finished Updating Coupon!========================");
					System.out.println();

					System.out.println("=====================Deleting Coupon!========================");
					try {
						String deletingCouponMessage = companyFacade.deleteCoupon(4);
						System.out.println(deletingCouponMessage);
					} catch (Exception e) {
						System.out.println(
								"Sorry you can't delete this coupon either because it doesn't exist or it doesn't belong to you!");
					}
					System.out.println("=====================Finished Deleting Coupon!========================");
					System.out.println();
					System.out.println("=====================Getting all company Coupons!========================");
					for (Coupon ccc : companyFacade.getAllCompanyCoupons()) {
						System.out.println(ccc);
					}
					System.out.println(
							"=====================Finished Getting all Company Coupons!========================");
					System.out.println();

					System.out.println(
							"=====================Getting all company Coupons by category!========================");
					for (Coupon couponCategory : companyFacade.getAllCouponsByCategory(CategoryTypes.FOOD)) {
						System.out.println(couponCategory);

					}
					System.out.println(
							"=====================Finished Getting all company Coupons by Category!========================");
					System.out.println();

					System.out.println(
							"=====================Getting all company Coupons by maxPrice!========================");
					for (Coupon couponMaxPrice : companyFacade.getCouponsByMaxPrice(50)) {
						System.out.println(couponMaxPrice);
					}
					System.out.println(
							"=====================Finished Getting all company Coupons by maxPrice!========================");
					System.out.println();
					System.out.println("=====================Getting Company Information!========================");
					System.out.println(companyFacade.getCompanyInformation());
					System.out.println(
							"=====================Finished Getting Company Information !========================");

				} else {
					System.out.println("=============================================");
					System.out.println("password or email are wrong!");
					System.out.println("try again!");
					System.out.println("=============================================");
				}
			} catch (Exception e) {
				System.out.println("=============================================");
				System.out.println("password or email are wrong!");
				System.out.println("try again!");
				System.out.println("=============================================");
			}
			break;
		case Customer:
			CustomerFacade customerFacade = null;
			try {
				System.out.println("====================Logging!=========================");
				customerFacade = (CustomerFacade) login.login(email, password, clientType);
				if (!customerFacade.equals(null)) {
					System.out.println("=====================Successfully logged in!========================");
					System.out.println();
					System.out.println("=====================Getting all coupons!========================");
					try {
						for (Coupon coupon : customerFacade.getAllCoupons()) {
							System.out.println(coupon);
						}
					} catch (CouponDoesntExistException e) {
						System.out.println(e.getCouponEmptyMessage());
					}
					System.out.println("======================Finished getting all coupons!=======================");
					System.out.println();
					System.out.println("=======================buying a coupon!======================");
					String purchaseMessage = "";
					for (int i = 0; i < 5; i++) {
						purchaseMessage = customerFacade.purchaseCoupon(rand.nextInt(20) + 1);
						System.out.println(purchaseMessage);
					}

					System.out.println("======================Finished buying coupon!=======================");
					System.out.println();
					System.out.println("=========================Getting all purchased Coupons!====================");
					try {
						for (Coupon coupon : customerFacade.getAllCustomerCoupons()) {
							System.out.println(coupon);
						}
					} catch (CouponDoesntExistException e) {
						System.out.println(e.getCouponEmptyMessage());
					}
					System.out.println(
							"=================Finished getting purchased Coupons!============================");
					System.out.println();
					System.out.println(
							"===================Getting purchased coupons by category!==========================");
					try {
						for (Coupon coupon : customerFacade.getAllCustomerCouponsByCategory(CategoryTypes.RESTRAUNT)) {
							System.out.println(coupon);
						}
					} catch (CouponDoesntExistException e) {
						System.out.println(e.getCouponEmptyMessage());
					}
					System.out.println(
							"====================Finished Getting all purchased coupons by category!=========================");
					System.out.println();
					System.out.println("====================Getting coupons by maxprice!=========================");
					try {
						for (Coupon coupon : customerFacade.getAllCouponsByMaxPrice(5)) {
							System.out.println(coupon);
						}
					} catch (CouponDoesntExistException e) {
						System.out.println(e.getCouponEmptyMessage());
					}
					System.out.println(
							"====================Finished getting all customers coupons by maxprice!=========================");
					System.out.println();
					System.out.println("===================Getting Customers Information!==========================");
					System.out.println(customerFacade.getCustomerInformation());
					System.out.println(
							"==================Finished Getting customers information!===========================");
					System.out.println();
					System.out.println(
							"===================Getting all Customer coupons by maxPrice!==========================");
					for (Coupon coupon : customerFacade.getAllCustomerCouponsByMaxPrice(15)) {
						System.out.println(coupon);
					}
					System.out.println(
							"==================Finished Getting customers coupons by maxprice!===========================");
					System.out.println();

					System.out.println(
							"==================Getting Customers coupons by category!===========================");
					for (Coupon coupon : customerFacade.getAllCustomerCouponsByCategory(CategoryTypes.FOOD)) {
						System.out.println(coupon);
					}
					System.out.println(
							"====================Finished Getting customers coupons by category!=========================");
					System.out.println();
				}
			} catch (Exception e) {
				System.out.println("=============================================");
				System.out.println("password or email are wrong!");
				System.out.println("try again!");
				System.out.println("=============================================");
			}

			break;
		default:

			break;
		}

		// logging as an Admin

		try {
			deletingExpiredCoupons.join();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		deletingExpiredCoupons.stop();
		addingCategories.stop();

	}
}
