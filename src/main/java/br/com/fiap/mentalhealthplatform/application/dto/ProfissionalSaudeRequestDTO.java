package br.com.fiap.mentalhealthplatform.application.dto;

import br.com.fiap.mentalhealthplatform.domain.enums.TipoProfissional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalSaudeRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    @Size(min = 10, max = 20, message = "Telefone deve ter entre 10 e 20 caracteres")
    private String telefone;

    @NotBlank(message = "CRP é obrigatório")
    @Size(min = 5, max = 20, message = "CRP deve ter entre 5 e 20 caracteres")
    private String crp;

    @NotNull(message = "Tipo de profissional é obrigatório")
    private TipoProfissional tipoProfissional;

    @Size(max = 200, message = "Especialidade deve ter no máximo 200 caracteres")
    private String especialidade;

    @Size(max = 1000, message = "Bio deve ter no máximo 1000 caracteres")
    private String bio;
}
