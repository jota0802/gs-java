package br.com.fiap.mentalhealthplatform.application.dto;

import br.com.fiap.mentalhealthplatform.domain.enums.NivelAnsiedade;
import br.com.fiap.mentalhealthplatform.domain.enums.NivelHumor;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDiarioRequestDTO {

    @NotNull(message = "ID do paciente é obrigatório")
    private Long pacienteId;

    @NotNull(message = "Data de registro é obrigatória")
    private LocalDate dataRegistro;

    @NotNull(message = "Nível de humor é obrigatório")
    private NivelHumor nivelHumor;

    @NotNull(message = "Nível de ansiedade é obrigatório")
    private NivelAnsiedade nivelAnsiedade;

    @NotNull(message = "Horas de sono é obrigatório")
    @Min(value = 0, message = "Horas de sono deve ser no mínimo 0")
    @Max(value = 24, message = "Horas de sono deve ser no máximo 24")
    private Integer horasSono;

    @NotNull(message = "Campo 'praticou exercício' é obrigatório")
    private Boolean praticouExercicio;

    @Size(max = 1000, message = "Anotações devem ter no máximo 1000 caracteres")
    private String anotacoes;

    @Size(max = 500, message = "Gatilhos emocionais devem ter no máximo 500 caracteres")
    private String gatilhosEmocionais;

    @NotNull(message = "Qualidade do dia é obrigatória")
    @Min(value = 1, message = "Qualidade do dia deve ser no mínimo 1")
    @Max(value = 10, message = "Qualidade do dia deve ser no máximo 10")
    private Integer qualidadeDia;
}
