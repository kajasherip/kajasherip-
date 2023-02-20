package com.bus.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bus.beans.UpcommingMovies;

@Repository
public interface UpcomMovie extends JpaRepository<UpcommingMovies, Long>{

	//List<UpcommingMovies> findAll(long id);
}
