package br.com.fiap.mentalhealthplatform.presentation.controller;

import br.com.fiap.mentalhealthplatform.application.dto.ConsultaRequestDTO;
import br.com.fiap.mentalhealthplatform.application.dto.ConsultaResponseDTO;
import br.com.fiap.mentalhealthplatform.application.service.ConsultaService;
import br.com.fiap.mentalhealthplatform.domain.enums.StatusConsulta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService service;

    @GetMapping
    public ResponseEntity<List<ConsultaResponseDTO>> findAll() {
        List<ConsultaResponseDTO> consultas = service.findAll();
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> findById(@PathVariable Long id) {
        ConsultaResponseDTO consulta = service.findById(id);
        return ResponseEntity.ok(consulta);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ConsultaResponseDTO>> findByPacienteId(@PathVariable Long pacienteId) {
        List<ConsultaResponseDTO> consultas = service.findByPacienteId(pacienteId);
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/profissional/{profissionalId}")
    public ResponseEntity<List<ConsultaResponseDTO>> findByProfissionalId(@PathVariable Long profissionalId) {
        List<ConsultaResponseDTO> consultas = service.findByProfissionalId(profissionalId);
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ConsultaResponseDTO>> findByStatus(@PathVariable StatusConsulta status) {
        List<ConsultaResponseDTO> consultas = service.findByStatus(status);
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/profissional/{profissionalId}/proximas")
    public ResponseEntity<List<ConsultaResponseDTO>> findProximasConsultasProfissional(@PathVariable Long profissionalId) {
        List<ConsultaResponseDTO> consultas = service.findProximasConsultasProfissional(profissionalId);
        return ResponseEntity.ok(consultas);
    }

    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> create(@Valid @RequestBody ConsultaRequestDTO dto) {
        ConsultaResponseDTO consulta = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(consulta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ConsultaRequestDTO dto) {
        ConsultaResponseDTO consulta = service.update(id, dto);
        return ResponseEntity.ok(consulta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ConsultaResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam StatusConsulta status) {
        ConsultaResponseDTO consulta = service.updateStatus(id, status);
        return ResponseEntity.ok(consulta);
    }
}
