import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrestadorHomologado extends PrestadorServico {

    public PrestadorHomologado() {

    }

    public PrestadorHomologado(String cpf, String nome, double valorHora) {
        this.cpf = cpf;
        this.nome = nome;
        setValorHora(valorHora);
    }

    @Override
    public void setValorHora(double valorHora) {
        this.valorHora = valorHora * 1.135;
    }

    @Override
    public void criar_prestador() {
        String sql = "insert into prestador_servico_homologado values (?,?,?)";
        Connection connect = ConnectionMySQL.getConnectionMySQL();

        try {
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, cpf);
            pst.setString(2, nome);
            pst.setDouble(3, valorHora);
            pst.execute();
            System.out.println("Prestador criado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Prestador nao criado!" + e.getMessage());
        }
    }

    @Override
    public void ler_prestador() {
        String sql = "select * from prestador_servico_homologado where cpf = ?";
        Connection connect = ConnectionMySQL.getConnectionMySQL();

        try {
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, cpf);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                double valorHora = rs.getDouble("valor_hora");
                System.out.printf("CPF: %s\nNome: %s\nValor Hora: %.2f\n", cpf, nome, valorHora);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar prestador." + e.getMessage());
        }
    }

    @Override
    public void atualizar_prestador() {
        String sql = "update prestador_servico_homologado set nome = ?, valor_hora = ? where cpf = ?";
        Connection connect = ConnectionMySQL.getConnectionMySQL();

        try {
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setDouble(2, valorHora);
            pst.setString(3, cpf);
            int ret = pst.executeUpdate();

            if (ret > 0) {
                System.out.println("Prestador atualizado com sucesso!");
            } else {
                System.out.println("Prestador nao atualizado!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar prestador." + e.getMessage());
        }
    }

    @Override
    public void deletar_prestador() {
        String sql = "delete from prestador_servico_homologado where cpf = ?";
        Connection connect = ConnectionMySQL.getConnectionMySQL();

        try {
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, cpf);
            int ret = pst.executeUpdate();

            if (ret > 0) {
                System.out.println("Prestador deletado com sucesso!");
            } else {
                System.out.println("Nao foi possivel deletar prestador!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar prestador." + e.getMessage());
        }
    }
}
