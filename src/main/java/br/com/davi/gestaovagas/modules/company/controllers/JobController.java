package br.com.davi.gestaovagas.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.davi.gestaovagas.modules.company.dto.JobDTO;
import br.com.davi.gestaovagas.modules.company.entities.JobEntity;
import br.com.davi.gestaovagas.modules.company.useCase.CreateJobUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job2")
@Tag(name = "Vagas", description = "Informações de vagas")
public class JobController {
    
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole(COMPANY)")
    @Operation(summary = "Cadastro de vagas", description = "Essa função é responsável por cadastrar vagas")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content (array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
        })
    })
    @SecurityRequirement(name = "jwt_auth")
    public JobEntity create(@Valid @RequestBody JobDTO jobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_Id");

            var jobEntity = JobEntity.builder()
                .benefits(jobDTO.getBenefits())
                .description(jobDTO.getDescription())
                .level(jobDTO.getLevel())
                .companyId(UUID.fromString(companyId.toString()))
                .build();

            return this.createJobUseCase.execute(jobEntity);
    }
}
