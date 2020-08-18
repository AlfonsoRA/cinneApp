package com.alfonso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alfonso.model.Usuarios;

public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {

}
