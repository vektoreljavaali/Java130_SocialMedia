package com.vektorel.VektorelSocialMedia.dto.response;

import com.vektorel.VektorelSocialMedia.repository.entity.Comment;
import com.vektorel.VektorelSocialMedia.repository.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagePostDto {
    Post post;
    List<Comment> commentList;
}
