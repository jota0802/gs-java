package br.com.fiap.mentalhealthplatform.application.dto;

import br.com.fiap.mentalhealthplatform.domain.enums.TipoProfissional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalSaudeResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crp;
    private TipoProfissional tipoProfissional;
    private String especialidade;
    private String bio;
    private Boolean ativo;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
}
