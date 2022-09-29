package com.deloitte.hackathon.controller;


import com.deloitte.hackathon.entity.Comment;
import com.deloitte.hackathon.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments/")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PutMapping("/comments/{commentId}/update-comment")
    public ResponseEntity<Void> updateComment(@PathVariable("commentId") Long commentId,
                                              @RequestBody Comment comment) {
        commentService.updateComment(commentId, comment);
        return ResponseEntity.ok().build();
    }
}
