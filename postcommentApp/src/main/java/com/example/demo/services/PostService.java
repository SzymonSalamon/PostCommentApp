package com.example.demo.services;

import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private static final int PAGE_SIZE = 20;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    public List<Post> getPosts(int page, Sort.Direction sort) {
        return postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE,
                Sort.by(sort, "id")));
    }
    @Cacheable(cacheNames = "SinglePost", key = "#id")
    public Post getSinglePost(long id) {
        return postRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Cacheable(cacheNames = "PostsWithComments")
    public List<Post> getPostsWithComments(int page, Sort.Direction sort) {
        List<Post> allPosts = postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE,
                Sort.by(sort, "id"))); //gathers all posts from database
        List<Long> ids = allPosts.stream() // gathers all post ids
                .map(Post::getId)
                .collect(Collectors.toList());
        var comments = commentRepository.findAllByPostIdIn(ids); //gathers all comments by post_id table field
        allPosts.forEach(post -> post.setCommentList(extractComments(comments, post.getId()))); //assigns comments to
                                                                                                //the post it belongs to

        return allPosts;
    }
    private List<Comment> extractComments(List<Comment> comments, long id) {
        return comments.stream()
                .filter(comment -> comment.getPostId() == id)
                .collect(Collectors.toList()); //list of all comments of base and certain post_id, if the comment belongs to the
                                                //post with passed id then it sets its comments list to the returned list
    }

    public Post addPost(Post post, String username) {
        post.setAuthor(username);
        return postRepository.save(post);
    }

    @Transactional
    @CachePut(cacheNames = "SinglePost", key = "#result.id")
    public Post editPost(long id, Post post, String author) {
        var editedPost = postRepository.findById(id).orElseThrow();
        if (validateAuthor(editedPost, author)) {
            editedPost.setContent(post.getContent());
            editedPost.setTitle(post.getTitle());
        }
        return editedPost;
    }
    @CacheEvict(cacheNames = "SinglePost")
    public void deletePost(long id, String user) {
        var postToDelete = postRepository.findById(id).orElseThrow();
        if (validateAuthor(postToDelete, user)) {
            postRepository.deleteById(id);
        }
    }
    @CacheEvict(cacheNames = "PostsWithComments")
    public void clearPostsWithComments() {
    }
    public boolean validateAuthor(Post post, String author) {
        return post.getAuthor().equals(author);
    }

    public List<Post> getUserPosts(int pageNumber, Sort.Direction sortDirection, String username) {
        return postRepository.findAllByAuthor(PageRequest.of(pageNumber, PAGE_SIZE,
                Sort.by(sortDirection, "id")), username);
    }

}
