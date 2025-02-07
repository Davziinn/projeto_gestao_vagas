package br.com.davi.gestaovagas.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.davi.gestaovagas.modules.company.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID>{
    Optional<JobEntity> findByUsernameOrEmail(String username, String email);
}
