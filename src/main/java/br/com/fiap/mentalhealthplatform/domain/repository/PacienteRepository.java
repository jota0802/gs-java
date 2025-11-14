package br.com.fiap.mentalhealthplatform.domain.repository;

import br.com.fiap.mentalhealthplatform.domain.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
    Optional<Paciente> findByEmail(String email);
    
    Optional<Paciente> findByCpf(String cpf);
    
    List<Paciente> findByAtivoTrue();
    
    List<Paciente> findByNomeContainingIgnoreCase(String nome);
}
