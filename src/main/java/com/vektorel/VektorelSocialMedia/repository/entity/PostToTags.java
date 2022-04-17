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
@Table(name = "tblposttotags")
@Entity
public class PostToTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    long postid;
    long tagsid;
}
