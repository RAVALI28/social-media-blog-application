package com.springlearning.social_media_blog_app.DTO;

import lombok.Data;

@Data
public class PostDto {

    private Long id;
    private String title;
    private String description;
    private String content;
}
