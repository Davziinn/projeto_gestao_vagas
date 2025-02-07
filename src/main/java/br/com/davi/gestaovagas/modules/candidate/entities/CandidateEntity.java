package br.com.davi.gestaovagas.modules.candidate.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

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
    private UUID uuid;
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo [username] não pode ter espaços")
    private String username;

    @Email(message = "O campo [email] deve conter um email válido")
    private String email;

    @Length(min = 8, max = 100, message = "O campo [senha] deve conter no mínimo 8 dígitos")
    private String password;
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
