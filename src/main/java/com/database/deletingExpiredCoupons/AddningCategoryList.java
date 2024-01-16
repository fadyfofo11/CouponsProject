package com.database.deletingExpiredCoupons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.database.beans.Category;
import com.database.repositories.CategoryRepository;

@Component
public class AddningCategoryList implements Runnable {
	@Autowired
	private CategoryRepository catrepo;

	@Override
	public void run() {
		Category cat = null;
		try {
			if (!catrepo.existsById(1)) {
				String message = addCategory();
				System.out.println(message);
			} else {
				System.out.println("Categories already exist!");
			}

		} catch (Exception e) {
			e.getMessage();
		}

	}

	public String addCategory() {

//			if (!catrepo.findByCategory("FOOD")) {
		Category food = new Category("FOOD");
		catrepo.save(food);
//			}

//			if (!catrepo.findByCategory("ELECTRICITY")) {
		Category electricity = new Category("ELECTRICITY");
		catrepo.save(electricity);
//			}

//			if (!catrepo.findByCategory("VACATION")) {
		Category vacation = new Category("VACATION");
		catrepo.save(vacation);
//			}

//			if (!catrepo.findByCategory("RESTRAUNT")) {
		Category restraunt = new Category("RESTRAUNT");
		catrepo.save(restraunt);
//			}

//			if (!catrepo.findByCategory("OTHER")) {
		Category other = new Category("OTHER");
		catrepo.save(other);
//			}

		return "Done inserting categories!";

	}

}
