package com.springlearning.social_media_blog_app.Service;


import com.springlearning.social_media_blog_app.DTO.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {

    PostDto createPost(PostDto postDto);

     List<PostDto> getAllPosts();

    PostDto getPostById(long id);

    PostDto updatePostById(PostDto postDto, long id);

    void deletePostById(long id);
}
