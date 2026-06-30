package pipeline;

import model.Despesa;
import model.EstagioDespesa;

public class Transformer {

    public static Despesa transformar(String linha, int ano) {

        try {
            String[] colunas = linha.split(";");

            if (colunas.length < 8) return null;

            String instituicao = limpar(colunas[0]);
            String uf = limpar(colunas[2]);
            String populacaoStr = limpar(colunas[3]);
            String estagioStr = limpar(colunas[4]);
            String conta = limpar(colunas[5]);
            String valorStr = limpar(colunas[7]);

            EstagioDespesa estagio = EstagioDespesa.fromString(estagioStr);
            if (estagio == null) return null;

            if (conta.contains("Despesas") || conta.startsWith("FU")) {
                return null;
            }

            long populacao = Long.parseLong(populacaoStr);

            valorStr = valorStr.replace(".", "").replace(",", ".");
            double valor = Double.parseDouble(valorStr);

            return new Despesa(
                    instituicao,
                    uf,
                    populacao,
                    conta,
                    estagio,
                    valor,
                    ano
            );

        } catch (Exception e) {
            return null;
        }
    }

    private static String limpar(String valor) {
        return valor.replace("\"", "").trim();
    }
}
