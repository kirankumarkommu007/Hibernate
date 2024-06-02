package com.demo.main;

import com.demo.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

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

		// Execute Native SQL query to retrieve all users
		List<User> allUsers = session.createNativeQuery("SELECT * FROM user", User.class).list();
		for (User user : allUsers) {
			System.out
					.println("User ID: " + user.getId() + ", Name: " + user.getName() + ", Email: " + user.getEmail());
		}

		// Execute Native SQL query to retrieve users with a specific name
		List<User> usersWithNameJohn = session
				.createNativeQuery("SELECT * FROM user WHERE name = 'Kiran'", User.class).list();
		for (User user : usersWithNameJohn) {
			System.out
					.println("User ID: " + user.getId() + ", Name: " + user.getName() + ", Email: " + user.getEmail());
		}

		// Execute Native SQL update query to update email of a user
		int rowsUpdated = session.createNativeQuery("UPDATE user SET email = 'Kommukiran1226@example.com' WHERE id = 1")
				.executeUpdate();
		System.out.println("Rows updated: " + rowsUpdated);


		// Commit the transaction
		transaction.commit();

		// Close session and session factory
		session.close();
		sessionFactory.close();

	}
}
