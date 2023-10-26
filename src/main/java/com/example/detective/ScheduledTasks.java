/*package com.example.detective;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.detective.repository.OtpRepository;

@Component
public class ScheduledTasks {
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	@Autowired
	OtpRepository otpRepository;

	@Scheduled(fixedDelayString = "${scheduler.clean.otpdb}")
	public void cleanUpOtpTable() {
		log.info("OTP table cleanup : commencing");
		long count = otpRepository.count();
		log.info("OTP table cleanup : {} records exist", count);
		otpRepository.deleteAll(otpRepository.findByExpiresLessThanEqual(new Date()));
		count = count - otpRepository.count();
		log.info("OTP table cleanup : {} records deleted", count);
		log.info("OTP table cleanup : completed");
	}

}*/
