package com.vektorel.VektorelSocialMedia.controller.web;

import com.vektorel.VektorelSocialMedia.repository.entity.Comment;
import com.vektorel.VektorelSocialMedia.repository.entity.Post;
import com.vektorel.VektorelSocialMedia.repository.entity.User;
import com.vektorel.VektorelSocialMedia.service.CommentService;
import com.vektorel.VektorelSocialMedia.service.PostService;
import com.vektorel.VektorelSocialMedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    /**
     * Web ortamında dosya almak için özellikle MultiparFile kullanılmalıdır.
     * jpg, png, mp4, docx, pdf, gif, xlsx, json, v.s.
     * Gelen bilgiyi, base64 olarak alıyor. bizde bu bilgiyi herhangi bir
     * ortama dosya olarak aktarıyoruz.
     * @param userid
     * @param content
     * @param image
     * @return
     */
    @PostMapping("/save")
    public Object save(long userid, String content, MultipartFile image){
        /**
         * Optional olarak aldığınız bilgiler bir kapsül içinde gelir.
         * bu nedenle optional olan değeri kontrol etmek gereklidir.         *
         */
        Optional<User> user = userService.findById(userid);
        /**
         * 1- Eğer resmi gelen dosya adı ile kayıt edersem ne olur????
         * 1.1- Aynı isimde olan dosyalar bir biri üzerinde yazıldığı için
         * karmaşa çıkar. Peki ne yapmalıyım??
         * 1.2- Çözüm, her resim ya da dosya için benzersiz ad vermek.
         */
        String resim_id = UUID.randomUUID().toString();
        /**
         * Yeni bir boş dosya oluşturdum.
         */
        try{
            Optional<String> resimurl = postService.uploadFile(image);
            if(resimurl.isPresent()){
                Post post = Post.builder()
                        .userid(userid)
                        .content(content)
                        .dislike(0)
                        .likecount(0)
                        .sharedtime(new Date().getTime()) //şuanın long değeri
                        .username(user.isPresent() ? user.get().getUsername() : "")
                        .userphoto(user.isPresent() ? user.get().getAvatar() : "/images/user-1.jpg")
                        .commentcount(0)
                        .url(resimurl.get()).build();
                postService.save(post);
            }
            return "redirect:/home";
        }catch (Exception ex){
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("error","hata..: "+ex.toString());
            // eğer hata mesajı vermek istiyorsan --> return modelAndView;
            return "redirect:/home";
        }

    }


    @PostMapping("/addcomment")
    public Object addCommend(long userid, long postid, String comment){
        Optional<User> user = userService.findById(userid);
        Comment commentLocal = Comment.builder()
                .comment(comment)
                .postid(postid)
                .createtime(new Date().getTime())
                .username(user.isPresent() ? user.get().getUsername() : "")
                .avatar(user.isPresent() ? user.get().getAvatar() : "/images/user-1.jpg")
                .build();
        commentService.save(commentLocal);
        return "redirect:/home";
    }

    public void Like(long userid, long postid){
        /**
         * Daha önce like etmiş mi?
         * Daha önce dis like etmiş mi?
         * Eğer dislike var ise onu eksilt, like arttır.
         * eğer daha önveden like yapmamış ise like sayısını arttır.
         */

    }

}
