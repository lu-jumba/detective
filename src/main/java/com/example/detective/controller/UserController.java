package com.example.detective.controller;



import com.example.detective.entities.Incident;
import com.example.detective.entities.Otp;
import com.example.detective.entities.User;
import com.example.detective.handler.Response;
import com.example.detective.service.UserService;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 *
 * @author Lugwiri
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping ("/createUser")
    public ResponseEntity <Response <Integer>> createUser(@RequestBody User user) throws NoSuchAlgorithmException{
        Response <Integer> response = userService.createUser(user);

        return switch (response.getCode()) {
            case 0 -> new ResponseEntity<>(response, HttpStatus.OK);
            case 101 -> new ResponseEntity<>(response, HttpStatus.CONFLICT);
            default -> new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        };
    }
    
    @PostMapping ("/updatePassword")
    public ResponseEntity <Response <Integer>> updatePassword(@RequestBody String username, String password) throws NoSuchAlgorithmException{
        Response <Integer> response = userService.updatePassword(username, password);

        return switch (response.getCode()) {
            case 0 -> new ResponseEntity<>(response, HttpStatus.OK);
            case 101 -> new ResponseEntity<>(response, HttpStatus.CONFLICT);
            default -> new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        };
    }

    @GetMapping("/username/getUser")
    public ResponseEntity <Response <User>> getUser(
        @PathVariable("username") String username){
        Response <User> response = userService.getUser(username);

        return switch (response.getCode()) {
            case 0 -> new ResponseEntity<>(response, HttpStatus.OK);
            case 101 -> new ResponseEntity<>(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        };
    }
    
    @GetMapping("/username/authUser")
    public ResponseEntity <Response <Boolean>> authUser(
        @PathVariable("username") String username, 
        @RequestParam String password, Otp otp){
        Response <Boolean> response = userService.authUser(username, password, otp);

        return switch (response.getCode()) {
            case 0 -> new ResponseEntity<>(response, HttpStatus.OK);
            case 101 -> new ResponseEntity<>(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        };
    }

    @GetMapping("/username/incidents")
    public ResponseEntity <Response <List<Incident>>> incidents(
        @PathVariable("username") String username, 
        @RequestParam Incident i){

            Response <List<Incident>> response = userService.incidents(username, i);

            return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
    @GetMapping("/users")
    public ResponseEntity <Response <List<User>>> users(){
            Response <List<User>> response = userService.users();

            return new ResponseEntity<>(response, HttpStatus.OK);
    }  

}
