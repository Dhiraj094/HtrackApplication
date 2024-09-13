package com.ktpl.mitadt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ktpl.mitadt.entity.HtUser;

/**
 * Htrack User Repository
 * 
 */
public interface UserRepository extends JpaRepository<HtUser, Integer> {

	/// TEST
	HtUser findByLoginAndPassword(String login, String password);

	List<HtUser> findByLogin(String login);

	List<HtUser> findByLoginAndIdNot(String login, Integer id);

	@Query(nativeQuery = true, value = "SELECT * FROM ht_user WHERE login = ? and id != ?")
	List<HtUser> getUserByLoginAndNotAvailableinID(String login, Integer id);

	@Query(nativeQuery = true, value = "SELECT * FROM ht_user WHERE login = ? ")
	List<HtUser> getUserByLogin(String login);

}