package kz.kurmangaliev.auction.controller;

import kz.kurmangaliev.auction.dto.ResultMessage;
import kz.kurmangaliev.auction.dto.UserDto;
import kz.kurmangaliev.auction.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/registration")
    public ResultMessage addUser(@RequestBody @Valid UserDto userDto) {
        if (!userService.saveUser(userDto)) {

            return ResultMessage.failure("Пользователь с таким email уже существует");
        }

        return ResultMessage.success();
    }
}
