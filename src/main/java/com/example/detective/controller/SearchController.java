package com.example.detective.controller;

import com.example.detective.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping
    private ResponseEntity getBlogByKeyword(@RequestParam String q){
        return new ResponseEntity(searchService.getBlogByKeyword(q), HttpStatus.OK);
    }

}
