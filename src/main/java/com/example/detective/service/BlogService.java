package com.example.detective.service;

import com.example.detective.handler.Response;
import com.example.detective.handler.ServiceStatus;
import com.example.detective.dto.BlogDto;
import com.example.detective.entities.Blog;
import com.example.detective.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    //fetch post content data
    public Response list(long id){
        Optional<Blog> blog = blogRepository.findById(id);

        if(blog.isEmpty()){
            return new Response(null, ServiceStatus.POST_NOT_FOUND);
        }
        else if(blog.isPresent()){
            return new Response(blog, ServiceStatus.SUCCESS);
        }
        else{
            return new Response(null, ServiceStatus.ERROR);
        }
    }

    //create post
    public Response insert(Blog blog){
        if(blog.getContent() == null || blog.getContent() == ""){
            return new Response(null, ServiceStatus.POST_CONTENT_EMPTY);
        }
        else if(blog.getContent() != null){
            
                return new Response(blogRepository.save(blog), ServiceStatus.SUCCESS);
            
            
        }
        return null;
        
    }

    //edit blog
    public Response edit(Blog blog){
        Optional<Blog> insertedBlog = blogRepository.findById(blog.getId());

        if(blog.getId() == 0){
            return new Response(null, ServiceStatus.POST_DOES_NOT_EXIST);
        }
        else if(blog.getContent() == null || blog.getContent() == ""){
            return new Response(null, ServiceStatus.POST_CONTENT_EMPTY);
        }
        else if(insertedBlog.isEmpty()){
            return new Response(null, ServiceStatus.POST_DOES_NOT_EXIST);
        }
        else if(insertedBlog.isPresent()){
            blog.setPhoto(insertedBlog.get().getPhoto());
            blog.setDate(insertedBlog.get().getDate());
            blog.setEdited(true);

            return new Response(blogRepository.save(blog), ServiceStatus.SUCCESS);
        }
        else{
            return new Response(null, ServiceStatus.ERROR);
        }
    }

    //deleting a blog
    public Response delete(long id){
        Optional<Blog> blog = blogRepository.findById(id);

        if(blog.isEmpty()){
            return new Response(null, ServiceStatus.POST_NOT_FOUND);
        }
        else if(blog.isPresent()){
            blogRepository.deleteById(id);
            return new Response(null, ServiceStatus.SUCCESS);
        }
        else{
            return new Response(null, ServiceStatus.ERROR);
        }
    }

    public Response listContent(long id){
        Optional<Blog> blog = blogRepository.findById(id);

        if(blog.isEmpty()){
            return new Response(null, ServiceStatus.POST_NOT_FOUND);
        }
        else if(blog.isPresent()){
            BlogDto blogContent = blogRepository.findContent(blog.get());

            return new Response(blogContent, ServiceStatus.SUCCESS);
        }
        else {
            return new Response(null, ServiceStatus.ERROR);
        }
    }

}
