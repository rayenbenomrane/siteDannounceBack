package com.example.demo.Dao;

import com.example.demo.model.Image;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Integer> {
}