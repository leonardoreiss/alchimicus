package com.DAO;

import com.Model.Fornecedor;
import com.banco.Banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class FornecedorDAO implements DAO<Fornecedor> {

    private Fornecedor fornecedor;
    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public boolean insere(Fornecedor dado) throws SQLException {
        boolean inseriu;

        Banco.conectar();

        String sql = "INSERT INTO fornecedores (codForn, cnBruxo, nomeForn, enderecoForn, emailForn, telefoneForn) values (?, ?, ?,?,?,?)";

        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setInt(1, dado.getCodForn());
        pst.setString(2, dado.getCnBruxo());
        pst.setString(3, dado.getNomeForn());
        pst.setString(4, dado.getEnderecoForn());
        pst.setString(5, dado.getEmailForn());
        pst.setString(6, dado.getTelefoneForn());


        if (pst.executeUpdate() > 0)
            inseriu = true;
        else
            inseriu = false;
        Banco.desconectar();
        return inseriu;
    }

    @Override
    public boolean remove(Fornecedor dado) throws SQLException {
        boolean removeu;
        if (produtoFonecedor(dado) == true) {
            removeu = false;
        } else {
            Banco.conectar();

            String sql = "DELETE FROM fornecedores WHERE codForn = ?";

            pst = Banco.obterConexao().prepareStatement(sql);
            pst.setInt(1, dado.getCodForn());

            if (pst.executeUpdate() > 0)
                removeu = true;
            else
                removeu = false;
            Banco.desconectar();
        }
        return removeu;
    }

    @Override
    public boolean altera(Fornecedor dado) throws SQLException {
        boolean alterou;
        Banco.conectar();

        String sql = "UPDATE fornecedores SET nomeForn = ?, cnBruxo = ?, enderecoForn = ?, emailForn = ?, telefoneForn = ? WHERE codForn = ?";

        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setString(1, dado.getNomeForn());
        pst.setString(2, dado.getCnBruxo());
        pst.setString(3, dado.getEnderecoForn());
        pst.setString(4, dado.getEmailForn());
        pst.setString(5, dado.getTelefoneForn());
        pst.setInt(6, dado.getCodForn());

        if (pst.executeUpdate() > 0)
            alterou = true;
        else
            alterou = false;
        Banco.desconectar();
        return alterou;
    }

    @Override
    public Fornecedor buscaID(Fornecedor dado) throws SQLException {
        fornecedor = null;
        String sql = "SELECT * FROM fornecedores WHERE codForn = ?";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, dado.getCodForn());
        rs = pst.executeQuery();

        if (rs.next()) {
            fornecedor = new Fornecedor();
            fornecedor.setCodForn(rs.getInt("codForn"));
            fornecedor.setCnBruxo(rs.getString("cnBruxo"));
            fornecedor.setNomeForn(rs.getString("nomeForn"));
            fornecedor.setEnderecoForn(rs.getString("enderecoForn"));
            fornecedor.setEmailForn(rs.getString("emailForn"));
            fornecedor.setTelefoneForn(rs.getString("telefoneForn"));
        }

        Banco.desconectar();
        return fornecedor;
    }

    @Override
    public Collection<Fornecedor> lista(String filtro) throws SQLException {
        Collection<Fornecedor> listagem = new ArrayList<>();

        fornecedor = null;

        String sql = "SELECT * FROM fornecedores ";

        if (filtro.length() != 0) {
            sql += "WHERE " + filtro;
        }

        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);

        rs = pst.executeQuery();

        while (rs.next()) {
            fornecedor = new Fornecedor();
            fornecedor.setCodForn(rs.getInt("codForn"));
            fornecedor.setCnBruxo(rs.getString("cnBruxo"));
            fornecedor.setNomeForn(rs.getString("nomeForn"));
            fornecedor.setEnderecoForn(rs.getString("enderecoForn"));
            fornecedor.setEmailForn(rs.getString("emailForn"));
            fornecedor.setTelefoneForn(rs.getString("telefoneForn"));

            listagem.add(fornecedor);
        }

        Banco.desconectar();

        return listagem;
    }

    public boolean produtoFonecedor(Fornecedor dado) throws SQLException {
        boolean existe;
        Banco.conectar();

        String sql = "SELECT codProd FROM produto WHERE codForn = ?";

        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, dado.getCodForn());
        rs = pst.executeQuery();
        if (rs.next())
            existe = true;
        else
            existe = false;
        Banco.desconectar();
        return existe;

    }

}
