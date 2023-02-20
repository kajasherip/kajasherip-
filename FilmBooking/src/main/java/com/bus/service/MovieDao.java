package com.bus.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bus.beans.MovieDetails;

@Repository
public class MovieDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	public void createMovie(MovieDetails movieDetails) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(movieDetails).toString();
			System.out.println("Movie Id is created = ");
			session.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
