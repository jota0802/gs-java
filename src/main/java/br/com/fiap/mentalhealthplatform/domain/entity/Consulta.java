package br.com.fiap.mentalhealthplatform.domain.entity;

import br.com.fiap.mentalhealthplatform.domain.enums.StatusConsulta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissional_id", nullable = false)
    private ProfissionalSaude profissional;

    @Column(nullable = false)
    private LocalDateTime dataHoraConsulta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConsulta status = StatusConsulta.AGENDADA;

    @Column(length = 200)
    private String motivoConsulta;

    @Column(columnDefinition = "TEXT")
    private String observacoesProfissional;

    @Column(columnDefinition = "TEXT")
    private String prescricoes;

    @Column
    private Integer duracaoMinutos;

    @Column(length = 200)
    private String localConsulta;

    @Column
    private Boolean consultaOnline = false;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Column
    private LocalDateTime dataAtualizacao;

    @PreUpdate
    protected void onUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}
