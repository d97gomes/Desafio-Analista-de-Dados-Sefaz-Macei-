import analysis.QueryExecutor;
import pipeline.Extractor;
import pipeline.Transformer;
import pipeline.CsvWriter;
import model.Despesa;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String caminhoZip = "dados_compactos/2020/finbra_CAP_DespesasporFuncao(AnexoI-E) (1).zip"; // AJUSTA AQUI
        String caminhoCsv = "dados_limpos.csv";

        // ✅ 1. extrair linhas do ZIP
        List<String> linhas = Extractor.extrairLinhas(caminhoZip);

        // ✅ 2. transformar em objetos
        List<Despesa> despesas = new ArrayList<>();

        for (String linha : linhas) {
            Despesa d = Transformer.transformar(linha, 2020);

            if (d != null) {
                despesas.add(d);
            }
        }

        // ✅ 3. gerar CSV
        CsvWriter.escrever(despesas, caminhoCsv);


        // 🔥 4. CRIAR TABELA NO DUCKDB
        QueryExecutor.criarTabela(caminhoCsv);

        // 🔥 5. EXECUTAR ANALISE
        QueryExecutor.executarAnalise();


        System.out.println("✅ CSV gerado. Agora podemos analisar.");
    }
}
