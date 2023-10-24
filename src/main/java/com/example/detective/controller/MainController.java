package com.example.detective.controller;

import com.example.detective.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/main")
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping
    public ResponseEntity listMainBlog(){
        return new ResponseEntity(mainService.listMainBlog(), HttpStatus.OK);
    }

    @GetMapping("/top")
    public ResponseEntity listMainBlogTop(){
        return new ResponseEntity(mainService.listMainBlogSortTop(), HttpStatus.OK);
    }

    @GetMapping("/latest")
    public ResponseEntity listMainBlogLatest(){
        return new ResponseEntity(mainService.listMainBlogSortLatest(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity listByKeyword(@RequestParam String q){
        return new ResponseEntity(mainService.listByKeyword(q), HttpStatus.OK);
    }

}
