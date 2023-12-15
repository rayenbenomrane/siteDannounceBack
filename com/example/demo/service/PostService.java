package com.example.demo.service;

import com.example.demo.Dao.PostRepo;
import com.example.demo.Dao.UtilisateurRepo;
import com.example.demo.model.Image;
import com.example.demo.model.Post;
import com.example.demo.model.Utilisateur;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {

    private final PostRepo postRepository;
    @Autowired
    private UtilisateurRepo utilisateurRepository;

    @Autowired
    private EntityManager entityManager;
    @Autowired
    public PostService(PostRepo postRepository) {
        this.postRepository = postRepository;
    }
    @Transactional
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(int id) {
        return postRepository.findById(id);
    }

    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    public void deletePostById(int id) {
        postRepository.deleteById(id);
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    public void addImagesToPost(int postId, List<String> imageDatas) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        optionalPost.ifPresent(post -> {
            post.getImages().clear(); // Clear existing images
            for (String imageData : imageDatas) {
                Image image = new Image();
                image.setImageUrl(imageData);
                image.setPost(post);
                post.getImages().add(image);
            }
            postRepository.save(post);
        });
    }
   
    @Transactional
    public Post createPost(int userId, Post post) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElse(null);

        if (utilisateur != null) {
            post.setUser(utilisateur);
            return postRepository.save(post);
        }

        return null; 
    }
    public List<Post> getPostsByUserIdUtilisateur(int idUtilisateur) {
        return postRepository.findByUser_IdUtilisateur(idUtilisateur);
    }
    public Optional<Post> getPostByVille(String ville) {
        return postRepository.findByVille(ville);
    }
}
