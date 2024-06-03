package com.demo.entity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
	public static void main(String[] args) {
	
		Configuration configuration = new Configuration().configure();

		SessionFactory sessionFactory = configuration.buildSessionFactory();

		Session session = sessionFactory.openSession();

		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<NewUser> cq = cb.createQuery(NewUser.class);

		// Define the root of the query, which is the Employee entity
		Root<NewUser> root = cq.from(NewUser.class);

		// Create a condition (predicate) to find employees with id = 1
		Predicate predicate = cb.equal(root.get("id"), "1");

		// Set the query to select all columns from Employee where the condition is met
		cq.select(root).where(predicate);

		// Execute the query and retrieve the results as a list of Employee objects
		List<NewUser> result = session.createQuery(cq).getResultList();

		// Iterate over the results and print the fetched Employee details
		for (NewUser user : result) {
			System.out.println("Fetched User: " + user.getName() + ", " + user.getEmail());
		}

		 sessionFactory.close();
	}
}
