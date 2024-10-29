import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {
    public static String status = "Nao conectado";

    public static Connection getConnectionMySQL() {
        Connection connection = null;

        String serverName = "localhost";
        String mydatabase = "prestadores_servico";
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        String username = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, username, password);
            status = "conectado";
            System.out.println("Banco " + mydatabase + " " + status);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco " + mydatabase);
            System.out.println(e.getMessage());
        }

        return connection;
    }
}