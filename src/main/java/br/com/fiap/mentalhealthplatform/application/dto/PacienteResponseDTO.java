package br.com.fiap.mentalhealthplatform.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private LocalDate dataNascimento;
    private String sexo;
    private String observacoes;
    private Boolean ativo;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
}
