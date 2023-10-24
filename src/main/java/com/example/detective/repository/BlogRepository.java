package com.example.detective.repository;

import com.example.detective.dto.MainBlogDto;
import com.example.detective.dto.BlogContentDto;
import com.example.detective.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> { 


    @Query("select new com.example.detective.dto.BlogContentDto(b.id, b.photo, b.content,  b.date, p.isEdited) from Blog b where b = ?1")
    BlogContentDto findContent(Blog blog);

}
