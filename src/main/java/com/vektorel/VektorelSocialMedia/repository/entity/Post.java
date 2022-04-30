package com.vektorel.VektorelSocialMedia.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "tblpost")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    long userid;
    String userphoto;
    String username;
    long sharedtime;
    @Column(length = 5000)
    String content;
    String url;
    int likecount;
    int dislike;
    int commentcount;

}
