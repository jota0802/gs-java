package br.com.fiap.mentalhealthplatform.domain.entity;

import br.com.fiap.mentalhealthplatform.domain.enums.TipoProfissional;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profissionais_saude")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false, unique = true, length = 20)
    private String crp; // Registro profissional

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoProfissional tipoProfissional;

    @Column(length = 200)
    private String especialidade;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Column
    private LocalDateTime dataAtualizacao;

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consulta> consultas = new ArrayList<>();

    @PreUpdate
    protected void onUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}
