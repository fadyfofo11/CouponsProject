package com.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.database.tests.TestAll;

@SpringBootApplication
public class MakingDatabaseApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(MakingDatabaseApplication.class, args);

		TestAll testAll = ctx.getBean(TestAll.class);

		testAll.mainTest();

	}

}
