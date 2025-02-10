package br.com.davi.gestaovagas.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.davi.gestaovagas.modules.candidate.dto.CandidateRequestDTO;
import br.com.davi.gestaovagas.modules.candidate.useCase.AuthCandidateUseCase;

@RestController
@RequestMapping("/candidate2")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;
    
    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody CandidateRequestDTO candidateRequestDTO) {
        try {
            var token = this.authCandidateUseCase.execute(candidateRequestDTO);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
