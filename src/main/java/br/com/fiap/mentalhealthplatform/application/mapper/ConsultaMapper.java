package br.com.fiap.mentalhealthplatform.application.mapper;

import br.com.fiap.mentalhealthplatform.application.dto.ConsultaRequestDTO;
import br.com.fiap.mentalhealthplatform.application.dto.ConsultaResponseDTO;
import br.com.fiap.mentalhealthplatform.domain.entity.Consulta;
import br.com.fiap.mentalhealthplatform.domain.entity.Paciente;
import br.com.fiap.mentalhealthplatform.domain.entity.ProfissionalSaude;
import br.com.fiap.mentalhealthplatform.domain.enums.StatusConsulta;

public class ConsultaMapper {

    public static Consulta toEntity(ConsultaRequestDTO dto, Paciente paciente, ProfissionalSaude profissional) {
        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        consulta.setProfissional(profissional);
        consulta.setDataHoraConsulta(dto.getDataHoraConsulta());
        consulta.setStatus(dto.getStatus() != null ? dto.getStatus() : StatusConsulta.AGENDADA);
        consulta.setMotivoConsulta(dto.getMotivoConsulta());
        consulta.setObservacoesProfissional(dto.getObservacoesProfissional());
        consulta.setPrescricoes(dto.getPrescricoes());
        consulta.setDuracaoMinutos(dto.getDuracaoMinutos());
        consulta.setLocalConsulta(dto.getLocalConsulta());
        consulta.setConsultaOnline(dto.getConsultaOnline() != null ? dto.getConsultaOnline() : false);
        return consulta;
    }

    public static ConsultaResponseDTO toResponseDTO(Consulta entity) {
        ConsultaResponseDTO dto = new ConsultaResponseDTO();
        dto.setId(entity.getId());
        dto.setPacienteId(entity.getPaciente().getId());
        dto.setPacienteNome(entity.getPaciente().getNome());
        dto.setProfissionalId(entity.getProfissional().getId());
        dto.setProfissionalNome(entity.getProfissional().getNome());
        dto.setDataHoraConsulta(entity.getDataHoraConsulta());
        dto.setStatus(entity.getStatus());
        dto.setMotivoConsulta(entity.getMotivoConsulta());
        dto.setObservacoesProfissional(entity.getObservacoesProfissional());
        dto.setPrescricoes(entity.getPrescricoes());
        dto.setDuracaoMinutos(entity.getDuracaoMinutos());
        dto.setLocalConsulta(entity.getLocalConsulta());
        dto.setConsultaOnline(entity.getConsultaOnline());
        dto.setDataCadastro(entity.getDataCadastro());
        dto.setDataAtualizacao(entity.getDataAtualizacao());
        return dto;
    }

    public static void updateEntity(Consulta entity, ConsultaRequestDTO dto) {
        entity.setDataHoraConsulta(dto.getDataHoraConsulta());
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
        entity.setMotivoConsulta(dto.getMotivoConsulta());
        entity.setObservacoesProfissional(dto.getObservacoesProfissional());
        entity.setPrescricoes(dto.getPrescricoes());
        entity.setDuracaoMinutos(dto.getDuracaoMinutos());
        entity.setLocalConsulta(dto.getLocalConsulta());
        if (dto.getConsultaOnline() != null) {
            entity.setConsultaOnline(dto.getConsultaOnline());
        }
    }
}
