package com.example.demo.controllers.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class PostDto {
    private long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime created;
}
