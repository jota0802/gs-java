package br.com.fiap.mentalhealthplatform.application.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    @Size(min = 10, max = 20, message = "Telefone deve ter entre 10 e 20 caracteres")
    private String telefone;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido")
    private String cpf;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento deve ser no passado")
    private LocalDate dataNascimento;

    @Size(max = 10, message = "Sexo deve ter no máximo 10 caracteres")
    private String sexo;

    @Size(max = 1000, message = "Observações devem ter no máximo 1000 caracteres")
    private String observacoes;
}
