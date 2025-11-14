package br.com.fiap.mentalhealthplatform.application.service;

import br.com.fiap.mentalhealthplatform.application.dto.ConsultaRequestDTO;
import br.com.fiap.mentalhealthplatform.application.dto.ConsultaResponseDTO;
import br.com.fiap.mentalhealthplatform.application.exception.ResourceNotFoundException;
import br.com.fiap.mentalhealthplatform.application.mapper.ConsultaMapper;
import br.com.fiap.mentalhealthplatform.domain.entity.Consulta;
import br.com.fiap.mentalhealthplatform.domain.entity.Paciente;
import br.com.fiap.mentalhealthplatform.domain.entity.ProfissionalSaude;
import br.com.fiap.mentalhealthplatform.domain.enums.StatusConsulta;
import br.com.fiap.mentalhealthplatform.domain.repository.ConsultaRepository;
import br.com.fiap.mentalhealthplatform.domain.repository.PacienteRepository;
import br.com.fiap.mentalhealthplatform.domain.repository.ProfissionalSaudeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository repository;
    private final PacienteRepository pacienteRepository;
    private final ProfissionalSaudeRepository profissionalRepository;

    @Transactional(readOnly = true)
    public List<ConsultaResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(ConsultaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ConsultaResponseDTO findById(Long id) {
        Consulta consulta = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta", "id", id));
        return ConsultaMapper.toResponseDTO(consulta);
    }

    @Transactional(readOnly = true)
    public List<ConsultaResponseDTO> findByPacienteId(Long pacienteId) {
        return repository.findByPacienteId(pacienteId)
                .stream()
                .map(ConsultaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ConsultaResponseDTO> findByProfissionalId(Long profissionalId) {
        return repository.findByProfissionalId(profissionalId)
                .stream()
                .map(ConsultaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ConsultaResponseDTO> findByStatus(StatusConsulta status) {
        return repository.findByStatus(status)
                .stream()
                .map(ConsultaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ConsultaResponseDTO> findProximasConsultasProfissional(Long profissionalId) {
        return repository.findProximasConsultasProfissional(profissionalId, LocalDateTime.now())
                .stream()
                .map(ConsultaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ConsultaResponseDTO create(ConsultaRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente", "id", dto.getPacienteId()));

        ProfissionalSaude profissional = profissionalRepository.findById(dto.getProfissionalId())
                .orElseThrow(() -> new ResourceNotFoundException("Profissional de Saúde", "id", dto.getProfissionalId()));

        Consulta consulta = ConsultaMapper.toEntity(dto, paciente, profissional);
        consulta = repository.save(consulta);
        return ConsultaMapper.toResponseDTO(consulta);
    }

    @Transactional
    public ConsultaResponseDTO update(Long id, ConsultaRequestDTO dto) {
        Consulta consulta = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta", "id", id));

        // Validar se paciente existe (se foi alterado)
        if (!consulta.getPaciente().getId().equals(dto.getPacienteId())) {
            Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Paciente", "id", dto.getPacienteId()));
            consulta.setPaciente(paciente);
        }

        // Validar se profissional existe (se foi alterado)
        if (!consulta.getProfissional().getId().equals(dto.getProfissionalId())) {
            ProfissionalSaude profissional = profissionalRepository.findById(dto.getProfissionalId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profissional de Saúde", "id", dto.getProfissionalId()));
            consulta.setProfissional(profissional);
        }

        ConsultaMapper.updateEntity(consulta, dto);
        consulta = repository.save(consulta);
        return ConsultaMapper.toResponseDTO(consulta);
    }

    @Transactional
    public void delete(Long id) {
        Consulta consulta = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta", "id", id));
        repository.delete(consulta);
    }

    @Transactional
    public ConsultaResponseDTO updateStatus(Long id, StatusConsulta novoStatus) {
        Consulta consulta = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta", "id", id));
        consulta.setStatus(novoStatus);
        consulta = repository.save(consulta);
        return ConsultaMapper.toResponseDTO(consulta);
    }
}
