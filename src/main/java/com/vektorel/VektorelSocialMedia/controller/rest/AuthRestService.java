package com.vektorel.VektorelSocialMedia.controller.rest;

import com.vektorel.VektorelSocialMedia.repository.entity.User;
import com.vektorel.VektorelSocialMedia.service.UserService;
import com.vektorel.VektorelSocialMedia.utility.JwtTokenManager;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/rest/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthRestService {

    private final UserService userService;
    private final JwtTokenManager jwtTokenManager;

    @GetMapping("/login")
    @Operation(summary = "Kullanıcı girişi")
    public ResponseEntity<String> login(String username, String password) {
        Optional<User> user = userService.findByUsernameAndPassword(username, password);
        if (user.isPresent()) {
            Optional<String> token = jwtTokenManager.createToken(user.get().getId());
            if (token.isPresent()) {
                return ResponseEntity.ok(token.get());
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
