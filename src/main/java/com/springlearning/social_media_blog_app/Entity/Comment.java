package com.springlearning.social_media_blog_app.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //name
    private String name;

    //email
    private String email;

    //body
    private String body;

    //Mapping between comments and posts(ManyToOne)
    //Comment table is managing the relationship between post and comment table

    //fetch-lazy will not provide the post details when we fetch the comment,
    // hence the code performance is also high.
    //fetch-eagrly will provide the post details when we fetch the comment unnecessarily,
    // hence the code performance is low.
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;


}
