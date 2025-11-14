package br.com.fiap.mentalhealthplatform.domain.enums;

public enum NivelAnsiedade {
    NENHUMA("Nenhuma", 0),
    LEVE("Leve", 1),
    MODERADA("Moderada", 2),
    GRAVE("Grave", 3),
    MUITO_GRAVE("Muito Grave", 4);

    private final String descricao;
    private final int valor;

    NivelAnsiedade(String descricao, int valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getValor() {
        return valor;
    }
}
