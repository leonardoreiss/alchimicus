package com.DAO;

import com.Model.Fornecedor;
import com.banco.Banco;
import com.Model.Categoria;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CategoriaDAO implements DAO<Categoria> {
    private Categoria categoria;
    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public boolean insere(Categoria dado) throws SQLException {
        boolean inseriu;
        Banco.conectar();

        String sql = "INSERT INTO categoria (codCat, nomeCat, descricaoCat) values (?, ?, ?)";
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, dado.getCodCat());
        pst.setString(2, dado.getNomeCat());
        pst.setString(3, dado.getDescricaoCat());

        if (pst.executeUpdate() > 0)
            inseriu = true;
        else
            inseriu = false;

        Banco.desconectar();
        return inseriu;
    }

    @Override
    public boolean remove(Categoria dado) throws SQLException {
        boolean removeu;

        if (produtoCategoria(dado) == true) {
            removeu = false;
        } else {

            Banco.conectar();
            String sql = "DELETE FROM categoria WHERE codCat = ?";
            pst = Banco.obterConexao().prepareStatement(sql);
            pst.setInt(1, dado.getCodCat());
            if (pst.executeUpdate() > 0)
                removeu = true;
            else
                removeu = false;
            Banco.desconectar();
        }
        return removeu;
    }

    @Override
    public boolean altera(Categoria dado) throws SQLException {
        boolean alterou;
        Banco.conectar();
        String sql = "UPDATE categoria SET nomeCat = ?, descricaoCat = ? WHERE codCat = ?";

        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, dado.getNomeCat());
        pst.setString(2, dado.getDescricaoCat());
        pst.setInt(3, dado.getCodCat());

        if (pst.executeUpdate() > 0)
            alterou = true;
        else
            alterou = false;

        Banco.desconectar();
        return alterou;
    }

    @Override
    public Categoria buscaID(Categoria dado) throws SQLException {
        categoria = null;
        String sql = "SELECT * FROM categoria WHERE codCat = ?";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, dado.getCodCat());
        rs = pst.executeQuery();

        if (rs.next()) {
            categoria = new Categoria();
            categoria.setCodCat(rs.getInt("codCat"));
            categoria.setNomeCat(rs.getString("nomeCat"));
            categoria.setDescricaoCat(rs.getString("descricaoCat"));
        }
        Banco.desconectar();
        return categoria;
    }

    @Override
    public Collection<Categoria> lista(String filtro) throws SQLException {
        Collection<Categoria> listagem = new ArrayList<>();

        categoria = null;

        String sql = "SELECT * FROM categoria ";
        if (filtro.length() != 0) {
            sql += "WHERE " + filtro;
        }
        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);

        rs = pst.executeQuery();

        while (rs.next()) {
            categoria = new Categoria();
            categoria.setCodCat(rs.getInt("codCat"));
            categoria.setNomeCat(rs.getString("nomeCat"));
            categoria.setDescricaoCat(rs.getString("descricaoCat"));
            listagem.add(categoria);
        }

        Banco.desconectar();
        return listagem;
    }

    private boolean produtoCategoria(Categoria dado) throws SQLException {
        boolean existe;
        Banco.conectar();

        String sql = "SELECT codCat FROM produto WHERE codCat = ?";

        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, dado.getCodCat());
        rs = pst.executeQuery();
        if (rs.next())
            existe = true;
        else
            existe = false;
        Banco.desconectar();
        return existe;
    }
}


