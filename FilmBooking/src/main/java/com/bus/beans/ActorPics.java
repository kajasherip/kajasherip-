package com.bus.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "actor_pictures")
public class ActorPics implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long picid;
	@Column(nullable = false)
	private String actorPic;
	@Column(nullable = false)
	private String actoressPic;
	@Column(nullable = false)
	private String directorPic;
	@Column(nullable = false)
	private String producerPic;
	@Column(nullable = false)
	private String musicPic;
	
	@OneToOne
	@JoinColumn
	private ActorDetails actorDetails;

	public long getPicid() {
		return picid;
	}

	public void setPicid(long picid) {
		this.picid = picid;
	}

	public String getActorPic() {
		return actorPic;
	}

	public void setActorPic(String actorPic) {
		this.actorPic = actorPic;
	}

	public String getActoressPic() {
		return actoressPic;
	}

	public void setActoressPic(String actoressPic) {
		this.actoressPic = actoressPic;
	}

	public String getDirectorPic() {
		return directorPic;
	}

	public void setDirectorPic(String directorPic) {
		this.directorPic = directorPic;
	}

	public String getProducerPic() {
		return producerPic;
	}

	public void setProducerPic(String producerPic) {
		this.producerPic = producerPic;
	}

	public String getMusicPic() {
		return musicPic;
	}

	public void setMusicPic(String musicPic) {
		this.musicPic = musicPic;
	}

	public ActorDetails getActorDetails() {
		return actorDetails;
	}

	public void setActorDetails(ActorDetails actorDetails) {
		this.actorDetails = actorDetails;
	}
	
	public ActorPics() {
		super();
	}

	public ActorPics(long picid, String actorPic, String actoressPic, String directorPic, String producerPic,
			String musicPic, ActorDetails actorDetails) {
		super();
		this.picid = picid;
		this.actorPic = actorPic;
		this.actoressPic = actoressPic;
		this.directorPic = directorPic;
		this.producerPic = producerPic;
		this.musicPic = musicPic;
		this.actorDetails = actorDetails;
	}

	public ActorPics(String actorPic, String actoressPic, String directorPic, String producerPic, String musicPic,
			ActorDetails actorDetails) {
		super();
		this.actorPic = actorPic;
		this.actoressPic = actoressPic;
		this.directorPic = directorPic;
		this.producerPic = producerPic;
		this.musicPic = musicPic;
		this.actorDetails = actorDetails;
	}

	@Override
	public String toString() {
		return "ActorPics [picid=" + picid + ", actorPic=" + actorPic + ", actoressPic=" + actoressPic
				+ ", directorPic=" + directorPic + ", producerPic=" + producerPic + ", musicPic=" + musicPic
				+ ", actorDetails=" + actorDetails + "]";
	}
	
	
}
