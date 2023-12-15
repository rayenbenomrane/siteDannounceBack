package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.CommentaireRepo;
import com.example.demo.model.Commentaire;

import jakarta.transaction.Transactional;

@Service

public class CommentaireService {
	 private final CommentaireRepo commentaireRepository;

	    @Autowired
	    
	    public CommentaireService(CommentaireRepo commentaireRepository) {
	        this.commentaireRepository = commentaireRepository;
	    }

	    
	    // Enregistrer un commentaire
	    public Commentaire saveCommentaire(Commentaire commentaire) {
	        return commentaireRepository.save(commentaire);
	    }

	    // Récupérer tous les commentaires
	    public List<Commentaire> getAllCommentaires() {
	        return commentaireRepository.findAll();
	    }

	    // Récupérer un commentaire par son ID
	    public Optional<Commentaire> getCommentaireById(int id) {
	        return commentaireRepository.findById(id);
	    }

	    // Mettre à jour un commentaire
	    public Commentaire updateCommentaire(Commentaire commentaire) {
	        return commentaireRepository.save(commentaire);
	    }

	    // Supprimer un commentaire par son ID
	    public void deleteCommentaireById(int id) {
	        commentaireRepository.deleteById(id);
	    }

	    // Supprimer un commentaire
	    public void deleteCommentaire(Commentaire commentaire) {
	        commentaireRepository.delete(commentaire);
	    }
}
