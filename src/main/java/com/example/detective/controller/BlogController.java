package com.example.detective.controller;

import com.example.detective.handler.Response;
import com.example.detective.entities.Blog;
import com.example.detective.service.BlogService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/*@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    //fetch post content data
    @GetMapping("/{id}")
    public ResponseEntity listById(@PathVariable("id") Long id){
        Response response = blogService.list(id);

        if(response.getCode() == 0){
            return new ResponseEntity (response, HttpStatus.OK);
        }
        else if(response.getCode() == 200){
            return new ResponseEntity (response, HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity (response, HttpStatus.BAD_REQUEST);
        }
    }

    //create/edit post
    @PostMapping
    public ResponseEntity insert(@RequestBody Blog blog){
        Response response = blogService.insert(blog);

        if(response.getCode() == 0){
            return new ResponseEntity(response, HttpStatus.OK);
        }
        else{
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity edit(@RequestBody Blog blog) {
        Response response = blogService.edit(blog);

        if(response.getCode() == 0) {
            return new ResponseEntity(response, HttpStatus.OK);
        }
        else if(response.getCode() == 401){
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }
        else{
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    //deleting a post
    @DeleteMapping("/{blogId}/delete")
    public ResponseEntity delete(@PathVariable("blogId") Long blogId){
        Response response = blogService.delete(blogId);

        if(response.getCode() == 0){
            return new ResponseEntity(response, HttpStatus.OK);
        }
        else if(response.getCode() == 200){
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        else if(response.getCode() == 401){
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }
        else{
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/content/{id}")
    public ResponseEntity listContent(@PathVariable("id") Long id){
        Response response = blogService.listContent(id);

        if(response.getCode() == 0){
            return new ResponseEntity(response, HttpStatus.OK);
        }
        else if(response.getCode() == 200){
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

} */

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;



    @GetMapping("/{id}")
        public ResponseEntity<Response<Blog>> listById(
                @PathVariable("id") Long id
                /*@RequestParam String username*/) {
            Response<Blog> response = blogService.list(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    @PostMapping
    public ResponseEntity<Response<Blog>> insert(@RequestBody Blog blog) {
        Response<Blog> response = blogService.insert(blog);

        return switch (response.getCode()) {
            case 0 -> new ResponseEntity<>(response, HttpStatus.OK);
            case 101 -> new ResponseEntity<>(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        };
    }

    @PutMapping
    public ResponseEntity<Response<Blog>> edit(@RequestBody Blog blog) {
        Response<Blog> response = blogService.edit(blog);

       return switch (response.getCode()) {
            case 0 -> new ResponseEntity<>(response, HttpStatus.OK);
            case 101 -> new ResponseEntity<>(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        };
    }

    @DeleteMapping("/{blogId}/delete")
    public ResponseEntity<Response<Integer>> delete(@PathVariable("blogId") Long blogId) {
        Response<Integer> response = blogService.delete(blogId);
        
        return switch (response.getCode()) {
            case 0 -> new ResponseEntity<>(response, HttpStatus.OK);
            case 101 -> new ResponseEntity<>(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        };
    }

    @GetMapping("/content/{id}")
    public ResponseEntity<Response<Blog>> listContent(@PathVariable("id") Long id) { //was String
        Response<Blog> response = blogService.listContent(id);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}


