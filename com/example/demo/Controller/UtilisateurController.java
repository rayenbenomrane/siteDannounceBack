package com.example.demo.Controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Utilisateur;
import com.example.demo.service.UtilisateurService;

@RestController
@RequestMapping("/utilisateurs")
@CrossOrigin(origins = "*")
public class UtilisateurController {
    @Autowired
    private final UtilisateurService utilisateurService;

    
    @Autowired
	private UtilisateurService userserv; 
	@Autowired
	private org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder BCryptPasswordEncoder;
	  @Autowired
	    public UtilisateurController(UtilisateurService utilisateurService) {
	        this.utilisateurService = utilisateurService;
	    }
	  
	  @PostMapping("/login")
	  public ResponseEntity<Object> login(@RequestBody Map<String, String> credentials) {
	    try {
	      String email = credentials.get("email");
	      String mdp = credentials.get("mdp");
	      System.out.println("Received login request. Email: " + email + ", Password: " + mdp);

	      Optional<Utilisateur> user = this.userserv.getUtilisateurByEmail(email);

	      if (user.isPresent()) {
	        if (BCryptPasswordEncoder.matches(mdp, user.get().getPassword())) {
	          // Include role in the response
	          Map<String, Object> response = new HashMap<>();
	          response.put("user", user.get());
	          response.put("role", user.get().getRole());

	          return new ResponseEntity<>(response, HttpStatus.OK);
	        } else {
	          return new ResponseEntity<>("Password not correct", HttpStatus.UNAUTHORIZED);
	        }
	      } else {
	        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	      }
	    } catch (Exception e) {
	      return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
  

	@PostMapping
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        try {
            // Encrypt the password before saving
            String encodedPassword = BCryptPasswordEncoder.encode(utilisateur.getPassword());
            utilisateur.setPassword(encodedPassword);

            Utilisateur createdUtilisateur = utilisateurService.saveUtilisateur(utilisateur);
            return ResponseEntity.ok(createdUtilisateur);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        try {
            List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
            return ResponseEntity.ok(utilisateurs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable int id) {
        try {
            return utilisateurService.getUtilisateurById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Utilisateur> getUtilisateurByName(@PathVariable String name) {
        try {
            Utilisateur utilisateur = utilisateurService.getUtilisateurByName(name);
            return ResponseEntity.ok(utilisateur);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable int id, @RequestBody Utilisateur utilisateur) {
        try {
            // Encrypt the password before updating
            String encodedPassword = BCryptPasswordEncoder.encode(utilisateur.getPassword());
            utilisateur.setPassword(encodedPassword);

            Utilisateur updatedUtilisateur = utilisateurService.updateUtilisateur(utilisateur);
            return ResponseEntity.ok(updatedUtilisateur);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable int id) {
        try {
            utilisateurService.deleteUtilisateurById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUtilisateur(@RequestBody Utilisateur utilisateur) {
        try {
            utilisateurService.deleteUtilisateur(utilisateur);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Utilisateur> getUtilisateurByEmail(@PathVariable String email) {
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurByEmail(email);

        return utilisateur.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
