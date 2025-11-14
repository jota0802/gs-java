package br.com.fiap.mentalhealthplatform.domain.repository;

import br.com.fiap.mentalhealthplatform.domain.entity.Consulta;
import br.com.fiap.mentalhealthplatform.domain.enums.StatusConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    
    List<Consulta> findByPacienteId(Long pacienteId);
    
    List<Consulta> findByProfissionalId(Long profissionalId);
    
    List<Consulta> findByStatus(StatusConsulta status);
    
    List<Consulta> findByDataHoraConsultaBetween(LocalDateTime inicio, LocalDateTime fim);
    
    @Query("SELECT c FROM Consulta c WHERE c.paciente.id = :pacienteId AND c.status = :status")
    List<Consulta> findByPacienteIdAndStatus(Long pacienteId, StatusConsulta status);
    
    @Query("SELECT c FROM Consulta c WHERE c.profissional.id = :profissionalId AND c.dataHoraConsulta >= :dataInicio ORDER BY c.dataHoraConsulta")
    List<Consulta> findProximasConsultasProfissional(Long profissionalId, LocalDateTime dataInicio);
}
