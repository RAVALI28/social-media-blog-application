package com.springlearning.social_media_blog_app.Controller;

import com.springlearning.social_media_blog_app.DTO.CommentDto;
import com.springlearning.social_media_blog_app.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //Create a new Comment (/api/v1/posts/{postId}/comments)

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") long postId, @RequestBody CommentDto commentDto){

        CommentDto createdCommentDto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(createdCommentDto, HttpStatus.CREATED);

    }

    //Get all comments (/api/v1/posts/{postId}/comments)

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> fetchAllCommentsByPostId(@PathVariable("postId") long postId){
       List<CommentDto> commentDtoList = commentService.getAllCommentsByPostId(postId);
       return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }
}
