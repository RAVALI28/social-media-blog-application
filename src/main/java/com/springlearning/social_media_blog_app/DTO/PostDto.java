package com.springlearning.social_media_blog_app.DTO;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostDto {

    private Long id;
    private String title;
    private String description;
    private String content;

}
