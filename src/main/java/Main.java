import pipeline.Extractor;
import pipeline.Transformer;
import pipeline.CsvWriter;
import model.Despesa;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String basePath = "dados_compactos/";
        String caminhoCsv = "dados_limpos.csv";

        List<Despesa> todasDespesas = new ArrayList<>();

        String[] anos = {"2020", "2021", "2022", "2023", "2024", "2025"};

        for (String ano : anos) {

            String caminhoZip;

            if (ano.equals("2020")) {
                caminhoZip = basePath + ano + "/finbra_CAP_DespesasporFuncao(AnexoI-E) (1).zip";
            } else {
                caminhoZip = basePath + ano + "/finbra_CAP_DespesasporFuncao(AnexoI-E).zip";
            }

            System.out.println("📦 Processando ano: " + ano);

            List<String> linhas = Extractor.extrairLinhas(caminhoZip);

            for (String linha : linhas) {
                Despesa d = Transformer.transformar(linha, Integer.parseInt(ano));

                if (d != null) {
                    todasDespesas.add(d);
                }
            }
        }

        // ✅ CSV final consolidado
        CsvWriter.escrever(todasDespesas, caminhoCsv);

        System.out.println("✅ CSV CONSOLIDADO CRIADO!");
    }
}