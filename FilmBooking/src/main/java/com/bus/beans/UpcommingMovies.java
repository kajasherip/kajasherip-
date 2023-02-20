package com.bus.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "upcomming_shows")
public class UpcommingMovies implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "movie_id")
	private int movieId;
	@Column(nullable = false)
	private String moviename;
	@Column(nullable = false)
	private String image;
	@Column(columnDefinition = "varchar(1000)", nullable = false)
	private String movieDetails;
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMoviename() {
		return moviename;
	}
	public void setMoviename(String moviename) {
		this.moviename = moviename;
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
	
	
	public UpcommingMovies(int movieId, String moviename, String image, String movieDetails) {
		super();
		this.movieId = movieId;
		this.moviename = moviename;
		this.image = image;
		this.movieDetails = movieDetails;
	}
	
	
	public UpcommingMovies(String moviename, String image, String movieDetails) {
		super();
		this.moviename = moviename;
		this.image = image;
		this.movieDetails = movieDetails;
	}
	
	public UpcommingMovies() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "UpcommingMovies [movieId=" + movieId + ", moviename=" + moviename + ", image=" + image
				+ ", movieDetails=" + movieDetails + "]";
	}
	
	
}
