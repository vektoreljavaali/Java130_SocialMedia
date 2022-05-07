package com.vektorel.VektorelSocialMedia.model;

import com.vektorel.VektorelSocialMedia.dto.response.PagePostDto;
import com.vektorel.VektorelSocialMedia.repository.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelHome {
    String username;
    String avatar;
    long userid;
    String followercount;
    List<PagePostDto> postlist;
}
