package com.example.demo.controllers;

import com.example.demo.models.Comment;
import com.example.demo.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public Comment addComment (@PathVariable("postId") Long postId, @RequestBody Comment comment,
                               @ApiIgnore @AuthenticationPrincipal String username) {
        return commentService.addComment(postId, comment, username);
    }
}
