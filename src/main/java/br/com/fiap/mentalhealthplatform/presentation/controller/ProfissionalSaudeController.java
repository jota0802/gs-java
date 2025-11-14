package br.com.fiap.mentalhealthplatform.presentation.controller;

import br.com.fiap.mentalhealthplatform.application.dto.ProfissionalSaudeRequestDTO;
import br.com.fiap.mentalhealthplatform.application.dto.ProfissionalSaudeResponseDTO;
import br.com.fiap.mentalhealthplatform.application.service.ProfissionalSaudeService;
import br.com.fiap.mentalhealthplatform.domain.enums.TipoProfissional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profissionais")
@RequiredArgsConstructor
public class ProfissionalSaudeController {

    private final ProfissionalSaudeService service;

    @GetMapping
    public ResponseEntity<List<ProfissionalSaudeResponseDTO>> findAll() {
        List<ProfissionalSaudeResponseDTO> profissionais = service.findAll();
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<ProfissionalSaudeResponseDTO>> findAtivos() {
        List<ProfissionalSaudeResponseDTO> profissionais = service.findAtivos();
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalSaudeResponseDTO> findById(@PathVariable Long id) {
        ProfissionalSaudeResponseDTO profissional = service.findById(id);
        return ResponseEntity.ok(profissional);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ProfissionalSaudeResponseDTO>> findByTipo(@PathVariable TipoProfissional tipo) {
        List<ProfissionalSaudeResponseDTO> profissionais = service.findByTipoProfissional(tipo);
        return ResponseEntity.ok(profissionais);
    }

    @PostMapping
    public ResponseEntity<ProfissionalSaudeResponseDTO> create(@Valid @RequestBody ProfissionalSaudeRequestDTO dto) {
        ProfissionalSaudeResponseDTO profissional = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(profissional);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfissionalSaudeResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ProfissionalSaudeRequestDTO dto) {
        ProfissionalSaudeResponseDTO profissional = service.update(id, dto);
        return ResponseEntity.ok(profissional);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
