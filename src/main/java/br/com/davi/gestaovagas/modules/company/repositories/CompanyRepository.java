package br.com.davi.gestaovagas.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.davi.gestaovagas.modules.company.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID>{
    Optional<CompanyEntity> findByUserNameOrEmail(String username, String email);
    Optional<CompanyEntity> findByUsername(String username);
}
