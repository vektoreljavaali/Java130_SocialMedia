package com.vektorel.VektorelSocialMedia.repository.entity;

import lombok.*;

import javax.persistence.*;


/**
 * POJO ile iligi eklenmesi gerekn kodların
 * karmaşıklığını azaltmak ve daha kolay bir
 * kullanım sağlamak amacıyla Lombok Porject
 * geliştirilmiştir. Kullanım için anotasyonlarını
 * eklemek yeterlidir.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "tbluser")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String username;
    String borndate;
    String email;
    String phone;
    String avatar;
    String phonetoken;
    long createprofile;


}
