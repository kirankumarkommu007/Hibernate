package com.demo.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.demo.entity.Address;
import com.demo.entity.User;


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
		User user = new User();
        user.setName("Kiran");
        user.setEmail("Kommu@gmail.com");

        // Create Address object
        Address address = new Address();
        address.setStreet("Mlg");
        address.setCity("nalgonda");
        address.setState("TG");
        address.setZipCode("508207");

        // Set address to user
        user.setAddress(address);

        // Save the user object (Persistent state)
        session.save(user);


		// Commit the transaction
		transaction.commit();

		// The user objects are now in the detached state
		session.close();

		System.out.println("Users saved successfully");

		sessionFactory.close();
	}
}
