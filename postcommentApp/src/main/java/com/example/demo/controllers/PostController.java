package com.example.demo.controllers;

import com.example.demo.controllers.dto.PostDto;
import com.example.demo.controllers.dto.PostDtoMapper;
import com.example.demo.models.Post;
import com.example.demo.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public List<PostDto> getPosts(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return PostDtoMapper.mapToPostDtos(postService.getPosts(pageNumber, sortDirection));
    }
    @GetMapping("/posts/comments")
    public List<Post> getPostsWithComments(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return postService.getPostsWithComments(pageNumber, sortDirection);
    }
    @GetMapping("/my-posts")
    public List<PostDto> getUserPosts(@RequestParam(required = false) Integer page, Sort.Direction sort, @ApiIgnore @AuthenticationPrincipal String user) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return PostDtoMapper.mapToPostDtos(postService.getUserPosts(pageNumber, sortDirection, user));
    }
    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable long id) {
        return postService.getSinglePost(id);
    }
    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post, @ApiIgnore @AuthenticationPrincipal String user) {
        return postService.addPost(post, user);
    }
    @PutMapping("/posts/{id}")
    public Post editPost(@PathVariable long id, @RequestBody Post post, @ApiIgnore @AuthenticationPrincipal String user) {
        return postService.editPost(id, post, user);
    }
    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable long id, @ApiIgnore @AuthenticationPrincipal String user) {
        postService.deletePost(id, user);
    }
}
