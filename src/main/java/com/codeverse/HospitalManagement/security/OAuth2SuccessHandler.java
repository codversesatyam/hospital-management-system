package com.codeverse.HospitalManagement.security;

import com.codeverse.HospitalManagement.Enum.Role;
import com.codeverse.HospitalManagement.entity.User;
import com.codeverse.HospitalManagement.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;

    private final AuthUtil authUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getAttribute("email");
        String login = oAuth2User.getAttribute("login");

        if( email == null){
            email = email + "@github.com";
        }

        String finalEmail = email;
        User user = userRepository.findByEmail(email).orElseGet(
                () -> {

                    User newUser = new User();
                    newUser.setUserName(login);
                    newUser.setEmail(finalEmail);
                    newUser.setRole(Role.PATIENT);
                    newUser.setPassword("OAUTH2_USER");
                    return userRepository.save(newUser);
                });

        String jwt = authUtil.generateAccessToken(user);

        response.sendRedirect(
                "http://localhost:3000/oauth2/success?token=" + jwt
        );

        
    }
}
