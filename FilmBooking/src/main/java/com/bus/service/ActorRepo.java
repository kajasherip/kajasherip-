package com.bus.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bus.beans.ActorDetails;

@Repository
public interface ActorRepo extends JpaRepository<ActorDetails, Long>{

}
