package br.com.davi.gestaovagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
    
    @Schema(example = "GYMPass, Plano de Saúde")
    private String benefits;

    @Schema(example = "Vaga para desenvolvedor java")
    private String description;
    
    @Schema(example = "JUNIOR")
    private String level;
}
