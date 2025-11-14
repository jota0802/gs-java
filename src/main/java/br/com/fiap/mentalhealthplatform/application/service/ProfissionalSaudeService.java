package br.com.fiap.mentalhealthplatform.application.service;

import br.com.fiap.mentalhealthplatform.application.dto.ProfissionalSaudeRequestDTO;
import br.com.fiap.mentalhealthplatform.application.dto.ProfissionalSaudeResponseDTO;
import br.com.fiap.mentalhealthplatform.application.exception.DuplicateResourceException;
import br.com.fiap.mentalhealthplatform.application.exception.ResourceNotFoundException;
import br.com.fiap.mentalhealthplatform.application.mapper.ProfissionalSaudeMapper;
import br.com.fiap.mentalhealthplatform.domain.entity.ProfissionalSaude;
import br.com.fiap.mentalhealthplatform.domain.enums.TipoProfissional;
import br.com.fiap.mentalhealthplatform.domain.repository.ProfissionalSaudeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfissionalSaudeService {

    private final ProfissionalSaudeRepository repository;

    @Transactional(readOnly = true)
    public List<ProfissionalSaudeResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(ProfissionalSaudeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProfissionalSaudeResponseDTO> findAtivos() {
        return repository.findByAtivoTrue()
                .stream()
                .map(ProfissionalSaudeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProfissionalSaudeResponseDTO findById(Long id) {
        ProfissionalSaude profissional = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional de Saúde", "id", id));
        return ProfissionalSaudeMapper.toResponseDTO(profissional);
    }

    @Transactional(readOnly = true)
    public List<ProfissionalSaudeResponseDTO> findByTipoProfissional(TipoProfissional tipo) {
        return repository.findByTipoProfissional(tipo)
                .stream()
                .map(ProfissionalSaudeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProfissionalSaudeResponseDTO create(ProfissionalSaudeRequestDTO dto) {
        // Validar duplicidade de email
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Profissional de Saúde", "email", dto.getEmail());
        }

        // Validar duplicidade de CRP
        if (repository.findByCrp(dto.getCrp()).isPresent()) {
            throw new DuplicateResourceException("Profissional de Saúde", "CRP", dto.getCrp());
        }

        ProfissionalSaude profissional = ProfissionalSaudeMapper.toEntity(dto);
        profissional = repository.save(profissional);
        return ProfissionalSaudeMapper.toResponseDTO(profissional);
    }

    @Transactional
    public ProfissionalSaudeResponseDTO update(Long id, ProfissionalSaudeRequestDTO dto) {
        ProfissionalSaude profissional = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional de Saúde", "id", id));

        // Validar duplicidade de email
        repository.findByEmail(dto.getEmail())
                .ifPresent(p -> {
                    if (!p.getId().equals(id)) {
                        throw new DuplicateResourceException("Profissional de Saúde", "email", dto.getEmail());
                    }
                });

        // Validar duplicidade de CRP
        repository.findByCrp(dto.getCrp())
                .ifPresent(p -> {
                    if (!p.getId().equals(id)) {
                        throw new DuplicateResourceException("Profissional de Saúde", "CRP", dto.getCrp());
                    }
                });

        ProfissionalSaudeMapper.updateEntity(profissional, dto);
        profissional = repository.save(profissional);
        return ProfissionalSaudeMapper.toResponseDTO(profissional);
    }

    @Transactional
    public void delete(Long id) {
        ProfissionalSaude profissional = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional de Saúde", "id", id));
        repository.delete(profissional);
    }

    @Transactional
    public void inativar(Long id) {
        ProfissionalSaude profissional = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional de Saúde", "id", id));
        profissional.setAtivo(false);
        repository.save(profissional);
    }
}
