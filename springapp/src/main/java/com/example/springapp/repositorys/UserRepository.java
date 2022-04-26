package com.example.springapp.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springapp.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

	@Query(value = "SELECT * FROM user WHERE email = ?1 and password = ?2", nativeQuery = true)
	public List<UserEntity> findUserByEmailAndPassword(String email, String password);
	
	
	@Query(value = "SELECT * FROM user WHERE email = ?1", nativeQuery = true)
	public Object findByEmail(String email);
	
	
}
