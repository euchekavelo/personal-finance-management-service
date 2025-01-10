package ru.promo_z.personalfinancemanagementservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.promo_z.personalfinancemanagementservice.dto.request.UserRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.TokenResponseDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.UserResponseDto;
import ru.promo_z.personalfinancemanagementservice.exception.RegistrationException;
import ru.promo_z.personalfinancemanagementservice.exception.UserNotFoundException;
import ru.promo_z.personalfinancemanagementservice.mapper.UserMapper;
import ru.promo_z.personalfinancemanagementservice.model.User;
import ru.promo_z.personalfinancemanagementservice.model.Wallet;
import ru.promo_z.personalfinancemanagementservice.repository.UserRepository;
import ru.promo_z.personalfinancemanagementservice.security.JwtUtil;
import ru.promo_z.personalfinancemanagementservice.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto)
            throws RegistrationException {

        Optional<User> optionalUser = userRepository.findByEmail(userRequestDto.getEmail());
        if (optionalUser.isPresent()) {
            throw new RegistrationException("A user with this email already exists.");
        }

        User newUser = userMapper.UserRegistrationRequestDtoToUser(userRequestDto);
        Wallet wallet = new Wallet();
        wallet.setBalance(0L);
        wallet.setUser(newUser);
        newUser.setWallet(wallet);

        return userMapper.userToUserResponseDto(userRepository.save(newUser));
    }

    @Override
    public TokenResponseDto loginUser(UserRequestDto userRequestDto) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmailAndPassword(userRequestDto.getEmail(),
                userRequestDto.getPassword());
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("The user with the specified data was not found.");
        }

        return TokenResponseDto.builder()
                .token(jwtUtil.generateTokenForUser(userRequestDto.getEmail()))
                .build();
    }
}
