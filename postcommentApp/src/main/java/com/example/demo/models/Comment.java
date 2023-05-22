package com.example.demo.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private long id;
    @ApiModelProperty(hidden = true)
    private long postId;
    @ApiModelProperty(hidden = true)
    private String author;
    private String content;
    private LocalDateTime created;
}
