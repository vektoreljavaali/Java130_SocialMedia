package com.vektorel.VektorelSocialMedia.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbldisliketopost")
@Entity
public class DislikeToPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    long userId;
    long postId;
}
