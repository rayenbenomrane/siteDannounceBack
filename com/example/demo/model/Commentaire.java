package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Commentaire {

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IdCommentaire;
	private String Contenu;
	
	@ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private Utilisateur user;

    @ManyToOne
    @JoinColumn(name = "idPost")
    private Post post;

	public String getContenu() {
		return Contenu;
	}

	public void setContenu(String contenu) {
		Contenu = contenu;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Commentaire(String contenu, Utilisateur user, Post post) {
		super();
		Contenu = contenu;
		this.user = user;
		this.post = post;
	}
	

}
