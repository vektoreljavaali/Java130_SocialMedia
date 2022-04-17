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
@Table(name = "tblfollow")
@Entity
/**
 * Kişinin takip ettiği kullancılar
 */
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    long id;
    long userid;
    /**
     * userid kişinini takip ettiği kullaıcı id si
     */
    long followid;
    /**
     * Eğer bir kişi bir kişiyi takip etmiyorsa
     * bu değer ya hiç yoktur yada 0 dır
     * 0-> takip etmiyor
     * 1-> takip ediyor
     * 2-> takip isteği göndermiş onayda bekliyor.
     */
    int status;
}
