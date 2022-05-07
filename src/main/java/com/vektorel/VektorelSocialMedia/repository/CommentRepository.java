package com.vektorel.VektorelSocialMedia.repository;

import com.vektorel.VektorelSocialMedia.repository.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByPostidOrderByCreatetimeDesc(long id);
}
