package br.com.davi.gestaovagas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
            .info(new Info().title("Gestão de vagas").description("API para gestão de vagas")
                .version("1"))
            .schemaRequirement("jwt_auth", createSecurityScheme());
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme().name("jwt_auth").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT");
    }
}
