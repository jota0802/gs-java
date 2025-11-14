package br.com.fiap.mentalhealthplatform.application.mapper;

import br.com.fiap.mentalhealthplatform.application.dto.RegistroDiarioRequestDTO;
import br.com.fiap.mentalhealthplatform.application.dto.RegistroDiarioResponseDTO;
import br.com.fiap.mentalhealthplatform.domain.entity.Paciente;
import br.com.fiap.mentalhealthplatform.domain.entity.RegistroDiario;

public class RegistroDiarioMapper {

    public static RegistroDiario toEntity(RegistroDiarioRequestDTO dto, Paciente paciente) {
        RegistroDiario registro = new RegistroDiario();
        registro.setPaciente(paciente);
        registro.setDataRegistro(dto.getDataRegistro());
        registro.setNivelHumor(dto.getNivelHumor());
        registro.setNivelAnsiedade(dto.getNivelAnsiedade());
        registro.setHorasSono(dto.getHorasSono());
        registro.setPraticouExercicio(dto.getPraticouExercicio());
        registro.setAnotacoes(dto.getAnotacoes());
        registro.setGatilhosEmocionais(dto.getGatilhosEmocionais());
        registro.setQualidadeDia(dto.getQualidadeDia());
        return registro;
    }

    public static RegistroDiarioResponseDTO toResponseDTO(RegistroDiario entity) {
        RegistroDiarioResponseDTO dto = new RegistroDiarioResponseDTO();
        dto.setId(entity.getId());
        dto.setPacienteId(entity.getPaciente().getId());
        dto.setPacienteNome(entity.getPaciente().getNome());
        dto.setDataRegistro(entity.getDataRegistro());
        dto.setNivelHumor(entity.getNivelHumor());
        dto.setNivelAnsiedade(entity.getNivelAnsiedade());
        dto.setHorasSono(entity.getHorasSono());
        dto.setPraticouExercicio(entity.getPraticouExercicio());
        dto.setAnotacoes(entity.getAnotacoes());
        dto.setGatilhosEmocionais(entity.getGatilhosEmocionais());
        dto.setQualidadeDia(entity.getQualidadeDia());
        dto.setDataCadastro(entity.getDataCadastro());
        dto.setDataAtualizacao(entity.getDataAtualizacao());
        return dto;
    }

    public static void updateEntity(RegistroDiario entity, RegistroDiarioRequestDTO dto) {
        entity.setDataRegistro(dto.getDataRegistro());
        entity.setNivelHumor(dto.getNivelHumor());
        entity.setNivelAnsiedade(dto.getNivelAnsiedade());
        entity.setHorasSono(dto.getHorasSono());
        entity.setPraticouExercicio(dto.getPraticouExercicio());
        entity.setAnotacoes(dto.getAnotacoes());
        entity.setGatilhosEmocionais(dto.getGatilhosEmocionais());
        entity.setQualidadeDia(dto.getQualidadeDia());
    }
}
