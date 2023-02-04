package kz.kurmangaliev.auction.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class AbstractController {
    protected Long getUserId() {
        return 1L;
    }
}
