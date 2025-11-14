package br.com.fiap.mentalhealthplatform.domain.repository;

import br.com.fiap.mentalhealthplatform.domain.entity.ProfissionalSaude;
import br.com.fiap.mentalhealthplatform.domain.enums.TipoProfissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfissionalSaudeRepository extends JpaRepository<ProfissionalSaude, Long> {
    
    Optional<ProfissionalSaude> findByEmail(String email);
    
    Optional<ProfissionalSaude> findByCrp(String crp);
    
    List<ProfissionalSaude> findByTipoProfissional(TipoProfissional tipoProfissional);
    
    List<ProfissionalSaude> findByAtivoTrue();
    
    List<ProfissionalSaude> findByEspecialidadeContainingIgnoreCase(String especialidade);
}
