package com.xyz.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xyz.domain.User;

public class DuplicateTest {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		long end = System.currentTimeMillis();
		System.out.println("create context: " + (end - start));
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");

		batch(userDAO, 0, 1);
		batch(userDAO, 0, 1);
	}

	private static void batch(UserDAO userDAO, int first, int count) {
		long insert_total = 0;
		long select_total = 0;

		for (int i = first; i < count; i++) {
			User user = new User(i, "first"+i, "last"+i, "address"+i, "city"+i, "state"+i, 12345, 
					 "c1_"+i, "c2_"+i, "c3_"+i, "c4_"+i, "c5_"+i, "c6_"+i, "c7_"+i, "c8_"+i, "c9_"+i);

			long insert_start = System.currentTimeMillis();
			userDAO.insertUser(user);
			long insert_end = System.currentTimeMillis();
			insert_total += (insert_end - insert_start);
			long select_start = System.currentTimeMillis();
			User user1 = userDAO.selectUser(i);
			long select_end = System.currentTimeMillis();
			select_total += (select_end - select_start);
			if (i % 1000 == 0) {
				System.out.println("run at: " + i);
			}
		}
		System.out.println(insert_total+", "+select_total);
		
	}
}