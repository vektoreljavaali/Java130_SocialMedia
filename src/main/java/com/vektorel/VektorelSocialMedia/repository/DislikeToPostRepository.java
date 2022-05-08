package com.vektorel.VektorelSocialMedia.repository;

import com.vektorel.VektorelSocialMedia.repository.entity.DislikeToPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DislikeToPostRepository extends JpaRepository<DislikeToPost, Long> {

    Optional<DislikeToPost> findOptionalByPostIdAndUserId(Long postId, Long userId);
    Optional<DislikeToPost> findOptionalByPostId(Long postId);
    Optional<DislikeToPost> findOptionalByUserId(Long userId);
}
