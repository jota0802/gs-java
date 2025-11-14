package br.com.fiap.mentalhealthplatform.application.mapper;

import br.com.fiap.mentalhealthplatform.application.dto.PacienteRequestDTO;
import br.com.fiap.mentalhealthplatform.application.dto.PacienteResponseDTO;
import br.com.fiap.mentalhealthplatform.domain.entity.Paciente;

public class PacienteMapper {

    public static Paciente toEntity(PacienteRequestDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setNome(dto.getNome());
        paciente.setEmail(dto.getEmail());
        paciente.setTelefone(dto.getTelefone());
        paciente.setCpf(dto.getCpf().replaceAll("[^0-9]", ""));
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setSexo(dto.getSexo());
        paciente.setObservacoes(dto.getObservacoes());
        return paciente;
    }

    public static PacienteResponseDTO toResponseDTO(Paciente entity) {
        PacienteResponseDTO dto = new PacienteResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setCpf(entity.getCpf());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setSexo(entity.getSexo());
        dto.setObservacoes(entity.getObservacoes());
        dto.setAtivo(entity.getAtivo());
        dto.setDataCadastro(entity.getDataCadastro());
        dto.setDataAtualizacao(entity.getDataAtualizacao());
        return dto;
    }

    public static void updateEntity(Paciente entity, PacienteRequestDTO dto) {
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setTelefone(dto.getTelefone());
        entity.setCpf(dto.getCpf().replaceAll("[^0-9]", ""));
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setSexo(dto.getSexo());
        entity.setObservacoes(dto.getObservacoes());
    }
}
