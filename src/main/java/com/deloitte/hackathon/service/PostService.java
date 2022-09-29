package com.deloitte.hackathon.service;

import com.deloitte.hackathon.aws.AwsStorageService;
import com.deloitte.hackathon.dto.IdImagePostResponse;
import com.deloitte.hackathon.dto.PostRequest;
import com.deloitte.hackathon.entity.Comment;
import com.deloitte.hackathon.entity.Post;
import com.deloitte.hackathon.entity.User;
import com.deloitte.hackathon.exception.AppException;
import com.deloitte.hackathon.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final UserService userService;

    private final AwsStorageService awsStorageService;

    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new AppException("Post not found"));
    }

    public String getPostImage(Long postId) {
        String image = getPost(postId).getImage();
        if (image != null) {
            image = awsStorageService.getImageURL(image);
        }
        return image;
    }

    public List<IdImagePostResponse> getPostImages() {
        return postRepository.findAll()
                .stream()
                .map(IdImagePostResponse::mapPostToIdImagePostResponse)
                .collect(Collectors.toList());
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Comment> getPostComments(Long postId) {
        Post post = getPost(postId);
        return post.getComments();
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public void addPost(Long userId, PostRequest postRequest) {
        User user = userService.getUser(userId);
        String fileName = postRequest.getImage().getOriginalFilename();
        awsStorageService.uploadFile(postRequest.getImage(), fileName);
        Post post = Post.builder()
                .image(fileName)
                .localDate(LocalDate.now())
                .user(user)
                .build();
        postRepository.save(post);
    }

    public void updatePost(Long postId, Post postRequest) {
        Post post = getPost(postId);
        post.setLocalDate(LocalDate.now());
        post.setLikes(post.getLikes() + 1);
        post.setDescription(postRequest.getDescription());
        postRepository.save(post);
    }


}
