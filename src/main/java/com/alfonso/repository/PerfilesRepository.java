package com.alfonso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alfonso.model.Perfil;

@Repository
public interface PerfilesRepository extends JpaRepository<Perfil, Integer> {

	List<Perfil> findByCuenta(String cuenta);
}
