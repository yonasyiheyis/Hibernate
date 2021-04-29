package edu.miu.cs.cs544.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Application {
	
	private static final SessionFactory sessionFactory;
	
	static {
		// If there is more than one entity, you will have to pass them as a comma delimited argument list to the method below
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Customer.class, Order.class, OrderLine.class, Product.class, CD.class, DVD.class, Book.class));
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// Hibernate placeholders
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Customer cust = new Customer("John", "Doe");
			Product cd = new CD("J.cole", "CD", "Music CD");
			Product dvd = new DVD("Blues", "DVD", "Music DVD");
			Product book = new Book("Decepto", "Book", "Novel ");
			List<OrderLine> oList = new ArrayList<>();
			OrderLine ol1 = new OrderLine(2, cd);
			OrderLine ol2 = new OrderLine(3, dvd);
			OrderLine ol3 = new OrderLine(4, book);
			oList.add(ol1);
			oList.add(ol2);
			oList.add(ol3);
			Order o1 = new Order(cust, oList);
			List<Order> orderList = new ArrayList<>();
			cust.setOrders(orderList);
			
			
			// save
			session.persist(o1);
			session.persist(cust);
			session.persist(cd);
			session.persist(dvd);
			session.persist(book);
			session.persist(ol1);
			session.persist(ol2);
			session.persist(ol3);

			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			// retrieve all orders
			List<Order> orderList = session.createQuery("from Order").list();
			for (Order o : orderList) {
				System.out.println(o);
			}
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		
		// Close the SessionFactory (not mandatory)
		sessionFactory.close();
	}
}