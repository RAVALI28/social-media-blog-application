package com.springlearning.social_media_blog_app.Exception;

public class ResourceNotFoundException extends RuntimeException{

    //resource
    //fieldName
    //fieldValue

    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        //Post not found with id :: 1(Message will be printed in this format
        super(String.format("%s not found with %s :: %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }


}
