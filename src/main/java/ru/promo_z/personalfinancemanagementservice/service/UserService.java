package ru.promo_z.personalfinancemanagementservice.service;

import ru.promo_z.personalfinancemanagementservice.dto.request.UserRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.TokenResponseDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.UserResponseDto;
import ru.promo_z.personalfinancemanagementservice.exception.RegistrationException;
import ru.promo_z.personalfinancemanagementservice.exception.UserNotFoundException;

public interface UserService {

    UserResponseDto registerUser(UserRequestDto userRequestDto) throws RegistrationException;

    TokenResponseDto loginUser(UserRequestDto userRequestDto) throws UserNotFoundException;
}
