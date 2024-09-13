package com.ktpl.mitadt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ktpl.mitadt.entity.HtImage;

/**
 * Health History Repository
 * 
 */
public interface ImageRepository extends JpaRepository<HtImage, Integer> {

}