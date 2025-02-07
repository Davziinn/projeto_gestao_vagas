package br.com.davi.gestaovagas.modules.company.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.davi.gestaovagas.exceptions.UserFoudException;
import br.com.davi.gestaovagas.modules.company.entities.CompanyEntity;
import br.com.davi.gestaovagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {
    
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository
            .findByUserNameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
            .ifPresent((user) -> {
                throw new UserFoudException();
            });

            return this.companyRepository.save(companyEntity);
    }
}  
