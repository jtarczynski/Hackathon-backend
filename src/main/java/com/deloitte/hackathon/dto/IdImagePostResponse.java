package com.deloitte.hackathon.dto;

import com.deloitte.hackathon.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IdImagePostResponse {

    Long id;

    String image;

    public static IdImagePostResponse mapPostToIdImagePostResponse(Post post) {
        return IdImagePostResponse.builder()
                .image(post.getImage())
                .id(post.getId())
                .build();
    }
}
