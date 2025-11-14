package br.com.fiap.mentalhealthplatform.presentation.controller;

import br.com.fiap.mentalhealthplatform.application.dto.PacienteRequestDTO;
import br.com.fiap.mentalhealthplatform.application.dto.PacienteResponseDTO;
import br.com.fiap.mentalhealthplatform.application.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService service;

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> findAll() {
        List<PacienteResponseDTO> pacientes = service.findAll();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<PacienteResponseDTO>> findAtivos() {
        List<PacienteResponseDTO> pacientes = service.findAtivos();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> findById(@PathVariable Long id) {
        PacienteResponseDTO paciente = service.findById(id);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<PacienteResponseDTO>> findByNome(@RequestParam String nome) {
        List<PacienteResponseDTO> pacientes = service.findByNome(nome);
        return ResponseEntity.ok(pacientes);
    }

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> create(@Valid @RequestBody PacienteRequestDTO dto) {
        PacienteResponseDTO paciente = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody PacienteRequestDTO dto) {
        PacienteResponseDTO paciente = service.update(id, dto);
        return ResponseEntity.ok(paciente);
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
