/*package com.example.detective.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.detective.entities.Otp;


public interface OtpRepository extends JpaRepository <Otp,Integer> {
	public Iterable<Otp> findByExpiresLessThanEqual(Date expires);

	public Optional<Otp> findByIdAndCode(Integer id, Integer code);
}*/
