package com.vektorel.VektorelSocialMedia.controller.web;

import com.vektorel.VektorelSocialMedia.model.ModelLogin;
import com.vektorel.VektorelSocialMedia.repository.entity.User;
import com.vektorel.VektorelSocialMedia.service.UserService;
import com.vektorel.VektorelSocialMedia.utility.StaticValues;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    /**
     *  ÖNEMLİ !!!!!!
     *  Eğer bir sayfa içinde bir değeri kontrol ediyorsanız, o değerin
     *  muhakkak controller tarafından geliyor olması gerekli. yoksa null
     *  olarak gelen değer sayfanının yğklenmemesine sebebiyet veriri.
     *  Görünen Hata Şekli:
     *  Caused by: org.springframework.expression.spel.SpelEvaluationException: EL1001E: Type conversion problem, cannot convert from null to boolean
     *  İlgili sayfalara, gönderilecek değerlerinm parça parça gönderilmesi
     *  yerine o sayfaya ait bir pageModel oluştuırmak doğru yaklaşımdır.
     *  loginPageModel
     */

    /**
     * getmapping te her hangi bir eklenti yazmıyorum
     * çünkü login e gelen kişinin ilk karşısına
     * çıkmasını istediğim sayfa bu olduğu için.
     * @return
     */
    @GetMapping("")
    public ModelAndView index(){
        /**
         * Burada model ve view a ihtiyacımız olacak
         * bu nedenle sırası ile bunları ekleyeceğiz
         * bu iki parametreyi kontrol ederek birleştirmesi
         * için de ModelAndView nesnesini kullnacağız.
         */
        ModelAndView view = new ModelAndView();
        view.setViewName("login");
        /**
         * html sayfasına gönderilmek üzere adı: model olan bir nesne gönderdi
         * burada gönderdiğim nesne yönetilebilir olması ve kodlama kolaylığı
         * açısından bir sınıf olarak kullanılmıştır.
         */
        view.addObject("model",ModelLogin.builder()
                .title("Kullanıcı girişi")
                .username("Kullanıcı adı")
                .password("Şifre")
                .error(false)
                .loginbutton("giriş yap").build());

        return view;
    }

    @PostMapping("")
    public Object index(String username, String password){
        /**
         * 1- login page üzerinden gelen kullanıcı adı ve şifreyi alırım.
         * 2- bu bilgilerin olduğu bir kullanıcı var mı?
         * 3- şuan controller dayız -> servis e sormalıyız. şuan böyle bir
         * method olmadığı için gidip sserviste bunu yazmalıyız.
         */
        Optional<User> result =
                userService.findByUsernameAndPassword(username, password);
        if(result.isPresent()){
            /**
             * Giriş yapan kullanıcının bilgilerini static bir değere atadım.
             */
            StaticValues.user = result.get();
            return "redirect:/home";
        }else{
            ModelAndView model = new ModelAndView();
            model.addObject("model",ModelLogin.builder()
                    .title("Kullanıcı girişi")
                    .username("Kullanıcı adı")
                    .password("Şifre")
                    .error(true)
                    .loginbutton("giriş yap").build());
            model.setViewName("login");
            return model;
        }

    }

    @PostMapping("/register")
    public Object register(String firstname, String lastname,
                           String email, String password,
                           int day, int month, int year,boolean gender,
                           String city, String country){
        userService.save(User.builder()
                        .avatar("")
                        .borndate(year+"")
                        .createprofile(new Date().getTime())
                        .email(email)
                        .username(email)
                        .name(firstname+ " "+ lastname)
                        .city(city)
                        .country(country)
                        .gender(gender ? "Erkek" : "Kadın")
                        .password(password)
                .build());

        return "redirect:/login";
    }

}
