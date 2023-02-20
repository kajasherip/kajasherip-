package com.bus.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bus.beans.ActorPics;

@Repository
public interface ActorPicRepo extends JpaRepository<ActorPics,Long>{

}
