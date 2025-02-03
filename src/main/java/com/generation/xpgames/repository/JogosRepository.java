package com.generation.xpgames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.xpgames.model.Jogos;



public interface JogosRepository extends JpaRepository<Jogos, Long>{

	
	
	public List<Jogos> findAllByPlataformaContainingIgnoreCase(@Param("plataforma")String plataforma );
}
