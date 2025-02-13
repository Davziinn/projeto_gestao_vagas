package br.com.davi.gestaovagas.modules.candidate.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity(name = "candidate")
@Data
public class CandidateEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Schema(example = "John Doe", requiredMode = RequiredMode.REQUIRED, description = "Nome do candidato")
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo [username] não pode ter espaços")
    @Schema(example = "JohnDoe", requiredMode = RequiredMode.REQUIRED, description = "Username do candidato")
    private String username;

    @Email(message = "O campo [email] deve conter um email válido")
    @Schema(example = "JohnDoe@gmail.com", requiredMode = RequiredMode.REQUIRED, description = "E-mail do candidato")
    private String email;

    @Length(min = 8, max = 100, message = "O campo [senha] deve conter no mínimo 8 dígitos")
    @Schema(example = "JohnDoe@1234", minLength = 10, maxLength = 100, requiredMode = RequiredMode.REQUIRED)
    private String password;

    @Schema(example = "Desevolvedor BackEnd Java")
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
