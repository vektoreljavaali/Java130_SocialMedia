package com.vektorel.VektorelSocialMedia.controller.rest;

import com.vektorel.VektorelSocialMedia.dto.response.GetPostListByUserIdResponseDto;
import com.vektorel.VektorelSocialMedia.repository.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/post")
@RequiredArgsConstructor
public class PostController {

    @PostMapping("/getpostlistbyuserid")
    public ResponseEntity<Post> getPostListByUserId(
            @Validated @RequestBody GetPostListByUserIdResponseDto dto) {
        return null;
    }

}
