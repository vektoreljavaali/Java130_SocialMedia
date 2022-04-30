package com.vektorel.VektorelSocialMedia.controller.web;

import com.vektorel.VektorelSocialMedia.model.ModelHome;
import com.vektorel.VektorelSocialMedia.repository.PostRepository;
import com.vektorel.VektorelSocialMedia.repository.entity.Post;
import com.vektorel.VektorelSocialMedia.repository.entity.User;
import com.vektorel.VektorelSocialMedia.service.PostService;
import com.vektorel.VektorelSocialMedia.utility.StaticValues;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    // localhost:9090/home
    @GetMapping("")
    public Object index(){
        User user = StaticValues.user;
        if(user==null)
            return "redirect:/login";
        ModelAndView modelAndView = new ModelAndView();
        List<Post> postList = postService.findByUserid(user.getId());
        modelAndView.addObject("model",
                ModelHome.builder()
                        .followercount("123")
                        .username(user.getUsername())
                        .postlist(postList)
                        .build());
        modelAndView.setViewName("index");
        return  modelAndView;
    }


    @GetMapping("/demo")
    public void DemoKayit(){

        postService.save(Post.builder()
                        .commentcount(25)
                        .content("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.   ")
                        .dislike(4)
                        .username("temel.k@gmail.com")
                        .sharedtime(new Date().getTime())
                        .userid(4l)
                        .userphoto("images/users/user-5.jpg")
                .build());

    }
}
