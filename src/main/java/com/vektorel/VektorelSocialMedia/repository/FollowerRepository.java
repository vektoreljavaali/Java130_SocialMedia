package com.vektorel.VektorelSocialMedia.repository;

import com.vektorel.VektorelSocialMedia.repository.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends JpaRepository<Follower,Long> {
}
