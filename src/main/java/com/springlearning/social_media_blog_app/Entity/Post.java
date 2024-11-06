package com.springlearning.social_media_blog_app.Entity;


import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Posts")
public class Post {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "Title")
    private String title;

    @Column(name = "Description")
    private String description;

    @Column(name = "Content")
    private String content;

}
