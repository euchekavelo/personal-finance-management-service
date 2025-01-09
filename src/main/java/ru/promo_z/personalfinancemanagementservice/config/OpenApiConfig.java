package ru.promo_z.personalfinancemanagementservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
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
        )
)
public class OpenApiConfig {
}
