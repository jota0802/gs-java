package br.com.fiap.mentalhealthplatform.application.service;

import br.com.fiap.mentalhealthplatform.application.dto.PacienteRequestDTO;
import br.com.fiap.mentalhealthplatform.application.dto.PacienteResponseDTO;
import br.com.fiap.mentalhealthplatform.application.exception.DuplicateResourceException;
import br.com.fiap.mentalhealthplatform.application.exception.ResourceNotFoundException;
import br.com.fiap.mentalhealthplatform.application.mapper.PacienteMapper;
import br.com.fiap.mentalhealthplatform.domain.entity.Paciente;
import br.com.fiap.mentalhealthplatform.domain.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;

    @Transactional(readOnly = true)
    public List<PacienteResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(PacienteMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PacienteResponseDTO> findAtivos() {
        return repository.findByAtivoTrue()
                .stream()
                .map(PacienteMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PacienteResponseDTO findById(Long id) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente", "id", id));
        return PacienteMapper.toResponseDTO(paciente);
    }

    @Transactional(readOnly = true)
    public List<PacienteResponseDTO> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(PacienteMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PacienteResponseDTO create(PacienteRequestDTO dto) {
        String cpfLimpo = dto.getCpf().replaceAll("[^0-9]", "");
        
        // Validar duplicidade de email
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Paciente", "email", dto.getEmail());
        }

        // Validar duplicidade de CPF
        if (repository.findByCpf(cpfLimpo).isPresent()) {
            throw new DuplicateResourceException("Paciente", "CPF", cpfLimpo);
        }

        Paciente paciente = PacienteMapper.toEntity(dto);
        paciente = repository.save(paciente);
        return PacienteMapper.toResponseDTO(paciente);
    }

    @Transactional
    public PacienteResponseDTO update(Long id, PacienteRequestDTO dto) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente", "id", id));

        String cpfLimpo = dto.getCpf().replaceAll("[^0-9]", "");

        // Validar duplicidade de email
        repository.findByEmail(dto.getEmail())
                .ifPresent(p -> {
                    if (!p.getId().equals(id)) {
                        throw new DuplicateResourceException("Paciente", "email", dto.getEmail());
                    }
                });

        // Validar duplicidade de CPF
        repository.findByCpf(cpfLimpo)
                .ifPresent(p -> {
                    if (!p.getId().equals(id)) {
                        throw new DuplicateResourceException("Paciente", "CPF", cpfLimpo);
                    }
                });

        PacienteMapper.updateEntity(paciente, dto);
        paciente = repository.save(paciente);
        return PacienteMapper.toResponseDTO(paciente);
    }

    @Transactional
    public void delete(Long id) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente", "id", id));
        repository.delete(paciente);
    }

    @Transactional
    public void inativar(Long id) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente", "id", id));
        paciente.setAtivo(false);
        repository.save(paciente);
    }
}
