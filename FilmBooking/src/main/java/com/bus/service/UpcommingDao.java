package com.bus.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bus.beans.UpcommingMovies;

@Repository
public class UpcommingDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void createMovie(UpcommingMovies upMovies) {
		Session session1 = null;
		try {
			session1 = sessionFactory.openSession();
			session1.beginTransaction();
			session1.save(upMovies).toString();
			System.out.println("Movie Id is created ");
			session1.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
