package com.springlearning.social_media_blog_app.Service;

import com.springlearning.social_media_blog_app.DTO.CommentDto;
import com.springlearning.social_media_blog_app.Entity.Comment;

import java.util.List;
import java.util.Set;

public interface CommentService {

    public CommentDto createComment(long postId, CommentDto commentDto);

    public List<CommentDto> getAllCommentsByPostId(long postId);
}
