package br.com.fiap.mentalhealthplatform.domain.repository;

import br.com.fiap.mentalhealthplatform.domain.entity.RegistroDiario;
import br.com.fiap.mentalhealthplatform.domain.enums.NivelHumor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegistroDiarioRepository extends JpaRepository<RegistroDiario, Long> {
    
    List<RegistroDiario> findByPacienteId(Long pacienteId);
    
    List<RegistroDiario> findByPacienteIdOrderByDataRegistroDesc(Long pacienteId);
    
    Optional<RegistroDiario> findByPacienteIdAndDataRegistro(Long pacienteId, LocalDate dataRegistro);
    
    List<RegistroDiario> findByDataRegistroBetween(LocalDate dataInicio, LocalDate dataFim);
    
    List<RegistroDiario> findByNivelHumor(NivelHumor nivelHumor);
    
    @Query("SELECT r FROM RegistroDiario r WHERE r.paciente.id = :pacienteId AND r.dataRegistro >= :dataInicio")
    List<RegistroDiario> findRecentesByPaciente(Long pacienteId, LocalDate dataInicio);
}
