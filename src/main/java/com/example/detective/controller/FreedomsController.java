package com.example.detective.controller;


//import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.detective.entities.Freedoms;
//import com.example.detective.entities.User;
import com.example.detective.repository.FreedomsRepository;
//import com.example.detective.repository.UserRepository;


@RestController
@RequestMapping("/api/freedoms")
public class FreedomsController {
	private static Logger log = LoggerFactory.getLogger(FreedomsController.class);
	@Autowired
	private FreedomsRepository freedomsRepository;
	//private UserRepository userRepository;
	//private final Long expiryInterval = 5L * 60 * 1000; //5 minutes
	//private String verificationResult;

	/*private Integer generateUniqueId(){
		final Integer min = 100000;
		final Integer max = 999999;
		return (int) Math.floor(Math.random()*(max-min+1)+min);
	}*/

	@GetMapping("/mint/{id}") //Mint -  Mint creates a new unspent freedoms owned by the minter
	public @ResponseBody Freedoms mintFreedoms(@PathVariable Integer uniqueId, @RequestBody String username) {
		log.info("{} mintFreedoms transaction created with id = {}", FreedomsController.class.getName(),uniqueId, username );
		Freedoms freedoms = new Freedoms();
		//update existing Freedoms if it exists
		freedomsRepository.findById(uniqueId)
		.ifPresent (f -> {
				f.setAmount((float) (freedoms.getAmount()+0.001)); //add new freedoms to existing ones
				//User u = userRepository.findByUsername(username);
				//u.setFreedoms(u.getFreedoms()+);
				freedomsRepository.save(f);
				});
			
		freedoms.setUniqueId(uniqueId);
		freedoms.setUsername(username);
        freedoms.setAmount((float) 0.001);
		//otp.setExpires(new Date(System.currentTimeMillis()+expiryInterval));
		freedomsRepository.save(freedoms);
		return freedoms;
	}

	/*@GetMapping("/create/{id}/{lifetime}") //Create a OTP with a custom expiry value
	public @ResponseBody Freedoms mintFreedomsWithLifetime(@PathVariable Integer uniqueId,@PathVariable Long lifetime, @RequestBody String minter) {
		log.info("{} mintFreedoms transaction created with id = {}", FreedomsController.class.getName(),uniqueId, minter );
		Freedoms freedoms = new Freedoms();
		//delete existing Freedoms if it exists
		freedomsRepository.findById(uniqueId)
		.ifPresent( entity -> 
			freedomsRepository.delete(entity)
		);
		freedoms.setUniqueId(uniqueId);
		freedoms.setMinter(minter);
		freedoms.setAmount((float) 0.001);
		freedoms.setExpires(new Date(System.currentTimeMillis()+lifetime));
		freedomsRepository.save(freedoms);
		return freedoms;
	}

	@PostMapping("/verify")
	public @ResponseBody String verifyFreedoms(@RequestBody Freedoms freedoms) {
		freedomsRepository.findByUniqueIdAndMinter(freedoms.geId(),freedoms.getMinter())
			.ifPresentOrElse(c -> {
				if (c.getExpires().before(new Date())) verificationResult="failed"; //Already Expired
				else verificationResult="ok";
				freedomsRepository.delete(c);
				}, 
							() -> {
				verificationResult="failed";} );
		return verificationResult;
	}*/
}

