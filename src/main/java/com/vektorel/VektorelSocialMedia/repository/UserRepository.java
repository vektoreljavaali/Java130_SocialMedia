package com.vektorel.VektorelSocialMedia.repository;

import com.vektorel.VektorelSocialMedia.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * Kullanıcı adı ve şifreyi kullanarak git db den bilglieri çek diyebilmem
     * gerekli.
     * Özel bir şekilde sorgu oluşturabilmek için belli parametreleri birleştirmeniz
     * gerekmektedir. bu parametreleri aşağıdaki adresten bulabilirsiniz.,
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
     * Özel JpaMethodları yazmak için kurallar bütünü;
     * Sizin girdiğiniz method adından ona uygun sorguyu olşuturuyor.
     * 1- "find" yazılacak
     * 2- genellikle "by" başa  eklenmeli bazı sorgularda 2. parametre olabilir.
     * 3- entity içinde tanımladığınız değişken adını yazacaksınız.Burada Dikkat
     * örneğin entity property(değişken) adı "username" olsun. değişkeni yazarken
     * ilk harfini büyük yamak zorundasınız. "Username" neden böyle yazılır, çünkü
     * spring kelimeleri ayrıştırmak için büyük küçük harf sırasına bakar.
     * 4- eğer birden fazla değişken ile sorgulama yapılacak ise birleştirme
     * operatörleri kullanılmalıdır. "and", "or" kullanılır ve eklenecek değişken
     * yine baş harfi büyük olarak yazılmalıdır.
     * 5- sonrasında methos un alması gereken parametleri yazmamız gereklidir. burada
     * dikkat etmeniz gereken husus method adını yazarken kullandığınız sıralama ile
     * method değişkenlerini yazdığınız sıranını aynı olması gereklidir. değişken
     * adlarının aynı olması gerekmez.
     *
     */

    // select * from tbluser where kullanıcıadı='' and sifre=''
    User findByUsernameAndPassword(String username, String password);

    Optional<User> findOptionalByUsernameAndPassword(String username, String password);

    // select * from tbluser where name=''
    List<User> findByName(String name);

    List<User> findByCity(String city);

    List<User> findByNameLike(String searchText);


    // 1- find
    // 2- findBy
    // 3- findByUsername = find By username
    // 4- findbyUsernameAndPassword
    // 5- findByUsernameAndPassword(String username,String password)
    // 5- findByUsernameAndPassword(String ayse,String nededin)
    // bulKullanıcıadıVeŞifresineGöre(String kullanıcıadı, String şifre);
    // select * from tbluser where kullanıcıadı=$kullanıcıadı and sifre=$şifre
}
