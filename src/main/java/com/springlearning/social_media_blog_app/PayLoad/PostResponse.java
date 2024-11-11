package com.springlearning.social_media_blog_app.PayLoad;

import com.springlearning.social_media_blog_app.DTO.PostDto;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostResponse {

    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLastPage;
}
