package com.springlearning.social_media_blog_app.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private long id;
    private String name;
    private String email;
    private String body;
}
