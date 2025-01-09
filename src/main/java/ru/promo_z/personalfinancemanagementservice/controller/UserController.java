package ru.promo_z.personalfinancemanagementservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo_z.personalfinancemanagementservice.dto.request.UserRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.TokenResponseDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.UserResponseDto;
import ru.promo_z.personalfinancemanagementservice.exception.RegistrationException;
import ru.promo_z.personalfinancemanagementservice.exception.UserNotFoundException;
import ru.promo_z.personalfinancemanagementservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto userRequestDto)
            throws RegistrationException {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody UserRequestDto userRequestDto)
            throws UserNotFoundException {

        return ResponseEntity.ok(userService.loginUser(userRequestDto));
    }
}
