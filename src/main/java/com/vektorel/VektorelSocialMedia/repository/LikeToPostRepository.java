package com.vektorel.VektorelSocialMedia.repository;

import com.vektorel.VektorelSocialMedia.repository.entity.LikeToPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeToPostRepository extends JpaRepository<LikeToPost, Long> {

    Optional<LikeToPost> findOptionalByPostIdAndUserId(Long postId, Long userId);
    Optional<LikeToPost> findOptionalByPostId(Long postId);
    Optional<LikeToPost> findOptionalByUserId(Long userId);
}
