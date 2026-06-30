package analysis;

import database.DuckDBConnection;

import java.sql.Connection;
import java.sql.Statement;

public class QueryExecutor {

    public static void criarTabela(String caminhoCsv) {

        try (Connection conn = DuckDBConnection.conectar();
             Statement stmt = conn.createStatement()) {

            String sql = "DROP TABLE IF EXISTS despesas; " +
                    "CREATE TABLE despesas AS " +
                    "SELECT * FROM read_csv_auto('" + caminhoCsv + "')";

            stmt.execute(sql);

            System.out.println("✅ Tabela criada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void executarAnalise() {

        try (Connection conn = DuckDBConnection.conectar();
             Statement stmt = conn.createStatement()) {

            String sql =
                    "SELECT instituicao, conta, ano, " +
                            "SUM(CASE WHEN estagio = 'EMPENHADO' THEN valor ELSE 0 END) AS empenhado, " +
                            "SUM(CASE WHEN estagio = 'PAGO' THEN valor ELSE 0 END) AS pago, " +
                            "SUM(CASE WHEN estagio = 'PAGO' THEN valor ELSE 0 END) / " +
                            "SUM(CASE WHEN estagio = 'EMPENHADO' THEN valor ELSE 0 END) AS taxa_execucao " +
                            "FROM despesas " +
                            "GROUP BY instituicao, conta, ano " +
                            "ORDER BY taxa_execucao DESC " +
                            "LIMIT 10";

            var rs = stmt.executeQuery(sql);

            System.out.println("\n📊 RESULTADO:");

            while (rs.next()) {
                System.out.println(
                        rs.getString("instituicao") + " | " +
                                rs.getString("conta") + " | " +
                                rs.getInt("ano") + " | " +
                                rs.getDouble("taxa_execucao")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}