package com.deloitte.hackathon.controller;

import com.deloitte.hackathon.dto.IdImagePostResponse;
import com.deloitte.hackathon.entity.Comment;
import com.deloitte.hackathon.entity.Post;
import com.deloitte.hackathon.service.CommentService;
import com.deloitte.hackathon.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts/")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("{postId}/comments/all")
    public ResponseEntity<List<Comment>> getPostComments(@PathVariable("postId") Long postId) {
        List<Comment> comments = postService.getPostComments(postId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("{postId}")
    public ResponseEntity<Post> getPost(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @GetMapping("{postId}/image")
    public ResponseEntity<String> getPostImage(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(postService.getPostImage(postId));
    }

    @GetMapping("all/images")
    public ResponseEntity<List<IdImagePostResponse>> getPostsImages() {
        return ResponseEntity.ok(postService.getPostImages());
    }

    @PutMapping("all")
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }
}
