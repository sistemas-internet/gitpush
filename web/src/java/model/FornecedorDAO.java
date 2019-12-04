package model;

import model.bean.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.ConnectionFactory;

// COM BASE EM ClienteDAO, fazer FornecedorDAO
public class FornecedorDAO {

    private final Connection conexao;
    PreparedStatement ps = null;
    String sqlSel = "SELECT * FROM CliFor ORDER BY nome ASC;";

    public FornecedorDAO() throws SQLException {
        this.conexao = ConnectionFactory.getInstance().getConnection();
    }

    public String inserir(Fornecedor f) throws SQLException {
        return null;
    }

    public List<Fornecedor> editar(Fornecedor f) throws SQLException {
        return null;
    }

    public String excluir(Fornecedor f) throws SQLException {
        return null;
    }

    public String alterar(Fornecedor f) {
        return null;
    }

    public List<Fornecedor> pesquisar(Fornecedor f) throws SQLException {
        List lista = listar(f);
        return lista;
    }

    private List listar(Fornecedor f) throws SQLException {
        return listar();
    }

    public List<Fornecedor> listar() {
        return null;
    }
}
