package com.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.demo.entity.Employee;
import com.demo.entity.FullTimeEmployee;
import com.demo.entity.PartTimeEmployee;
import com.demo.util.HibernateUtil;

public class App {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		// Saving some employees
		FullTimeEmployee fullTimeEmployee = new FullTimeEmployee();
		fullTimeEmployee.setName("John Doe");

		PartTimeEmployee partTimeEmployee = new PartTimeEmployee();
		partTimeEmployee.setName("Jane Doe");

		session.save(fullTimeEmployee);
		session.save(partTimeEmployee);

		transaction.commit();

		// Querying employees using HQL
		Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
		List<Employee> employees = query.list();

		for (Employee employee : employees) {
			System.out.println("Employee Name: " + employee.getName());
		}

		session.close();
	}
}
