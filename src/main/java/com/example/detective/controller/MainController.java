package com.example.detective.controller;

import com.example.detective.dto.MainBlogDto;
import com.example.detective.handler.Response;
import com.example.detective.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/main")
public class MainController {

    @Autowired
    private MainService mainService;

    

    @GetMapping("/{id}")
    public ResponseEntity<Response <List<MainBlogDto>>> listMainBlog(@PathVariable("id") Long id) { //was String
        Response <List<MainBlogDto>> response = mainService.listMainBlog();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/top")
    public ResponseEntity<Response <List<MainBlogDto>>> listMainBlogSortTop() { //was String
        Response <List<MainBlogDto>> response = mainService.listMainBlogSortTop();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/latest")
    public ResponseEntity <Response <List<MainBlogDto>>> listMainBlogSortLatest(){
        Response <List<MainBlogDto>> response = mainService.listMainBlogSortLatest();
        return new ResponseEntity <>(response, HttpStatus.OK);
    }

}
