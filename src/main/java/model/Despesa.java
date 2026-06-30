package model;

public record Despesa (
        String instituicao,
        String uf,
        long populacao,
        String estagio,
        double valor,
        int ano
){
}
