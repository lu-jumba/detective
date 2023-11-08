package com.example.detective.repository;

import com.example.detective.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface BlogRepository extends JpaRepository<Blog, Long> { 


    @Query("SELECT b FROM Blog b WHERE b.content = ?1")
    Blog findContent(Blog blog);

}
