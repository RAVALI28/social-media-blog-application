package com.springlearning.social_media_blog_app.Controller;



import com.springlearning.social_media_blog_app.DTO.PostDto;
import com.springlearning.social_media_blog_app.PayLoad.PostResponse;
import com.springlearning.social_media_blog_app.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    //(/api/posts)
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
       PostDto savedPostDto = postService.createPost(postDto);
       return new ResponseEntity(savedPostDto, HttpStatus.CREATED);

    }

    //(/api/posts)
    //Pagination and sorting
    @GetMapping
    public PostResponse getAllPosts(
         @RequestParam(value = "pageNo",defaultValue = "0", required = false) int pageNo,
         @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
         @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
         @RequestParam(value = "sortDir", defaultValue = "id", required = false) String sortDir
    )  {

        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);

    }

    //(/api/posts/{id})
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id){
       return ResponseEntity.ok(postService.getPostById(id));
    }

    //(/api/posts/{id})
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable long id){
       return ResponseEntity.ok(postService.updatePostById(postDto, id));
    }

    //(//(/api/posts/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable long id){
        postService.deletePostById(id);
        return ResponseEntity.ok("Deleted Succesfully Post resource ::" +id);
    }
}
