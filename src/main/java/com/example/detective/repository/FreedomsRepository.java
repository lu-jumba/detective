package com.example.detective.repository;

//import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.detective.entities.Freedoms;


public interface FreedomsRepository extends JpaRepository <Freedoms, Integer> {
	//public Iterable<Freedoms> findByExpiresLessThanEqual(Date expires);

	public Optional<Freedoms> findByUniqueIdAndMinter(Integer uniqueId, String username);
}
