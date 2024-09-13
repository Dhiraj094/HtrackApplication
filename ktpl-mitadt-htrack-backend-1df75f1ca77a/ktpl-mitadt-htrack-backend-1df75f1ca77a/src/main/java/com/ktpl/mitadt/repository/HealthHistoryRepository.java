package com.ktpl.mitadt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.ktpl.mitadt.entity.HtHealthHistory;

/**
 * Health History Repository
 * 
 */
public interface HealthHistoryRepository extends JpaRepository<HtHealthHistory, Integer> {

	@Query(value ="SELECT h FROM HtHealthHistory h where h.htUser.id = :userId")
	List<HtHealthHistory> getHealthHistoryForUser(Integer userId);
	
}