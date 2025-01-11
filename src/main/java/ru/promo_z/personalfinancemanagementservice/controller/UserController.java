package ru.promo_z.personalfinancemanagementservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo_z.personalfinancemanagementservice.dto.request.UserRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.ErrorResponseDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.TokenResponseDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.UserResponseDto;
import ru.promo_z.personalfinancemanagementservice.exception.RegistrationException;
import ru.promo_z.personalfinancemanagementservice.exception.UserNotFoundException;
import ru.promo_z.personalfinancemanagementservice.service.UserService;

@Tag(name="Контроллер по работе с пользователями", description="Спецификация API сервиса по работе с пользователями.")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Зарегистрировать нового пользователя.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))
            })
    })
    @PostMapping("/registration")
    @SecurityRequirements
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody @Valid UserRequestDto userRequestDto)
            throws RegistrationException {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userRequestDto));
    }

    @Operation(summary = "Войти в систему из под пользователя (выполнить авторизацию).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponseDto.class))
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))
            })
    })
    @PostMapping("/login")
    @SecurityRequirements
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid UserRequestDto userRequestDto)
            throws UserNotFoundException {

        return ResponseEntity.ok(userService.loginUser(userRequestDto));
    }
}
