package com.DAO;

import com.banco.Banco;
import com.Model.Funcionario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
public class FuncionarioDAO implements DAO <Funcionario>{
    private Funcionario funcionario;
    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public boolean insere(Funcionario dado) throws SQLException {
        return false;
    }

    @Override
    public boolean remove(Funcionario dado) throws SQLException {
        return false;
    }

    @Override
    public boolean altera(Funcionario dado) throws SQLException {
        return false;
    }

    @Override
    public Funcionario buscaID(Funcionario dado) throws SQLException {
        return null;
    }

    @Override
    public Collection<Funcionario> lista(String filtro) throws SQLException {
        return null;
    }

    public Funcionario buscaLogin(Funcionario dado) throws SQLException{
        funcionario = null;
        String sql = "SELECT * FROM funcionarios INNER JOIN login ON funcionarios.codFunc = login.codFunc " +
                "WHERE login.user= ? AND login.senha=?";
        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1,dado.getUser());
        pst.setString(2,dado.getSenha());
        rs = pst.executeQuery();

        if(rs.next()){
            funcionario = new Funcionario();
            funcionario.setCodFunc(rs.getInt("codFunc"));
            funcionario.setCargoFunc(rs.getString("cargoFunc"));
            funcionario.setNomeFunc(rs.getString("nomeFunc"));
            funcionario.setSobrenomeFunc(rs.getString("sobrenomeFunc"));
            funcionario.setUser(rs.getString("user"));
        }
        Banco.desconectar();
        return funcionario;
    }
}
