package ru.promo_z.personalfinancemanagementservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.promo_z.personalfinancemanagementservice.dto.request.UserRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.UserResponseDto;
import ru.promo_z.personalfinancemanagementservice.model.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    public abstract User UserRegistrationRequestDtoToUser(UserRequestDto userRequestDto);

    @Mapping(target = "wallet.walletId", source = "wallet.id")
    public abstract UserResponseDto userToUserResponseDto(User user);

    @Named("encodePassword")
    protected String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
