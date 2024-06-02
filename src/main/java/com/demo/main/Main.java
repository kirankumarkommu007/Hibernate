package com.demo.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.demo.entity.AdminUser;
import com.demo.entity.RegularUser;

public class Main {
	public static void main(String[] args) {
		// Create a Hibernate configuration object
		Configuration configuration = new Configuration().configure();

		// Create a session factory
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		// Open a session
		Session session = sessionFactory.openSession();

		// Begin a transaction
		Transaction transaction = session.beginTransaction();

		RegularUser regularUser = new RegularUser();
		regularUser.setStatus("ACTIVE");
		regularUser.setEmail("kommu@gmail.com");
		regularUser.setName("kiran");
		session.save(regularUser);

		
		RegularUser regularUser1 = new RegularUser();
		regularUser1.setStatus("DEACTIVE");
		regularUser1.setEmail("shivam@gmail.com");
		regularUser1.setName("Shiva");
		session.save(regularUser1);


		AdminUser adminUser = new AdminUser();
		adminUser.setAdminLevel("SUPER ADMIN");
		adminUser.setEmail("admin@gmail.com");
		adminUser.setName("shiva");

		
		session.save(adminUser);
		
		// Commit the transaction
		transaction.commit();

		// The user objects are now in the detached state
		session.close();

		System.out.println("Users saved successfully");

		sessionFactory.close();
	}
}
