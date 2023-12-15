package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
	public class Image {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int idImage;

	    
	    
	    private String imageUrl;

	    @ManyToOne(cascade = CascadeType.REMOVE)
	    @JoinColumn(name = "idPost")
	    @JsonBackReference
	    private Post post;

	    public Image() {
	        // default constructor
	    }

	   

		public int getIdImage() {
			return idImage;
		}



		public void setIdImage(int idImage) {
			this.idImage = idImage;
		}



		public String getImageUrl() {
			return imageUrl;
		}



		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}



		public Image(int idImage,String imageUrl, Post post) {
			super();
			this.idImage = idImage;
			this.imageUrl = imageUrl;
			this.post = post;
		}



		public Post getPost() {
			return post;
		}

		public void setPost(Post post) {
			this.post = post;
		}

	    
	}


