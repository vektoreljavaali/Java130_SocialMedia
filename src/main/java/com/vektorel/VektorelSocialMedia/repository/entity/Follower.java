package com.vektorel.VektorelSocialMedia.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * bir kişiyi takip edenlerin listesi.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "tblfollower")
@Entity
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    long id;
    /**
     * takip edilen kişi
     */
    long userid;
    /**
     * userid yi atkip eden kişi
     */
    long folowerid;
    int status;

}
