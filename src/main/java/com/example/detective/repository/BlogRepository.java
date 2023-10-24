package com.example.detective.repository;

import com.example.detective.dto.BlogDto;
import com.example.detective.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface BlogRepository extends JpaRepository<Blog, Long> { 


    @Query("select new com.example.detective.dto.BlogDto(b.id, b.photo, b.content,  b.date, p.isEdited) from Blog b where b = ?1")
    BlogDto findContent(Blog blog);

}
