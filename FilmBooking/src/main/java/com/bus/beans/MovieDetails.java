package com.bus.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "movies_details")
public class MovieDetails implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="movie_id")
	private long movieId;
	
	@Column(nullable = false)
	private String movieName;
	
	@Column(nullable = false)
	private String image;
	
	@Column(columnDefinition = "varchar(1000) ", nullable = false)
	private String movieDetails;

	@OneToOne
	@PrimaryKeyJoinColumn
	private ActorDetails actorDetails; // Actor class Object
	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMovieDetails() {
		return movieDetails;
	}

	public void setMovieDetails(String movieDetails) {
		this.movieDetails = movieDetails;
	}

	public MovieDetails(long movieId, String movieName, String image, String movieDetails) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.image = image;
		this.movieDetails = movieDetails;
	}

	public MovieDetails(String movieName, String image, String movieDetails) {
		super();
		this.movieName = movieName;
		this.image = image;
		this.movieDetails = movieDetails;
	}

	public MovieDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MovieDetails [movieId=" + movieId + ", movieName=" + movieName + ", image=" + image + ", movieDetails="
				+ movieDetails + "]";
	}
	
}
