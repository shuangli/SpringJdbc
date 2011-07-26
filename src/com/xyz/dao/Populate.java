package com.xyz.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xyz.domain.User;

public class Populate {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		long end = System.currentTimeMillis();
		System.out.println("create context: " + (end - start));
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");

		long insert_total = 0;
		for (int i=1000000; i<1100000; i++) {
			User user = new User(i, "first"+i, "last"+i, "address"+i, "city"+i, "state"+i, 12345, 
								 "c1_"+i, "c2_"+i, "c3_"+i, "c4_"+i, "c5_"+i, "c6_"+i, "c7_"+i, "c8_"+i, "c9_"+i);

			long insert_start = System.currentTimeMillis();
			userDAO.insertUser(user);
			long insert_end = System.currentTimeMillis();
			insert_total += (insert_end - insert_start);
			if (i % 1000 == 0) {
				System.out.println("insert at: " + i);
			}
		}
		System.out.println("insert total: " + insert_total);
	}
}