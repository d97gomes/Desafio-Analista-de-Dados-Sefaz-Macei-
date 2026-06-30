package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DuckDBConnection {

    private static final String URL = "jdbc:duckdb:meubanco.db";

    public static Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            System.out.println("✅ Conectado ao DuckDB com sucesso!");
            return conn;
        } catch (SQLException e) {
            System.err.println("❌ Erro ao conectar ao DuckDB: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}