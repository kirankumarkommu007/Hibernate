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
		// Create a Hibernate configuration object, which reads the hibernate.cfg.xml
		// file
		Configuration configuration = new Configuration().configure();

		// Create a session factory based on the configuration
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		// Open a session from the session factory
		Session session = sessionFactory.openSession();

		// Get the CriteriaBuilder instance from the session to construct the query
		CriteriaBuilder cb = session.getCriteriaBuilder();

		// Create a CriteriaQuery object for the Employee entity
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

		// Define the root of the query, which is the Employee entity
		Root<Employee> root = cq.from(Employee.class);

		// Create a condition (predicate) to find employees with id = 1
		Predicate predicate = cb.equal(root.get("id"), "1");

		// Set the query to select all columns from Employee where the condition is met
		cq.select(root).where(predicate);

		// Execute the query and retrieve the results as a list of Employee objects
		List<Employee> result = session.createQuery(cq).getResultList();

		// Iterate over the results and print the fetched Employee details
		for (Employee user : result) {
			System.out.println("Fetched User: " + user.getName() + ", " + user.getEmail());
		}

		// Uncomment these lines if you want to close the session and session factory
		// newSession.close();
		// sessionFactory.close();
	}
}
