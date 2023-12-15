package com.example.demo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Utilisateur  {
@Id	
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int idUtilisateur;
private String name;
private String email;
private String password;
private String Role;

@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
@JsonManagedReference
private List<Post> posts = new ArrayList<>();
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
private List<Commentaire> comments = new ArrayList<>();

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public List<Post> getPosts() {
	return posts;
}
public void setPosts(List<Post> posts) {
	this.posts = posts;
}
public Utilisateur(String name, String email, String password, List<Post> posts) {
	super();
	this.name = name;
	this.email = email;
	this.password = password;
	this.posts = posts;
	this.Role="User";
}

public Utilisateur(String name, String email, String password) {
	super();
	this.name = name;
	this.email = email;
	this.password = password;
}
public Utilisateur() {
	super();
}
public String getRole() {
	return Role;
}
public void setRole(String role) {
	Role = role;
}
public int getIdUtilisateur() {
	return idUtilisateur;
}
public void setIdUtilisateur(int idUtilisateur) {
	this.idUtilisateur = idUtilisateur;
}
public List<Commentaire> getComments() {
	return comments;
}
public void setComments(List<Commentaire> comments) {
	this.comments = comments;
}


}
