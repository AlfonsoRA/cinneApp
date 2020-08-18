package com.alfonso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alfonso.model.Banner;

@Repository
public interface BannersRepository extends JpaRepository<Banner, Integer> {
	
	//SQL //SELECT * FROM Banner WHERE estatus = ?
	//HIBERNATE // SELECT b FROM Banner b WHERE b.estatus= ?
	List<Banner> findByEstatus(String estatus);
	
}
