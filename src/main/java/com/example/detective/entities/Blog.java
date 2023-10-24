package com.example.detective.entities;

import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;


@Entity
@Getter @Setter @NoArgsConstructor
@JsonIdentityInfo(
        scope = Blog.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "blog")
@Indexed
public class Blog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field(name = "blogId", index = Index.NO, store = Store.YES)
    private long id;

    @Field(index = Index.NO, store = Store.YES)
    private Instant date = Instant.now();

    @NotNull
    @Field(store = Store.YES)
    @Column(columnDefinition="text")
    private String title;

    @Field(index = Index.NO, store = Store.YES)
    private String photo;

    @NotNull
    @Field(store = Store.YES)
    @Column(columnDefinition="text")
    private String content;

    @Field(index = Index.NO, store = Store.YES)
    private boolean isEdited;

}
