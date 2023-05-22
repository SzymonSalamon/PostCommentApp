package com.example.demo.models;

import com.example.demo.controllers.dto.PostDto;

import java.time.LocalDateTime;

public class PostDtoBuilder {
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
    private String author;

    public PostDtoBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public PostDtoBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public PostDtoBuilder setContent(String content) {
        this.content = content;
        return this;
    }
    public PostDtoBuilder setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public PostDtoBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }
    public PostDto build() {
        PostDto post = new PostDto();
        post.setId(id);
        post.setTitle(title);
        post.setContent(content);
        post.setCreated(created);
        post.setAuthor(author);
        return post;
    }
}
