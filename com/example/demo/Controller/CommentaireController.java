package com.example.demo.Controller;

import com.example.demo.model.Commentaire;
import com.example.demo.service.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commentaires")
public class CommentaireController {

    private final CommentaireService commentaireService;

    @Autowired
    public CommentaireController(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }

    // Endpoint pour créer un commentaire (POST)
    @PostMapping
    public ResponseEntity<Commentaire> createCommentaire(@RequestBody Commentaire commentaire) {
        Commentaire createdCommentaire = commentaireService.saveCommentaire(commentaire);
        return ResponseEntity.ok(createdCommentaire);
    }

    // Endpoint pour récupérer tous les commentaires (GET)
    @GetMapping
    public ResponseEntity<List<Commentaire>> getAllCommentaires() {
        List<Commentaire> commentaires = commentaireService.getAllCommentaires();
        return ResponseEntity.ok(commentaires);
    }

    // Endpoint pour récupérer un commentaire par son ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Commentaire> getCommentaireById(@PathVariable int id) {
        return commentaireService.getCommentaireById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint pour mettre à jour un commentaire (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Commentaire> updateCommentaire(@PathVariable int id, @RequestBody Commentaire commentaire) {
        
        Commentaire updatedCommentaire = commentaireService.updateCommentaire(commentaire);
        return ResponseEntity.ok(updatedCommentaire);
    }

    // Endpoint pour supprimer un commentaire par son ID (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentaire(@PathVariable int id) {
        commentaireService.deleteCommentaireById(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint pour supprimer un commentaire (DELETE)
    @DeleteMapping
    public ResponseEntity<Void> deleteCommentaire(@RequestBody Commentaire commentaire) {
        commentaireService.deleteCommentaire(commentaire);
        return ResponseEntity.noContent().build();
    }
}
