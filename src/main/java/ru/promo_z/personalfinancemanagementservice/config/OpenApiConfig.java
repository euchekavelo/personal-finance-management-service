package ru.promo_z.personalfinancemanagementservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Сервис управления личными финансами",
                description = "Описание спецификации API сервиса по управлению личными финансами.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Suslov Kirill",
                        url = "https://t.me/euchekavelo"
                )
        ),
        security = @SecurityRequirement(name = "Bearer Token")
)
@SecurityScheme(
        name = "Bearer Token",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {
}
