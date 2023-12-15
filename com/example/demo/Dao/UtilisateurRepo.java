package com.example.demo.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Utilisateur;

public interface UtilisateurRepo extends JpaRepository<Utilisateur,Integer>{
	
	
	List<Utilisateur> findAll();

    
    Utilisateur findByName(String name);
    Optional<Utilisateur> findByEmail(String email);
	

}
