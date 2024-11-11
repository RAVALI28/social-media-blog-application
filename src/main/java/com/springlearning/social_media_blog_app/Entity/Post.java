package com.springlearning.social_media_blog_app.Entity;


import jakarta.persistence.*;
import lombok.*;
import java.lang.String;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Post")
public class Post {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

}
