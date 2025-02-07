package br.com.davi.gestaovagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.davi.gestaovagas.modules.company.entities.JobEntity;
import br.com.davi.gestaovagas.modules.company.useCase.CreateJobUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job2")
public class JobController {
    
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    public JobEntity create(@Valid @RequestBody JobEntity jobEntity) {
        return this.createJobUseCase.execute(jobEntity);
    }
}
