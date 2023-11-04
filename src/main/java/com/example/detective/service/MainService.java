package com.example.detective.service;

import com.example.detective.handler.Response;
import com.example.detective.handler.ServiceStatus;
import com.example.detective.dto.MainBlogDto;
//import com.example.detective.entities.Blog;
import com.example.detective.repository.MainRepository;
import com.example.detective.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
//import java.security.Principal;
import java.util.List;
//import java.util.Optional;

@Service
@Transactional
public class MainService {

    @Autowired
    private MainRepository mainRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private SearchService searchService;

    public Response listMainBlog(){
        List<MainBlogDto> mainBlogs = mainRepository.findBy();

        return new Response(mainBlogs, ServiceStatus.SUCCESS);
    }

    public Response listMainBlogSortTop(){
        List<MainBlogDto> mainBlogs = mainRepository.findAllSort(Sort.by(Sort.Direction.DESC, "date"));

        return new Response(mainBlogs, ServiceStatus.SUCCESS);
    }

    public Response listMainBlogSortLatest(){
        List<MainBlogDto> mainBlogs = mainRepository.findAllSort(Sort.by(Sort.Direction.DESC, "date"));

        return new Response(mainBlogs, ServiceStatus.SUCCESS);
    }

    public Response listByKeyword(String query){
        List<MainBlogDto> mainBlogs = searchService.getBlogByKeyword(query);


        return new Response(mainBlogs, ServiceStatus.SUCCESS);
    }

  

}
