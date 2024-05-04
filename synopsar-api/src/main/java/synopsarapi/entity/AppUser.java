package synopsarapi.entity;

import lombok.Getter;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

@Getter
public class AppUser {
    private String id;
    private String name;
    private String email;
    private String imageUrl;

    public static AppUser fromGoogleUser(Object principal) {
        AppUser appUser = new AppUser();
        if (principal instanceof DefaultOAuth2User user) {
            appUser.id = user.getName(); // Assuming this is the correct method to get the user ID
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
}
