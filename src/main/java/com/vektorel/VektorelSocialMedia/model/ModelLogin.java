package com.vektorel.VektorelSocialMedia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModelLogin {
    String title;
    String username;
    String password;
    String loginbutton;
    String tabregister;
    String tablogin;
}
