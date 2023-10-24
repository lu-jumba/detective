package com.example.detective.service;

import com.example.detective.dto.MainBlogDto;
import com.example.detective.entities.Blog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {

    private final EntityManager em;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void initiateIndexing() throws InterruptedException{
        log.info("Initiating search indexing...");

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        fullTextEntityManager.createIndexer().startAndWait();

        log.info("Search index initiated");
    }

    public List<MainBlogDto> getBlogByKeyword(String keyword){

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);

        QueryBuilder qb = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Blog.class)
                .get();

        Query query = qb.keyword().fuzzy()
                .onField("content")
                .matching(keyword)
                .createQuery();

        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, Blog.class);
        fullTextQuery.setProjection("id", "title", "photo", "content",  "date", "isEdited");

        return getBlogList(fullTextQuery.getResultList());

    }


    private List<MainBlogDto> getBlogList(List<Object[]> blogs){
        List<MainBlogDto> blogList = new ArrayList<>();
        for(Object[] objects : blogs){
            MainBlogDto mainBlogDto = new MainBlogDto(
                    (Long) objects[0],
                    (String) objects[2],
                    (String) objects[3],
                    null, (Instant) objects[4],
                    (Boolean) objects[7]);
            blogList.add(mainBlogDto);
        }
        return blogList;
    }

}
