package br.com.fiap.mentalhealthplatform.application.service;

import br.com.fiap.mentalhealthplatform.application.dto.RegistroDiarioRequestDTO;
import br.com.fiap.mentalhealthplatform.application.dto.RegistroDiarioResponseDTO;
import br.com.fiap.mentalhealthplatform.application.exception.BusinessException;
import br.com.fiap.mentalhealthplatform.application.exception.ResourceNotFoundException;
import br.com.fiap.mentalhealthplatform.application.mapper.RegistroDiarioMapper;
import br.com.fiap.mentalhealthplatform.domain.entity.Paciente;
import br.com.fiap.mentalhealthplatform.domain.entity.RegistroDiario;
import br.com.fiap.mentalhealthplatform.domain.repository.PacienteRepository;
import br.com.fiap.mentalhealthplatform.domain.repository.RegistroDiarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistroDiarioService {

    private final RegistroDiarioRepository repository;
    private final PacienteRepository pacienteRepository;

    @Transactional(readOnly = true)
    public List<RegistroDiarioResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(RegistroDiarioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RegistroDiarioResponseDTO findById(Long id) {
        RegistroDiario registro = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro Diário", "id", id));
        return RegistroDiarioMapper.toResponseDTO(registro);
    }

    @Transactional(readOnly = true)
    public List<RegistroDiarioResponseDTO> findByPacienteId(Long pacienteId) {
        return repository.findByPacienteIdOrderByDataRegistroDesc(pacienteId)
                .stream()
                .map(RegistroDiarioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RegistroDiarioResponseDTO> findRecentesByPaciente(Long pacienteId, int dias) {
        LocalDate dataInicio = LocalDate.now().minusDays(dias);
        return repository.findRecentesByPaciente(pacienteId, dataInicio)
                .stream()
                .map(RegistroDiarioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public RegistroDiarioResponseDTO create(RegistroDiarioRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente", "id", dto.getPacienteId()));

        // Verificar se já existe registro para a mesma data
        repository.findByPacienteIdAndDataRegistro(dto.getPacienteId(), dto.getDataRegistro())
                .ifPresent(r -> {
                    throw new BusinessException(
                        "Já existe um registro diário para este paciente na data " + dto.getDataRegistro()
                    );
                });

        RegistroDiario registro = RegistroDiarioMapper.toEntity(dto, paciente);
        registro = repository.save(registro);
        return RegistroDiarioMapper.toResponseDTO(registro);
    }

    @Transactional
    public RegistroDiarioResponseDTO update(Long id, RegistroDiarioRequestDTO dto) {
        RegistroDiario registro = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro Diário", "id", id));

        // Verificar se a nova data já tem registro (se data foi alterada)
        if (!registro.getDataRegistro().equals(dto.getDataRegistro())) {
            repository.findByPacienteIdAndDataRegistro(dto.getPacienteId(), dto.getDataRegistro())
                    .ifPresent(r -> {
                        throw new BusinessException(
                            "Já existe um registro diário para este paciente na data " + dto.getDataRegistro()
                        );
                    });
        }

        RegistroDiarioMapper.updateEntity(registro, dto);
        registro = repository.save(registro);
        return RegistroDiarioMapper.toResponseDTO(registro);
    }

    @Transactional
    public void delete(Long id) {
        RegistroDiario registro = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro Diário", "id", id));
        repository.delete(registro);
    }
}
