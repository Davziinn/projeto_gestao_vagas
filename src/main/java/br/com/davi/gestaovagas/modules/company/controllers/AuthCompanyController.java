package br.com.davi.gestaovagas.modules.company.controllers;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.davi.gestaovagas.modules.company.dto.AuthCompanyDTO;
import br.com.davi.gestaovagas.modules.company.useCase.AuthCompanyUseCase;

@RestController
@RequestMapping("/company2")
public class AuthCompanyController {
    
    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/auth2")
    public ResponseEntity<Object> create(@RequestBody AuthCompanyDTO authCompanyDTO) {
        try {
            var result = this.authCompanyUseCase.execute(authCompanyDTO);
            return ResponseEntity.ok().body(result);
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
