package com.vektorel.VektorelSocialMedia.controller.rest;

import com.vektorel.VektorelSocialMedia.dto.response.GetPostListByUserIdResponseDto;
import com.vektorel.VektorelSocialMedia.dto.response.PagePostDto;
import com.vektorel.VektorelSocialMedia.dto.response.PostResponseDto;
import com.vektorel.VektorelSocialMedia.repository.entity.Post;
import com.vektorel.VektorelSocialMedia.repository.entity.User;
import com.vektorel.VektorelSocialMedia.service.PostService;
import com.vektorel.VektorelSocialMedia.service.UserService;
import com.vektorel.VektorelSocialMedia.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rest/post")
@RequiredArgsConstructor
public class PostRestController {

    private final UserService userService;
    private final PostService postService;
    private final JwtTokenManager jwtTokenManager;
    @PostMapping("/getpostlistbyuserid")
    public ResponseEntity<Post> getPostListByUserId(
            @Validated @RequestBody GetPostListByUserIdResponseDto dto) {
        return null;
    }

    @GetMapping("/getpostlistbyuserid")
    public ResponseEntity<PostResponseDto> getMyPostByUserId(Long userid, String token){

        if(jwtTokenManager.validateToken(token)){
            Optional<User> user =userService.findById(userid);
            if(user.isPresent()){
                List<PagePostDto> postList = postService.findByUserid(userid);
                PostResponseDto postResponseDto = PostResponseDto.builder()
                        .username(user.get().getUsername())
                        .avatar(user.get().getAvatar())
                        .postlist(postList)
                        .build();
                return ResponseEntity.ok(postResponseDto);
            }
            return ResponseEntity.badRequest().build();
        }else
            return ResponseEntity.badRequest().build();

    }
}
