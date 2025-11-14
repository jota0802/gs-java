package br.com.fiap.mentalhealthplatform.application.dto;

import br.com.fiap.mentalhealthplatform.domain.enums.NivelAnsiedade;
import br.com.fiap.mentalhealthplatform.domain.enums.NivelHumor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDiarioResponseDTO {

    private Long id;
    private Long pacienteId;
    private String pacienteNome;
    private LocalDate dataRegistro;
    private NivelHumor nivelHumor;
    private NivelAnsiedade nivelAnsiedade;
    private Integer horasSono;
    private Boolean praticouExercicio;
    private String anotacoes;
    private String gatilhosEmocionais;
    private Integer qualidadeDia;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
}
