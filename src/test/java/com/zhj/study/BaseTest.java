package com.zhj.study;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;

import com.zhj.study.hibernate.demo.po.Student;

public class BaseTest {

	protected SessionFactory sessionFactory;
	protected Session session;
	protected Transaction transaction;
	
	@Before
	public void init() {
//		Configuration config = (new Configuration()).configure("demo.hibernate.cfg.xml");
		Configuration config = (new AnnotationConfiguration()).configure("demo.hibernate.cfg.xml");
		sessionFactory = config.buildSessionFactory();
		
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
	}
	
	@After
	public void destroy() {
		session.beginTransaction().commit();
		
		if(session.isOpen()) {
			session.close();
		}
		sessionFactory.close();
	}
}
