package br.com.davi.gestaovagas.modules.candidate.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.davi.gestaovagas.exceptions.JobNotFoundException;
import br.com.davi.gestaovagas.exceptions.UserNotFoundException;
import br.com.davi.gestaovagas.modules.candidate.entities.ApplyJobEntity;
import br.com.davi.gestaovagas.modules.candidate.repositories.ApplyJobRepository;

public class ApplyJobCandidateUseCase {
    
    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {
        this.applyJobRepository.findById(idCandidate).orElseThrow(
            () -> {
                throw new UserNotFoundException();
            }
        );

        this.applyJobRepository.findById(idJob).orElseThrow(
            () -> {
                throw new JobNotFoundException();
            }
        );

        var applyJobEntity = ApplyJobEntity.builder()
            .candidateId(idCandidate)
            .jobId(idJob)
            .build();

        applyJobEntity = applyJobRepository.save(applyJobEntity);
        return applyJobEntity;
    }
}
