package org.example.accounts.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.ExternalDocumentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Account Microservice API")
                        .description("API documentation for Account microservice")
                        .version("1.0")
                        .contact(new Contact().name("ShivaKant Singh")
                                .email("shivakantsingh900@gmail.com")
                                .url("https://example.com"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Account Microservice Documentation")
                        .url("https://example.com/docs"));
    }
}
