package com.example.demo.controllers.dto;

import com.example.demo.models.Post;
import com.example.demo.models.PostDtoBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class PostDtoMapper {

    private PostDtoMapper() {}

    public static List<PostDto> mapToPostDtos (List<Post> posts) {
        return posts.stream()
                .map(post -> mapToPostDto(post))
                .collect(Collectors.toList());
    }

    private static PostDto mapToPostDto(Post post) {
        return new PostDtoBuilder()
                .setId(post.getId())
                .setContent(post.getContent())
                .setTitle(post.getTitle())
                .setCreated(post.getCreated())
                .setAuthor(post.getAuthor())
                .build();
    }
}
