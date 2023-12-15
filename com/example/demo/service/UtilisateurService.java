package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.UtilisateurRepo;
import com.example.demo.model.Utilisateur;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UtilisateurService {

	private final UtilisateurRepo utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepo utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
    	
        return utilisateurRepository.save(utilisateur);
    }

    @Transactional
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Transactional
    public Optional<Utilisateur> getUtilisateurById(int id) {
        return utilisateurRepository.findById(id);
    }

    @Transactional
    public Utilisateur getUtilisateurByName(String name) {
        return utilisateurRepository.findByName(name);
    }
    @Transactional
    public Optional<Utilisateur> getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    @Transactional
    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    
    public void deleteUtilisateurById(int id) {
        utilisateurRepository.deleteById(id);
    }

  
    public void deleteUtilisateur(Utilisateur utilisateur) {
        utilisateurRepository.delete(utilisateur);
    }
}
