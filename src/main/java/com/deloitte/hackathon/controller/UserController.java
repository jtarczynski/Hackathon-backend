package com.deloitte.hackathon.controller;

import com.deloitte.hackathon.dto.PostRequest;
import com.deloitte.hackathon.dto.UsernamePasswordRequest;
import com.deloitte.hackathon.entity.Comment;
import com.deloitte.hackathon.entity.Post;
import com.deloitte.hackathon.entity.User;
import com.deloitte.hackathon.service.CommentService;
import com.deloitte.hackathon.service.PostService;
import com.deloitte.hackathon.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final PostService postService;

    private final CommentService commentService;

    @PostMapping("{userId}/posts/{postId}/add-comment")
    public ResponseEntity<Void> addComment(@PathVariable("userId") Long userId,
                                           @PathVariable("postId") Long postId,
                                           @RequestBody Comment comment) {
        commentService.addComment(postId, userId, comment);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{userId}/update-post")
    public ResponseEntity<Void> updatePost(@PathVariable("userId") Long postId,
                                           @RequestBody Post post) {
        postService.updatePost(postId, post);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{userId}/add-post")
    public ResponseEntity<Void> addPost(@PathVariable("userId") Long userId,
                                        @ModelAttribute PostRequest postRequest) {
        postService.addPost(userId, postRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @GetMapping("current")
    public ResponseEntity<User> getCurrentUser(@RequestBody UsernamePasswordRequest usernamePasswordRequest) {
        return ResponseEntity.ok(userService.getCurrentUser(usernamePasswordRequest));
    }

    @GetMapping("all")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") Long userId,
                                           @RequestBody User user) {
        userService.updateUser(userId, user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("register-user")
    public ResponseEntity<Void> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("login-user")
    public ResponseEntity<User> loginUser(@RequestBody UsernamePasswordRequest request) {
        return ResponseEntity.ok(userService.loginUser(request.getUsername(), request.getPassword()));
    }
}
