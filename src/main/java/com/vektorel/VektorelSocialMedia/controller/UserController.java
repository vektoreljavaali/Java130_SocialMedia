package com.vektorel.VektorelSocialMedia.controller;

import com.vektorel.VektorelSocialMedia.repository.entity.User;

public class UserController {

    public void deneme(){
        User.builder().avatar("sdsa").build();
        User.builder().build().toString();
        // parametresiz cont. User.builder().build();
        User.builder().username("muhammed")
                .email("dfdsf@dsf.com").build();
    }
}
