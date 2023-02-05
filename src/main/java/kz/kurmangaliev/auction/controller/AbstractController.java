package kz.kurmangaliev.auction.controller;

import kz.kurmangaliev.auction.db.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class AbstractController {
    protected Long getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return user.getId();
    }

    @GetMapping("/greetings")
    public String greetings() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("{}", auth);
        return "Welcome to auction!";
    }

    @GetMapping("/failure")
    public String failure() {
        return "Authentication failed!";
    }
}
