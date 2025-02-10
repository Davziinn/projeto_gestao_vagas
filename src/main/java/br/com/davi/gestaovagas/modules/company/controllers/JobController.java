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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job2")
public class JobController {
    
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole(COMPANY)")
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
