package br.com.fiap.mentalhealthplatform.presentation.controller;

import br.com.fiap.mentalhealthplatform.application.dto.RegistroDiarioRequestDTO;
import br.com.fiap.mentalhealthplatform.application.dto.RegistroDiarioResponseDTO;
import br.com.fiap.mentalhealthplatform.application.service.RegistroDiarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros-diarios")
@RequiredArgsConstructor
public class RegistroDiarioController {

    private final RegistroDiarioService service;

    @GetMapping
    public ResponseEntity<List<RegistroDiarioResponseDTO>> findAll() {
        List<RegistroDiarioResponseDTO> registros = service.findAll();
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroDiarioResponseDTO> findById(@PathVariable Long id) {
        RegistroDiarioResponseDTO registro = service.findById(id);
        return ResponseEntity.ok(registro);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<RegistroDiarioResponseDTO>> findByPacienteId(@PathVariable Long pacienteId) {
        List<RegistroDiarioResponseDTO> registros = service.findByPacienteId(pacienteId);
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/paciente/{pacienteId}/recentes")
    public ResponseEntity<List<RegistroDiarioResponseDTO>> findRecentesByPaciente(
            @PathVariable Long pacienteId,
            @RequestParam(defaultValue = "30") int dias) {
        List<RegistroDiarioResponseDTO> registros = service.findRecentesByPaciente(pacienteId, dias);
        return ResponseEntity.ok(registros);
    }

    @PostMapping
    public ResponseEntity<RegistroDiarioResponseDTO> create(@Valid @RequestBody RegistroDiarioRequestDTO dto) {
        RegistroDiarioResponseDTO registro = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroDiarioResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody RegistroDiarioRequestDTO dto) {
        RegistroDiarioResponseDTO registro = service.update(id, dto);
        return ResponseEntity.ok(registro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
