package com.example.detective.repository;

import com.example.detective.dto.MainBlogDto;
import com.example.detective.entities.Blog;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MainRepository extends JpaRepository<Blog, Long> {

    @Query("select new com.example.detective.dto.MainBlogDto(b.id, b.photo, b.content,  b.date, b.isEdited) from Blog b")
    List<MainBlogDto> findBy();

    @Query("select new com.example.detective.dto.MainBlogDto(b.id, b.photo, b.content,  b.date, b.isEdited) from Blog b")
    List<MainBlogDto> findAllSort(Sort sort);

}
