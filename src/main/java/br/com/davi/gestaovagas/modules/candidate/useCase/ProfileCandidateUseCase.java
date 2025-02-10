package br.com.davi.gestaovagas.modules.candidate.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.davi.gestaovagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.davi.gestaovagas.modules.candidate.repositories.CandidateRepository;

@Service
public class ProfileCandidateUseCase {
    
    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate).orElseThrow(
            () -> {
                throw new UsernameNotFoundException("Candidate not found");
            }
        );

        var candidateDTO = ProfileCandidateResponseDTO.builder()
        .description(candidate.getDescription())
        .username(candidate.getUsername())
        .name(candidate.getName())
        .email(candidate.getEmail())
        .id(candidate.getId())
        .build();

        return candidateDTO;
    }
}
