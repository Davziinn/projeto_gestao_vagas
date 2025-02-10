package br.com.davi.gestaovagas.modules.company.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.davi.gestaovagas.exceptions.UserFoudException;
import br.com.davi.gestaovagas.modules.company.entities.CompanyEntity;
import br.com.davi.gestaovagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {
    
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository
            .findByUserNameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
            .ifPresent((user) -> {
                throw new UserFoudException();
            });

            var password = passwordEncoder.encode(companyEntity.getPassword());
            companyEntity.setPassword(password);

            return this.companyRepository.save(companyEntity);
    }
}  
