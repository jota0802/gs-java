package br.com.fiap.mentalhealthplatform.domain.enums;

public enum NivelHumor {
    MUITO_BOM("Muito Bom", 5),
    BOM("Bom", 4),
    NEUTRO("Neutro", 3),
    RUIM("Ruim", 2),
    MUITO_RUIM("Muito Ruim", 1);

    private final String descricao;
    private final int valor;

    NivelHumor(String descricao, int valor) {
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
