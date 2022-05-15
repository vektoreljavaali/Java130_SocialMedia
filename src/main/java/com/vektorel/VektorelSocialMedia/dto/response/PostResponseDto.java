package com.vektorel.VektorelSocialMedia.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {
    String username;
    String avatar;
    long userid;
    String followercount;
    List<PagePostDto> postlist;
}
