package com.springlearning.social_media_blog_app.Service;

import com.springlearning.social_media_blog_app.DTO.PostDto;
import com.springlearning.social_media_blog_app.Entity.Post;
import com.springlearning.social_media_blog_app.Exception.ResourceNotFoundException;
import com.springlearning.social_media_blog_app.PayLoad.PostResponse;
import com.springlearning.social_media_blog_app.Repository.PostRepository;
import com.springlearning.social_media_blog_app.SocialMediaBlogAppApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {

        //Map Post Dto to Post Entity
        Post post = mapDtoToEntity(postDto);

        //Save to DB
        Post savedPost = postRepository.save(post);

        //Map Post Entity to Post Dto
       return mapEntityToDto(savedPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

       Pageable pageable;
       if(sortBy != null && sortDir != null) {

          Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                   : Sort.by(sortBy).descending();
           pageable = PageRequest.of(pageNo, pageSize, sort);
        }
         else
        {
            pageable = PageRequest.of(pageNo, pageSize);
        }


        //List<Post> postList = postRepository.findAll();

        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> postList = posts.getContent();

        //Map Post Entity to Post Dto
      //return  postList.stream().map(post -> mapEntityToDto(post)).collect(Collectors.toList());
       List<PostDto> postDtoList = postList.stream().map(post -> mapEntityToDto(post)).collect(Collectors.toList());

       //Customize the Post Resource Response
        PostResponse postResponse = PostResponse
                .builder()
                .content(postDtoList)
                .pageNo(posts.getNumber())
                .pageSize(posts.getSize())
                .totalElements(posts.getTotalElements())
                .totalPages(posts.getTotalPages())
                .isLastPage(posts.isLast())
                .build();

        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post respectivePost = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", String.valueOf(id)));

        //map Entity  to Dto
        return mapEntityToDto(respectivePost);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, long id) {
        Post existingProduct = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        existingProduct.setTitle(postDto.getTitle());
        existingProduct.setContent(postDto.getContent());
        existingProduct.setDescription(postDto.getDescription());
       Post updatedPost = postRepository.save(existingProduct);

       //Map Post Entity to Post Dto
        return mapEntityToDto(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        Post originalPost = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        postRepository.delete(originalPost);
    }

    //Method for Mapping Post Entity to Post Dto
    public PostDto mapEntityToDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }


    //Method for Mapping Post Dto to Post Entity
    public Post mapDtoToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}
