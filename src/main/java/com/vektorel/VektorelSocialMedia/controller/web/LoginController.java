package com.vektorel.VektorelSocialMedia.controller.web;

import com.vektorel.VektorelSocialMedia.model.ModelLogin;
import com.vektorel.VektorelSocialMedia.repository.entity.User;
import com.vektorel.VektorelSocialMedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@RequiredArgsConstructor
@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;


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
                .loginbutton("giriş yap").build());

        return view;
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
