package br.com.davi.gestaovagas.modules.candidate.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.davi.gestaovagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.davi.gestaovagas.modules.candidate.entities.CandidateEntity;
import br.com.davi.gestaovagas.modules.candidate.useCase.CreateCandidateUseCase;
import br.com.davi.gestaovagas.modules.candidate.useCase.ListAllJobsByFilterUseCase;
import br.com.davi.gestaovagas.modules.candidate.useCase.ProfileCandidateUseCase;
import br.com.davi.gestaovagas.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate2")
@Tag(name = "Candidato", description = "Informações do candidato")
public class CandidateControler {
    
    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase; 

    @PostMapping("/")
    @Operation(summary = "Cadastro do candidato", description = "Essa função é responsável por cadastrar um candidato")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content (schema = @Schema(implementation = CandidateEntity.class))
        }),
        @ApiResponse(responseCode = "400", description = "Usuário já existe")
    })
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = this.createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Perfil do candidato", description = "Essa função é responsável por buscar todas as informações de candidato")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content (schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
        }),
        @ApiResponse(responseCode = "400", description = "User not found")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var idCandidate = request.getAttribute("candidate_id");

        try {
            var profile = this.profileCandidateUseCase.execute(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.status(HttpStatus.OK).body(profile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Listagem das cagas disponíveis para o candidato", description = "Essa função é responsável por listar todas as vagas para o candidato")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content (schema = @Schema (implementation = JobEntity.class))
        })
    })
    @SecurityRequirement(name = "jwt_auth")
    public List<JobEntity> findByJobFilter(@RequestParam String filter) {
        return this.listAllJobsByFilterUseCase.execute(filter);
    }
}
