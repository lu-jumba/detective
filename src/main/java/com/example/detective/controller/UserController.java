package com.example.detective.controller;



import com.example.detective.entities.Incident;
//import com.example.detective.entities.Otp;
import com.example.detective.entities.User;
import com.example.detective.handler.Response;
import com.example.detective.service.UserService;
import java.security.NoSuchAlgorithmException;
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
    public ResponseEntity createUser(@RequestBody User user) throws NoSuchAlgorithmException{
        Response response = userService.createUser(user);

        return switch (response.getCode()) {
            case 0 -> new ResponseEntity(response, HttpStatus.OK);
            case 101 -> new ResponseEntity(response, HttpStatus.CONFLICT);
            default -> new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        };
    }
    
    @PostMapping ("/updatePassword")
    public ResponseEntity updatePassword(@RequestBody String username, String password) throws NoSuchAlgorithmException{
        Response response = userService.updatePassword(username, password);

        return switch (response.getCode()) {
            case 0 -> new ResponseEntity(response, HttpStatus.OK);
            case 101 -> new ResponseEntity(response, HttpStatus.CONFLICT);
            default -> new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        };
    }

    @GetMapping("/username/getUser")
    public ResponseEntity getUser(@PathVariable("username") String username){
        Response response = userService.getUser(username);

        return switch (response.getCode()) {
            case 0 -> new ResponseEntity(response, HttpStatus.OK);
            case 101 -> new ResponseEntity(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        };
    }
    
    @GetMapping("/username/authUser")
    public ResponseEntity authUser(@PathVariable("username") String username, String password){
        Response response = userService.authUser(username, password);

        return switch (response.getCode()) {
            case 0 -> new ResponseEntity(response, HttpStatus.OK);
            case 101 -> new ResponseEntity(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        };
    }

    @GetMapping("/username/incidents")
    public ResponseEntity incidents(@PathVariable("username") String username, Incident i){
        return new ResponseEntity(userService.incidents(username, i), HttpStatus.OK);
    }
    
    
    @GetMapping("/userId/users")
    public ResponseEntity users(@PathVariable("userId") Long userId){
        return new ResponseEntity(userService.users(userId), HttpStatus.OK);
    }  

}
