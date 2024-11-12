package com.springlearning.social_media_blog_app.Service;

import com.springlearning.social_media_blog_app.DTO.CommentDto;
import com.springlearning.social_media_blog_app.Entity.Comment;
import com.springlearning.social_media_blog_app.Entity.Post;
import com.springlearning.social_media_blog_app.Exception.ResourceNotFoundException;
import com.springlearning.social_media_blog_app.Repository.CommentRepository;
import com.springlearning.social_media_blog_app.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        //Map commentDto to Comment Entity
        Comment comment = mapDtoToEntity(commentDto);

        //Fetch post from Post repository using Post Id from request URI
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));

        //Set comment to the post
        comment.setPost(post);

        //Save comment to DB
        Comment savedComment = commentRepository.save(comment);

        //Map entity to Dto
        return mapEntityToDto(savedComment);

    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(long postId) {
       List<Comment> commentList = commentRepository.findByPostId(postId);
       //map entity to dto
        List<CommentDto> commentDtoList = commentList.stream().map(comment -> mapEntityToDto(comment)).collect(Collectors.toList());
        return commentDtoList;
    }


    private Comment mapDtoToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setName(comment.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
    private CommentDto mapEntityToDto(Comment savedComment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(savedComment.getId());
        commentDto.setName(savedComment.getName());
        commentDto.setEmail(savedComment.getEmail());
        commentDto.setBody(savedComment.getBody());
        return commentDto;
    }
}
