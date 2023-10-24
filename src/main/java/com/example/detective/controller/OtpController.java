package com.example.detective.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.detective.entities.Otp;
import com.example.detective.repository.OtpRepository;


@RestController
public class OtpController {
	private static Logger log = LoggerFactory.getLogger(OtpController.class);
	@Autowired
	private OtpRepository otpRepository;
	private final Long expiryInterval = 5L * 60 * 1000; //5 minutes
	private String verificationResult;

	private Integer generateCode(){
		final Integer min = 100000;
		final Integer max = 999999;
		return (int) Math.floor(Math.random()*(max-min+1)+min);
	}

	@GetMapping("/otp/create/{id}") //Create a OTP with the default expiry value
	public @ResponseBody Otp getOTP(@PathVariable Integer id) {
		log.info("{} getOTP request received with id = {}", OtpController.class.getName(),id );
		Otp otp = new Otp();
		//delete existing OTP if it exists
		otpRepository.findById(id)
		.ifPresent( entity -> 
			otpRepository.delete(entity)
		);
		otp.setId(id);
		otp.setCode(generateCode());
		otp.setExpires(new Date(System.currentTimeMillis()+expiryInterval));
		otpRepository.save(otp);
		return otp;
	}

	@GetMapping("/otp/create/{id}/{lifetime}") //Create a OTP with a custom expiry value
	public @ResponseBody Otp getOTPWithLifetime(@PathVariable Integer id,@PathVariable Long lifetime) {
		log.info("{} getOTP request received with id = {}", OtpController.class.getName(),id );
		Otp otp = new Otp();
		//delete existing OTP if it exists
		otpRepository.findById(id)
		.ifPresent( entity -> 
			otpRepository.delete(entity)
		);
		otp.setId(id);
		otp.setCode(generateCode());
		otp.setExpires(new Date(System.currentTimeMillis()+lifetime));
		otpRepository.save(otp);
		return otp;
	}

	@PostMapping("/otp/verify")
	public @ResponseBody String verifyOTP(@RequestBody Otp otp) {
		otpRepository.findByIdAndCode(otp.getId(),otp.getCode())
			.ifPresentOrElse(c -> {
				if (c.getExpires().before(new Date())) verificationResult="failed"; //Already Expired
				else verificationResult="ok";
				otpRepository.delete(c);
				}, 
							() -> {
				verificationResult="failed";} );
		return verificationResult;
	}
}
