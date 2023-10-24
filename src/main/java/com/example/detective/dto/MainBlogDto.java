package com.example.detective.dto;

import lombok.Getter;

import java.time.Instant;

@Getter
public class MainBlogDto {

    private long id;
    private String title;
    private String photo;
    private String content;
    private Instant date;
    private Boolean isEdited;
   

    public MainBlogDto(long id, String title, String photo, String content,  Instant date, Boolean isEdited){
        this.id = id;
        this.title = title;
        this.photo = photo;
        this.content = content;
        this.date = date;
        this.isEdited = isEdited;
    }

}