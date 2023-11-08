package com.example.detective.service;

import com.example.detective.handler.Response;
import com.example.detective.handler.ServiceStatus;
import com.example.detective.dto.MainBlogDto;
import com.example.detective.repository.MainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MainService {

    @Autowired
    private MainRepository mainRepository;


    public Response <List<MainBlogDto>> listMainBlog(){
        List<MainBlogDto> mainBlogs = mainRepository.findBy();

        return new Response<>(mainBlogs, ServiceStatus.SUCCESS);
    }

    public Response <List<MainBlogDto>> listMainBlogSortTop(){
        List<MainBlogDto> mainBlogs = mainRepository.findAllSort(Sort.by(Sort.Direction.DESC, "date"));

        return new Response<>(mainBlogs, ServiceStatus.SUCCESS);
    }

    public Response <List<MainBlogDto>> listMainBlogSortLatest(){
        List<MainBlogDto> mainBlogs = mainRepository.findAllSort(Sort.by(Sort.Direction.DESC, "date"));

        return new Response<>(mainBlogs, ServiceStatus.SUCCESS);
    }  

}
