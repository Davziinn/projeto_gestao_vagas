package br.com.davi.gestaovagas.modules.candidate.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.davi.gestaovagas.exceptions.UserFoudException;
import br.com.davi.gestaovagas.modules.candidate.entities.CandidateEntity;
import br.com.davi.gestaovagas.modules.candidate.repositories.CandidateRepository;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute (CandidateEntity candidateEntity) {
        this.candidateRepository
            .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
            .ifPresent((user) -> {
                throw new UserFoudException();
            });

            return this.candidateRepository.save(candidateEntity);
    }
}
