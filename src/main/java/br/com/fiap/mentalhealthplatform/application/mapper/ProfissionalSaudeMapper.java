package br.com.fiap.mentalhealthplatform.application.mapper;

import br.com.fiap.mentalhealthplatform.application.dto.ProfissionalSaudeRequestDTO;
import br.com.fiap.mentalhealthplatform.application.dto.ProfissionalSaudeResponseDTO;
import br.com.fiap.mentalhealthplatform.domain.entity.ProfissionalSaude;

public class ProfissionalSaudeMapper {

    public static ProfissionalSaude toEntity(ProfissionalSaudeRequestDTO dto) {
        ProfissionalSaude profissional = new ProfissionalSaude();
        profissional.setNome(dto.getNome());
        profissional.setEmail(dto.getEmail());
        profissional.setTelefone(dto.getTelefone());
        profissional.setCrp(dto.getCrp());
        profissional.setTipoProfissional(dto.getTipoProfissional());
        profissional.setEspecialidade(dto.getEspecialidade());
        profissional.setBio(dto.getBio());
        return profissional;
    }

    public static ProfissionalSaudeResponseDTO toResponseDTO(ProfissionalSaude entity) {
        ProfissionalSaudeResponseDTO dto = new ProfissionalSaudeResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setCrp(entity.getCrp());
        dto.setTipoProfissional(entity.getTipoProfissional());
        dto.setEspecialidade(entity.getEspecialidade());
        dto.setBio(entity.getBio());
        dto.setAtivo(entity.getAtivo());
        dto.setDataCadastro(entity.getDataCadastro());
        dto.setDataAtualizacao(entity.getDataAtualizacao());
        return dto;
    }

    public static void updateEntity(ProfissionalSaude entity, ProfissionalSaudeRequestDTO dto) {
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setTelefone(dto.getTelefone());
        entity.setCrp(dto.getCrp());
        entity.setTipoProfissional(dto.getTipoProfissional());
        entity.setEspecialidade(dto.getEspecialidade());
        entity.setBio(dto.getBio());
    }
}
