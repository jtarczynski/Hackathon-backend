package com.deloitte.hackathon.service;

import com.deloitte.hackathon.entity.Comment;
import com.deloitte.hackathon.entity.Post;
import com.deloitte.hackathon.entity.User;
import com.deloitte.hackathon.exception.AppException;
import com.deloitte.hackathon.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostService postService;

    private final UserService userService;

    public Comment getComment(Long postId) {
        return commentRepository.findById(postId).orElseThrow(() -> new AppException("Comment not found"));
    }

    public void addComment(Long postId, Long userId, Comment comment) {
        Post post = postService.getPost(postId);
        User user = userService.getUser(userId);
        comment.setCommentDate(LocalDate.now());
        comment.setUser(user);
        commentRepository.save(comment);
        post.getComments().add(comment);
        postService.savePost(post);
    }

    public void updateComment(Long commentId, Comment commentRequest) {
        Comment comment = getComment(commentId);
        comment.setCommentDate(LocalDate.now());
        comment.setContent(commentRequest.getContent());
        commentRepository.save(comment);
    }
}
