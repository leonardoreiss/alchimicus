package com.DAO;

import com.Model.Categoria;
import com.Model.Fornecedor;
import com.Model.Produto;
import com.banco.Banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

public class ProdutoDAO implements DAO <Produto> {
    private Produto produto;
    private static PreparedStatement pst;
    private ResultSet rs;
    @Override
    public boolean insere(Produto dado) throws SQLException {
        boolean inseriu = false;
        Banco.conectar();

        String sql = "INSERT INTO produto (codProd, nomeProd, precoProd, "
                + "qtdEstoqueProd,codCat, codForn, descricao, foto) values (?, ?, ?, ?, ?, ?,?,?)";

        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setInt(1, dado.getCodProd());
        pst.setString(2, dado.getNomeProd());
        pst.setFloat(3,dado.getPrecoProd());
        pst.setInt(4, dado.getQtdEstoqueProd());
        pst.setInt(5,dado.getCategoria().getCodCat());
        pst.setInt(6,dado.getFornecedor().getCodForn());
        pst.setString(7, dado.getDescricao());
        pst.setString(8, dado.getFoto());

        if(pst.executeUpdate() > 0)
            inseriu = true;
        else
            inseriu = false;

        Banco.desconectar();
        return inseriu;
    }

    @Override
    public boolean remove(Produto dado) throws SQLException {
        boolean removeu;
        Banco.conectar();

        String sql = "DELETE FROM produto WHERE codProd = ?";

        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, dado.getCodProd());

        if(pst.executeUpdate() > 0)
            removeu = true;
        else
            removeu = false;

        Banco.desconectar();

        return removeu;
    }

    @Override
    public boolean altera(Produto dado) throws SQLException {
        boolean alterou;
        Banco.conectar();

        String sql = "UPDATE produto SET nomeProd = ?, precoProd = ?, qtdEstoqueProd = ?, descricao = ?, codCat = ?, codForn = ?, foto = ? WHERE codProd = ?";

        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setString(1, dado.getNomeProd());
        pst.setFloat(2,dado.getPrecoProd());
        pst.setInt(3, dado.getQtdEstoqueProd());
        pst.setString(4, dado.getDescricao());
        pst.setInt(5, dado.getCategoria().getCodCat());
        pst.setInt(6, dado.getFornecedor().getCodForn());
        pst.setString(7, dado.getFoto());
        pst.setInt(8, dado.getCodProd());

        if(pst.executeUpdate() > 0)
            alterou = true;
        else
            alterou = false;

        Banco.desconectar();

        return alterou;
    }

    @Override
    public Produto buscaID(Produto dado) throws SQLException {

        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        produto = null;
        String sql = "SELECT * FROM produto WHERE codProd = ?";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, dado.getCodProd());
        rs = pst.executeQuery();

        if(rs.next()) {

            produto = new Produto();

            produto.setCodProd(rs.getInt("codProd"));
            produto.setNomeProd(rs.getString("nomeProd"));
            produto.setPrecoProd(rs.getFloat("precoProd"));
            produto.setQtdEstoqueProd(rs.getInt("qtdEstoqueProd"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setFoto(rs.getString("foto"));

            Categoria categoria = new Categoria();
            Fornecedor fornecedor = new Fornecedor();

            categoria.setCodCat(rs.getInt("codCat"));
            fornecedor.setCodForn(rs.getInt("codForn"));

            categoria = categoriaDAO.buscaID(categoria);
            fornecedor = fornecedorDAO.buscaID(fornecedor);

            produto.setCategoria(categoria);
            produto.setFornecedor(fornecedor);
        }

        Banco.desconectar();

        return produto;
    }

    @Override
    public Collection<Produto> lista(String filtro) throws SQLException {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        Collection<Produto> listagem = new ArrayList<>();
        produto = null;
        String sql = "SELECT * FROM produto ";

        if(filtro.length() != 0) {
            sql += "WHERE " + filtro;
        }

        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);
        rs = pst.executeQuery();

        while(rs.next()) {
            produto = new Produto();
            produto.setNomeProd(rs.getString("nomeProd"));
            produto.setPrecoProd(rs.getInt("precoProd"));
            produto.setQtdEstoqueProd(rs.getInt("qtdEstoqueProd"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setFoto(rs.getString("foto"));

            Categoria categoria = new Categoria();
            Fornecedor fornecedor = new Fornecedor();

            categoria.setCodCat(rs.getInt("codCat"));
            fornecedor.setCodForn(rs.getInt("codForn"));

            categoria = categoriaDAO.buscaID(categoria);
            fornecedor = fornecedorDAO.buscaID(fornecedor);

            produto.setCategoria(categoria);
            produto.setFornecedor(fornecedor);

            listagem.add(produto);
        }

        Banco.desconectar();

        return listagem;
    }

    public Stack<Produto> listaProdutoDisponivel() throws SQLException{
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        Stack<Produto> listagem = new Stack<>();
        produto = null;
        String sql = "SELECT * FROM produto WHERE qtdEstoqueProd > 0";

        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);
        rs = pst.executeQuery();

        while(rs.next()) {
            produto = new Produto();
            produto.setCodProd(rs.getInt("codProd"));
            produto.setNomeProd(rs.getString("nomeProd"));
            produto.setPrecoProd(rs.getFloat("precoProd"));
            produto.setQtdEstoqueProd(rs.getInt("qtdEstoqueProd"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setFoto(rs.getString("foto"));

            Categoria categoria = new Categoria();
            Fornecedor fornecedor = new Fornecedor();

            categoria.setCodCat(rs.getInt("codCat"));
            fornecedor.setCodForn(rs.getInt("codForn"));

            categoria = categoriaDAO.buscaID(categoria);
            fornecedor = fornecedorDAO.buscaID(fornecedor);

            produto.setCategoria(categoria);
            produto.setFornecedor(fornecedor);

            listagem.add(produto);
        }

        Banco.desconectar();

        return listagem;
    }

    public Collection<Produto> buscaGeral(String pesquisa) throws SQLException{
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        Collection<Produto> listagem = new ArrayList<>();
        produto = null;
        String sql = "SELECT * FROM produto INNER JOIN categoria ON produto.codCat = categoria.codCat WHERE produto.codProd LIKE '% ? %' OR produto.nomeProd LIKE'% ? %' OR categoria.nomeCat LIKE '% ? %' AND produto.qtdEstoqueProd > 0;";

        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1,pesquisa);
        pst.setString(2,pesquisa);
        pst.setString(3,pesquisa);

        rs = pst.executeQuery();

        while(rs.next()) {
            produto = new Produto();
            produto.setNomeProd(rs.getString("nomeProd"));
            produto.setPrecoProd(rs.getFloat("preco"));
            produto.setQtdEstoqueProd(rs.getInt("qtdEstoqueProd"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setFoto(rs.getString("foto"));

            Categoria categoria = new Categoria();
            Fornecedor fornecedor = new Fornecedor();

            categoria.setCodCat(rs.getInt("codCat"));
            fornecedor.setCodForn(rs.getInt("codForn"));

            categoria = categoriaDAO.buscaID(categoria);
            fornecedor = fornecedorDAO.buscaID(fornecedor);

            produto.setCategoria(categoria);
            produto.setFornecedor(fornecedor);

            listagem.add(produto);
        }

        Banco.desconectar();

        return listagem;
    }
}
