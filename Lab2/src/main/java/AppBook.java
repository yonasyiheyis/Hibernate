import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class AppBook {
	
	private static SessionFactory sessionFactory;

	static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Book.class));
	}

	public static void main(String[] args) {
		
		// Hibernate placeholders
				Session session = null;
				Transaction tx = null;
				// Create and save three books
				try {
					session = sessionFactory.openSession();
					tx = session.beginTransaction();
					
					// Create new instance of books
					java.util.Date date1 = new java.util.Date();
					java.util.Date date2 = new java.util.Date();
					java.util.Date date3 = new java.util.Date();
					Book book1 = new Book(1, "Book 1", "ISBN 1", "Author 1", 101.0, date1);
					Book book2 = new Book(2, "Book 2", "ISBN 2", "Author 2", 102.0, date2);
					Book book3 = new Book(3, "Book 3", "ISBN 3", "Author 3", 103.0, date3);
					
					// save the book
					session.persist(book1);
					session.persist(book2);
					session.persist(book3);

					tx.commit();
				} catch (HibernateException e) {
					tx.rollback();
					e.printStackTrace();
				} finally {
					if (session != null)
						session.close();
				}
				// Retrieve and output all books
				try {
					session = sessionFactory.openSession();
					tx = session.beginTransaction();
					// retrieve all books
					List<Book> bookList = session.createQuery("from Book").list();
					for (Book book : bookList) {
						System.out.println(book);
					}
					tx.commit();
				} catch (HibernateException e) {
					tx.rollback();
					e.printStackTrace();
				} finally {
					if (session != null)
						session.close();
				}
				// Retrieve one book and update it and Delete another book
				try {
					session = sessionFactory.openSession();
					tx = session.beginTransaction();
					// retrieve one book and update it
					Book book1 = (Book) session.get(Book.class, 1);
					book1.setTitle("Book 1 Updated");
					book1.setPrice(110.0);
					// Delete another book
					Book book2 = (Book) session.load(Book.class, 2);
					session.delete(book2);
					
					tx.commit();
				} catch (HibernateException e) {
					tx.rollback();
					e.printStackTrace();
				} finally {
					if (session != null)
						session.close();
				}
				// Retrieve and output all books
				try {
					session = sessionFactory.openSession();
					tx = session.beginTransaction();
					// retrieve all books
					List<Book> bookList = session.createQuery("from Book").list();
					for (Book book : bookList) {
						System.out.println(book);
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
