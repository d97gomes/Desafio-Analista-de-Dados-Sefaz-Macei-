package model;

public record Despesa(
        String instituicao,
        String uf,
        long populacao,
        String conta,
        EstagioDespesa estagio,
        double valor,
        int ano
) {
}