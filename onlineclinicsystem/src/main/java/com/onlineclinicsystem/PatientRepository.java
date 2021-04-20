package com.onlineclinicsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {

//	@Modifying
//	@Query("update Patient p set u.firstname = ?1, u.lastname = ?2 where u.id = ?3")
//	void setUserInfoById(String firstname, String lastname, Integer userId);

	@Query("SELECT p from Patient p WHERE p.email = :email")
	Patient findByEmail(@Param("email") String email);
}

/*
 * @Query("select u from User u where u.email= :email") dynamic email lana h
 * public User getUserByUserEmail(@Param("email") String email); param use kr k
 * bind kra dynmc eml
 */