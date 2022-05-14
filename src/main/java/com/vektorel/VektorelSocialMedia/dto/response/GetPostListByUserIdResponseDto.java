package com.vektorel.VektorelSocialMedia.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPostListByUserIdResponseDto {

    String token;
    long userid;
}
