package com.example.demo.Controller;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
    	
        Post createdPost = postService.createPost(post.getUser().getIdUtilisateur(),post);
        return ResponseEntity.ok(createdPost);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable int id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable int id, @RequestBody Post post) {
        post.setIdPost(id);
        Post updatedPost = postService.updatePost(post);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable int id) {
        postService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePost(@RequestBody Post post) {
        postService.deletePost(post);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{postId}/images")
    public ResponseEntity<Void> addImagesToPost(@PathVariable int postId, @RequestBody List<String> imageUrls) {
        postService.addImagesToPost(postId, imageUrls);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable int userId) {
        List<Post> posts = postService.getPostsByUserIdUtilisateur(userId);
       return ResponseEntity.ok(posts);
    }
    @GetMapping("/ville/{ville}")
    public ResponseEntity<Post> getPostByVille(@PathVariable String ville) {
        java.util.Optional<Post> post = postService.getPostByVille(ville);

        return post.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    /*@PostMapping("/{postid}/{IdUtilisateur}")
    public ResponseEntity<Post> createPost(@PathVariable int userId, @RequestBody Post post) {
        Post createdPost = postService.createPost(userId, post);

        if (createdPost != null) {
            return ResponseEntity.ok(createdPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

}
