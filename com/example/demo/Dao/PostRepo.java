package com.example.demo.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Post;


public interface PostRepo  extends JpaRepository<Post,Integer>{
	 List<Post> findByUser_IdUtilisateur(int idUtilisateur);
	 Optional<Post> findByVille(String ville);
}
