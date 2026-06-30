package pipeline;

import model.Despesa;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {

    public static void escrever(List<Despesa> despesas, String caminhoArquivo) {

        try (FileWriter writer = new FileWriter(caminhoArquivo)) {

            writer.append("instituicao,uf,populacao,conta,estagio,valor,ano\n");

            for (Despesa d : despesas) {

                writer.append(escapar(d.instituicao())).append(",");
                writer.append(d.uf()).append(",");
                writer.append(String.valueOf(d.populacao())).append(",");
                writer.append(escapar(d.conta())).append(",");
                writer.append(d.estagio().name()).append(",");
                writer.append(String.valueOf(d.valor())).append(",");
                writer.append(String.valueOf(d.ano())).append("\n");
            }

            System.out.println("CSV gerado com sucesso em: " + caminhoArquivo);

        } catch (IOException e) {
            System.err.println("Erro ao escrever CSV: " + e.getMessage());
        }
    }

    private static String escapar(String texto) {
        if (texto.contains(",") || texto.contains("\"")) {
            texto = texto.replace("\"", "\"\"");
            return "\"" + texto + "\"";
        }
        return texto;
    }
}
