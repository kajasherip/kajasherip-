package com.bus;

import com.bus.beans.ActorDetails;
import com.bus.beans.ActorPics;
import com.bus.beans.MovieDetails;
import com.bus.beans.UpcommingMovies;
import com.bus.service.ActorDao;
import com.bus.service.ActorPicDao;
import com.bus.service.MovieDao;
import com.bus.service.UpcommingDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BusExperimentApplication /* implements CommandLineRunner */ {

	@Autowired
	private UpcommingDao upcommingDao;

	@Autowired
	private ActorDao actorDao;

	@Autowired
	private ActorPicDao picDao;

	public static void main(String[] args) {
		SpringApplication.run(BusExperimentApplication.class, args);
	}

	
	/*
	 * @Override public void run(String... args) throws Exception { ActorDetails
	 * actorDetail = getActorDetails(); actorDao.createMovie(actorDetail); }
	 * 
	 * private ActorDetails getActorDetails() { ActorDetails actorDetails = new
	 * ActorDetails(); actorDetails.setActorsid(31);
	 * actorDetails.setActorName("Kyle Allen");
	 * actorDetails.setActressName("Kathryn Newton");
	 * actorDetails.setDirecterName("Ian Samuels");
	 * actorDetails.setMusicName("Tom Bromley");
	 * actorDetails.setProducerName("Akiva Goldsman"); return actorDetails; }
	 */
	 	 
	
	/*
	 * @Override public void run(String... args) throws Exception { ActorPics
	 * actorDetail = getActorPics(); picDao.createMovie(actorDetail); }
	 * 
	 * 
	 * private ActorPics getActorPics() { ActorPics actorPics = new ActorPics();
	 * actorPics.setPicid(31); actorPics.setActorPic("tmtpHero.jpg");
	 * actorPics.setActoressPic("tmtpActress.jpg");
	 * actorPics.setDirectorPic("tmtpDirector.jpg");
	 * actorPics.setMusicPic("tmtpMusic.jpg");
	 * actorPics.setProducerPic("tmtpProducer.jpg"); return actorPics; }
	 */
	  
	 	 

	/*
	 * @Override public void run(String... args) throws Exception { UpcommingMovies
	 * upMovies = getMovieDetails(); upcommingDao.createMovie(upMovies); }
	 * 
	 * 
	 * private UpcommingMovies getMovieDetails() { UpcommingMovies upMovies = new
	 * UpcommingMovies(); upMovies.setMovieId(1); upMovies.setImage("Screem.jpg");
	 * upMovies.setMovieDetails("Horror"); upMovies.setMoviename("Screem"); return
	 * upMovies; }
	 * 
	 */

	/*
	 * @Override public void run(String... args) throws Exception { MovieDetails
	 * movieDetails = getMovieDetails(); movieDao.createMovie(movieDetails); }
	 */

	/*
	 * private MovieDetails getMovieDetails() { MovieDetails movieDetails = new
	 * MovieDetails(); // movieDetails.setMovieId(6); //
	 * movieDetails.setImage("TMTPT.jpg"); //
	 * movieDetails.setMovieDetails("Sci-Fic/Love"); //
	 * movieDetails.setMovieName("The Map of Tiny Perfect Things"); return
	 * movieDetails; }
	 */

}
