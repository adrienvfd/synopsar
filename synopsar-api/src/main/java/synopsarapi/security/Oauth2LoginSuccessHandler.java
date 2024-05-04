package synopsarapi.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import synopsarapi.entity.AppUser;

import java.io.IOException;

public class Oauth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("oAuth2User: " + oAuth2User.toString());
        AppUser appUser = AppUser.fromGoogleUser(oAuth2User);
        AppAuthenticationToken token = new AppAuthenticationToken(appUser);
        SecurityContextHolder.getContext().setAuthentication(token);
        response.sendRedirect("http://localhost:3000/profile");
    }
}