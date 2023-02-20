package com.bus.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "actor_details")
public class ActorDetails implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long actorsid;
	@Column(nullable = false)
	private String actorName;
	@Column(nullable = false)
	private String actressName;
	@Column(nullable = false)
	private String directerName;
	@Column(nullable = false)
	private String producerName;
	@Column(nullable = false)
	private String musicName;
	
	@OneToOne
	@JoinColumn
	private MovieDetails movieDetails;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private ActorPics actorPics;
	
	public long getActorsid() {
		return actorsid;
	}
	public void setActorsid(long actorsid) {
		this.actorsid = actorsid;
	}
	public String getActorName() {
		return actorName;
	}
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
	public String getActressName() {
		return actressName;
	}
	public void setActressName(String actressName) {
		this.actressName = actressName;
	}
	public String getDirecterName() {
		return directerName;
	}
	public void setDirecterName(String directerName) {
		this.directerName = directerName;
	}
	public String getProducerName() {
		return producerName;
	}
	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}
	public String getMusicName() {
		return musicName;
	}
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	public MovieDetails getMovieDetails() {
		return movieDetails;
	}
	public void setMovieDetails(MovieDetails movieDetails) {
		this.movieDetails = movieDetails;
	}
	public ActorDetails(long actorsid, String actorName, String actressName, String directerName, String producerName,
			String musicName, MovieDetails movieDetails) {
		super();
		this.actorsid = actorsid;
		this.actorName = actorName;
		this.actressName = actressName;
		this.directerName = directerName;
		this.producerName = producerName;
		this.musicName = musicName;
		this.movieDetails = movieDetails;
	}
	public ActorDetails(String actorName, String actressName, String directerName, String producerName,
			String musicName, MovieDetails movieDetails) {
		super();
		this.actorName = actorName;
		this.actressName = actressName;
		this.directerName = directerName;
		this.producerName = producerName;
		this.musicName = musicName;
		this.movieDetails = movieDetails;
	}

	public ActorDetails() {
		super();
	}
	
	@Override
	public String toString() {
		return "ActorDetails [actorsid=" + actorsid + ", actorName=" + actorName + ", actressName=" + actressName
				+ ", directerName=" + directerName + ", producerName=" + producerName + ", musicName=" + musicName
				+ ", movieDetails=" + movieDetails + "]";
	}
	
	
}
