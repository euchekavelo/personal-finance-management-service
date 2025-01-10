package ru.promo_z.personalfinancemanagementservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRequestDto {

    @Email(message = "Incorrect email format.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@mail\\.ru$", message = "The required mail format must end with @mail.ru")
    private String email;

    @NotBlank
    private String password;
}
