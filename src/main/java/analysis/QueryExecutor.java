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

    public static void analisarPorFuncao() {

        try (Connection conn = DuckDBConnection.conectar();
             Statement stmt = conn.createStatement()) {

            String sql =
                    "SELECT instituicao, conta, " +
                            "SUM(CASE WHEN estagio = 'EMPENHADO' THEN valor ELSE 0 END) AS empenhado, " +
                            "SUM(CASE WHEN estagio = 'PAGO' THEN valor ELSE 0 END) AS pago, " +
                            "SUM(CASE WHEN estagio = 'PAGO' THEN valor ELSE 0 END) / " +
                            "SUM(CASE WHEN estagio = 'EMPENHADO' THEN valor ELSE 0 END) AS taxa_execucao " +
                            "FROM despesas " +
                            "GROUP BY instituicao, conta " +
                            "ORDER BY taxa_execucao ASC";

            var rs = stmt.executeQuery(sql);

            System.out.println("\n📊 Execução por função:");

            while (rs.next()) {
                System.out.println(
                        rs.getString("instituicao") + " | " +
                                rs.getString("conta") + " | " +
                                rs.getDouble("taxa_execucao")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void rankingCapitais() {

        try (Connection conn = DuckDBConnection.conectar();
             Statement stmt = conn.createStatement()) {

            String sql =
                    "SELECT instituicao, " +
                            "SUM(CASE WHEN estagio = 'PAGO' THEN valor ELSE 0 END) / " +
                            "SUM(CASE WHEN estagio = 'EMPENHADO' THEN valor ELSE 0 END) AS taxa_execucao " +
                            "FROM despesas " +
                            "GROUP BY instituicao " +
                            "ORDER BY taxa_execucao DESC";

            var rs = stmt.executeQuery(sql);

            System.out.println("\n🏆 Ranking de capitais:");

            while (rs.next()) {
                System.out.println(
                        rs.getString("instituicao") + " | " +
                                rs.getDouble("taxa_execucao")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void analisarMaceio() {

        try (Connection conn = DuckDBConnection.conectar();
             Statement stmt = conn.createStatement()) {

            String sql =
                    "SELECT conta, " +
                            "SUM(CASE WHEN estagio = 'PAGO' THEN valor ELSE 0 END) / " +
                            "SUM(CASE WHEN estagio = 'EMPENHADO' THEN valor ELSE 0 END) AS taxa_execucao " +
                            "FROM despesas " +
                            "WHERE instituicao LIKE '%Maceió%' " +
                            "GROUP BY conta";

            var rs = stmt.executeQuery(sql);

            System.out.println("\n📍 Maceió:");

            while (rs.next()) {
                System.out.println(
                        rs.getString("conta") + " | " +
                                rs.getDouble("taxa_execucao")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void analisarAnos() {

        try (Connection conn = DuckDBConnection.conectar();
             Statement stmt = conn.createStatement()) {

            String sql =
                    "SELECT ano, COUNT(DISTINCT instituicao) as total " +
                            "FROM despesas " +
                            "GROUP BY ano";

            var rs = stmt.executeQuery(sql);

            System.out.println("\n📅 Capitais por ano:");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("ano") + " → " +
                                rs.getInt("total")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}