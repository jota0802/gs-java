package br.com.fiap.mentalhealthplatform.domain.enums;

public enum StatusConsulta {
    AGENDADA("Agendada"),
    CONFIRMADA("Confirmada"),
    EM_ANDAMENTO("Em Andamento"),
    CONCLUIDA("Concluída"),
    CANCELADA("Cancelada"),
    REAGENDADA("Reagendada");

    private final String descricao;

    StatusConsulta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
