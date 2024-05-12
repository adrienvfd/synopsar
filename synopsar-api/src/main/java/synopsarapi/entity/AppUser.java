package synopsarapi.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import synopsarapi.security.AppAuthenticationToken;

@Getter
@ToString
public class AppUser {
    private String id;
    private String name;
    private String email;
    private String imageUrl;

    public static AppUser fromGoogleUser(Object principal) {
        AppUser appUser = new AppUser();
        if (principal instanceof DefaultOAuth2User user) {
            appUser.id = user.getAttribute("sub"); // Assuming this is the correct method to get the user ID
            System.out.println("App user id: " + appUser.id);
            appUser.name = user.getAttribute("name");
            appUser.email = user.getAttribute("email");
            appUser.imageUrl = user.getAttribute("picture");
        } else if (principal instanceof DefaultOidcUser user) {
            appUser.id = user.getUserInfo().getSubject();
            appUser.name = user.getUserInfo().getGivenName();
            appUser.email = user.getUserInfo().getEmail();
            appUser.imageUrl = user.getUserInfo().getPicture();
        }
        return appUser;
    }

    public static AppUser getCurrentAppUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        SecurityContextImpl scI = new SecurityContextImpl(authentication);
        AppAuthenticationToken token = (AppAuthenticationToken) scI.getAuthentication();
        return (AppUser) token.getPrincipal();
    }
}
