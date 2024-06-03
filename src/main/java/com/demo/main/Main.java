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
        
        
    
     		User user1 = new User();
             user1.setName("Shiva");
             user1.setEmail("Shivam@gmail.com");

             // Create Address object
             Address address1 = new Address();
             address1.setStreet("Mlg");
             address1.setCity("nalgonda");
             address1.setState("TG");
             address1.setZipCode("508207");

             // Set address to user
             user1.setAddress(address1);

             // Save the user object (Persistent state)
             session.save(user1);


		// Commit the transaction
		transaction.commit();

		// The user objects are now in the detached state
		session.close();

		System.out.println("Users saved successfully");

		sessionFactory.close();
	}
}
