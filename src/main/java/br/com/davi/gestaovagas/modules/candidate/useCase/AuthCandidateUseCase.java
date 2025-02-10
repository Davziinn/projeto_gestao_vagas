package br.com.davi.gestaovagas.modules.candidate.useCase;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.davi.gestaovagas.modules.candidate.dto.CandidateRequestDTO;
import br.com.davi.gestaovagas.modules.candidate.dto.CandidateResponseDTO;
import br.com.davi.gestaovagas.modules.candidate.repositories.CandidateRepository;

@Service
public class AuthCandidateUseCase {
    
    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateResponseDTO execute(CandidateRequestDTO candidateRequestDTO) throws AuthenticationException {
        var candidate = this.candidateRepository.findByUsername(candidateRequestDTO.username()).orElseThrow(
            () -> {
                throw new UsernameNotFoundException("Username/Password incorrect");
            }
        );

        var passwordMatches = this.passwordEncoder
            .matches(candidateRequestDTO.password(), candidate.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofMinutes(10));

        var token = JWT.create()
            .withIssuer("javagas")
            .withSubject(candidate.getId().toString())
            .withExpiresAt(expiresIn)
            .withClaim("roles", Arrays.asList("CANDIDATE"))
            .sign(algorithm);

        var authCandidateResponse = CandidateResponseDTO.builder()
            .access_token(token)
            .expires_in(expiresIn.toEpochMilli())
            .build();
        
        return authCandidateResponse;

    }
}
