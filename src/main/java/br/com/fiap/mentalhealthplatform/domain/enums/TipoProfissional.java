package br.com.fiap.mentalhealthplatform.domain.enums;

public enum TipoProfissional {
    PSICOLOGO("Psicólogo"),
    PSIQUIATRA("Psiquiatra"),
    TERAPEUTA("Terapeuta"),
    COACH("Coach de Bem-Estar"),
    ASSISTENTE_SOCIAL("Assistente Social");

    private final String descricao;

    TipoProfissional(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
