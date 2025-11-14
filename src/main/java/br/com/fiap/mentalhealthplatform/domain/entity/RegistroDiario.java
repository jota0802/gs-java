package br.com.fiap.mentalhealthplatform.domain.entity;

import br.com.fiap.mentalhealthplatform.domain.enums.NivelAnsiedade;
import br.com.fiap.mentalhealthplatform.domain.enums.NivelHumor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "registros_diarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private LocalDate dataRegistro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelHumor nivelHumor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelAnsiedade nivelAnsiedade;

    @Column(nullable = false)
    private Integer horasSono;

    @Column(nullable = false)
    private Boolean praticouExercicio = false;

    @Column(columnDefinition = "TEXT")
    private String anotacoes;

    @Column(columnDefinition = "TEXT")
    private String gatilhosEmocionais;

    @Column(nullable = false)
    private Integer qualidadeDia; // 1 a 10

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Column
    private LocalDateTime dataAtualizacao;

    @PreUpdate
    protected void onUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}
