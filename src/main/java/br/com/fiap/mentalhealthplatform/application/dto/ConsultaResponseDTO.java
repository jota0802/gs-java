package br.com.fiap.mentalhealthplatform.application.dto;

import br.com.fiap.mentalhealthplatform.domain.enums.StatusConsulta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaResponseDTO {

    private Long id;
    private Long pacienteId;
    private String pacienteNome;
    private Long profissionalId;
    private String profissionalNome;
    private LocalDateTime dataHoraConsulta;
    private StatusConsulta status;
    private String motivoConsulta;
    private String observacoesProfissional;
    private String prescricoes;
    private Integer duracaoMinutos;
    private String localConsulta;
    private Boolean consultaOnline;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
}
