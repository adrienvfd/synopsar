package synopsarapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import synopsarapi.entity.AppUser;

@RestController
public class PingController {

    @GetMapping("/")
    public String ping() {
        System.out.println("PINGING...");
        return "pong";
    }

    @GetMapping("/secured")
    public String pingSecured() {
        System.out.println("SECURED PINGING...");
        return "secured pong";
    }

    @GetMapping("/me")
    public ResponseEntity<AppUser> google() {
        AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(user);
    }
}
