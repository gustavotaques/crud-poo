import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrestadorNaoHomologado extends PrestadorServico{

    public PrestadorNaoHomologado() {

    }

    public PrestadorNaoHomologado(String cpf, String nome, double valorHora) {
        this.cpf = cpf;
        this.nome = nome;
        this.valorHora = valorHora;
    }

    @Override
    public void criar_prestador() {
        String sql = "insert into prestador_servico_nao_homologado values(?,?,?)";
        Connection connect = ConnectionMySQL.getConnectionMySQL();

        try {
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, cpf);
            pst.setString(2, nome);
            pst.setDouble(3, valorHora);
            pst.executeUpdate();
            System.out.println("Prestador criado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar prestador: " + e.getMessage());
        }
    }

    @Override
    public void ler_prestador() {
        String sql = "select * from prestador_servico_nao_homologado where cpf = ?";
        Connection connect = ConnectionMySQL.getConnectionMySQL();

        try {
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, cpf);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                nome = rst.getString("nome");
                valorHora = rst.getDouble("valor_hora");
                System.out.printf("CPF: %s\nNome: %s\nValor Hora: %.2f\n", cpf, nome, valorHora);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao ler prestador: " + e.getMessage());
        }
    }

    @Override
    public void atualizar_prestador() {
        String sql = "update prestador_servico_nao_homologado set nome = ?, valor_hora = ? where cpf = ?";
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
                System.out.println("Nao foi possivel atualizar prestador!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar prestador: " + e.getMessage());
        }
    }

    @Override
    public void deletar_prestador() {
        String sql = "delete from prestador_servico_nao_homologado where cpf = ?";
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
            System.out.println("Erro ao deletar prestador: " + e.getMessage());
        }
    }
}
