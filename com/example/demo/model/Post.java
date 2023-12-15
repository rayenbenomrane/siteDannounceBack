package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  idPost;
	private String Titre;
	private String Description;
	private String Prix;
	private String ville;
	private String Statut;
	
	
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
    @JoinColumn(name = "idUtilisateur")
	@JsonBackReference
    private Utilisateur user;
	 @OneToMany(mappedBy = "post")
	    private List<Commentaire> comments = new ArrayList<>();
	 @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	 @JsonManagedReference
	    private List<Image> images = new ArrayList<>();
	public int getIdPost() {
		return idPost;
	}
	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}
	public String getTitre() {
		return Titre;
	}
	public void setTitre(String titre) {
		Titre = titre;
	}
	public String getPrix() {
		return Prix;
	}
	public void setPrix(String prix) {
		Prix = prix;
	}
	public Utilisateur getUser() {
		return user;
	}
	public void setUser(Utilisateur user) {
		this.user = user;
	}
	public List<Commentaire> getComments() {
		return comments;
	}
	public void setComments(List<Commentaire> comments) {
		this.comments = comments;
	}
	public Post(String titre, String description, String prix,String ville,String statut,Utilisateur user) {
		super();
		Titre = titre;
		setDescription(description);
		Prix = prix;
		this.user = user;
		this.ville=ville;
		this.Statut=statut;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public Post() {
		super();
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public String getStatut() {
		return Statut;
	}
	public void setStatut(String statut) {
		this.Statut = statut;
	}
	
	
}
