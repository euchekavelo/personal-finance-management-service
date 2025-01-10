package ru.promo_z.personalfinancemanagementservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRequestDto {

    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)" +
            "*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Incorrect email format." +
            "Recommended format, for example test@mail.ru.")
    private String email;

    @NotBlank(message = "The password must not be empty.")
    private String password;
}
