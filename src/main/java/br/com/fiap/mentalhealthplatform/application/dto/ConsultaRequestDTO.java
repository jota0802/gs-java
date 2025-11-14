package br.com.fiap.mentalhealthplatform.application.dto;

import br.com.fiap.mentalhealthplatform.domain.enums.StatusConsulta;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaRequestDTO {

    @NotNull(message = "ID do paciente é obrigatório")
    private Long pacienteId;

    @NotNull(message = "ID do profissional é obrigatório")
    private Long profissionalId;

    @NotNull(message = "Data e hora da consulta é obrigatória")
    @Future(message = "Data e hora da consulta deve ser no futuro")
    private LocalDateTime dataHoraConsulta;

    private StatusConsulta status;

    @Size(max = 200, message = "Motivo da consulta deve ter no máximo 200 caracteres")
    private String motivoConsulta;

    @Size(max = 1000, message = "Observações devem ter no máximo 1000 caracteres")
    private String observacoesProfissional;

    @Size(max = 1000, message = "Prescrições devem ter no máximo 1000 caracteres")
    private String prescricoes;

    private Integer duracaoMinutos;

    @Size(max = 200, message = "Local da consulta deve ter no máximo 200 caracteres")
    private String localConsulta;

    private Boolean consultaOnline;
}
