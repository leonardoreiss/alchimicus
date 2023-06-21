package com.DAO;

import com.Model.*;
import com.banco.Banco;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

public class VendaDAO implements DAO<Venda> {
    private java.sql.PreparedStatement pst;
    private java.sql.ResultSet rs;
    private Venda venda;
    private Produto produto;


    @Override
    public boolean insere(Venda dado) throws SQLException {
        Venda codVenda;
        Stack<ProdutoVenda> produtoVendas = new Stack<>();
        String sql = "INSERT INTO venda ( valorTotal, data, codBruxo, codFunc) " +
                " VALUES (?, ?,?, ?)";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);


        pst.setFloat(1, dado.getValorTotal());
        pst.setString(2, dado.getData());
        pst.setInt(3, dado.getCodBruxo());
        pst.setInt(4, dado.getCodFunc());

        int res = pst.executeUpdate();
        codVenda = ReturnId();

        produtoVendas = dado.getProdutoVendas();

        for (int i = 0; i < produtoVendas.size(); i++) {
            sql = "INSERT INTO venda_produto (codVend, codProd, qtdProduto) VALUES (?,?,?)";
            pst = Banco.obterConexao().prepareStatement(sql);
            pst.setInt(1, codVenda.getCodVend());
            pst.setInt(2,produtoVendas.get(i).getCodProd());
            pst.setInt(3,produtoVendas.get(i).getQtdProduto());

            pst.executeUpdate();
            }
        Banco.desconectar();

        return res != 0;
    }
    public Venda ReturnId ()throws SQLException{
        String sql = "SELECT max(codVend) AS 'codVend' FROM venda";
        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        rs = pst.executeQuery();
        if(rs.next()){
            venda = new Venda();
            venda.setCodVend(rs.getInt("codVend"));
        }else{
            venda = null;
        }
        return venda;
    }

    @Override
    public boolean remove(Venda dado) throws SQLException {
        String sql = "DELETE FROM venda WHERE codVend = ?";
        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setInt(1, dado.getCodVend());

        int res = pst.executeUpdate();

        Banco.desconectar();

        return res != 0;
    }

    @Override
    public boolean altera(Venda dado) throws SQLException {
        String sql = "UPDATE venda SET valorTotal = ?, data = ?, codBruxo = ?, codFunc = ? "
                + "WHERE codVend = ?";
        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setFloat(1, dado.getValorTotal());
        pst.setString(2, dado.getData());
        pst.setInt(3, dado.getCodBruxo());
        pst.setInt(4, dado.getCodFunc());
        pst.setInt(5, dado.getCodVend());
        int res = pst.executeUpdate();
        Banco.desconectar();
        return res != 0;
    }

    @Override
    public Venda buscaID(Venda dado) throws SQLException {
        String sql = "SELECT * FROM venda "
                + "WHERE codVend = ?";
        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, dado.getCodVend());
        rs = pst.executeQuery();
        if (rs.next()) {
            venda = new Venda();
            venda.setCodVend(rs.getInt("codVend"));
            venda.setValorTotal(rs.getInt("valorTotal"));
            venda.setData(rs.getString("data"));
            venda.setCodBruxo(rs.getInt("codBruxo"));
            venda.setCodVend(rs.getInt("codFunc"));
        } else {
            venda = null;
        }
        Banco.desconectar();
        return venda;
    }

    @Override
    public Collection<Venda> lista(String filtro) throws SQLException {
        ArrayList<Venda> lista = new ArrayList<>();

        String sql = "SELECT * FROM venda ";

        if (filtro != null && filtro.length() > 0) {
            sql += " WHERE codBruxo = " + filtro;
        }

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        rs = pst.executeQuery();

        while (rs.next()) {
            venda = new Venda();
            venda.setCodVend(rs.getInt("codVend"));
            venda.setValorTotal(rs.getInt("valorTotal"));
            venda.setData(rs.getString("data"));
            venda.setCodBruxo(rs.getInt("codBruxo"));
            venda.setCodFunc(rs.getInt("codFunc"));
            lista.add(venda);
        }

        Banco.desconectar();
        return lista;
    }


    public Produto buscaProduto(Produto dado) throws SQLException{
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        produto = null;
        String sql = "SELECT * FROM produto WHERE nomeProd = ?";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, dado.getNomeProd());
        rs = pst.executeQuery();

        if(rs.next()) {
            produto = new Produto();

            produto.setCodProd(rs.getInt("codProd"));
            produto.setNomeProd(rs.getString("nomeProd"));
            produto.setPrecoProd(rs.getFloat("precoProd"));
            produto.setQtdEstoqueProd(rs.getInt("qtdEstoqueProd"));

            Categoria categoria = new Categoria();
            categoria.setCodCat(rs.getInt("codCat"));
            categoria = categoriaDAO.buscaID(categoria);

            produto.setCategoria(categoria);
        }
        Banco.desconectar();
        return produto;
    }


    public Venda buscaCliente(Venda dado) throws SQLException {
        String sql = "SELECT * FROM venda "
                + "WHERE codBruxo = ?";
        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, dado.getCodBruxo());
        rs = pst.executeQuery();
        if (rs.next()) {
            venda = new Venda();
            venda.setCodVend(rs.getInt("codVend"));
            venda.setValorTotal(rs.getInt("valorTotal"));
            venda.setData(rs.getString("data"));
            venda.setCodBruxo(rs.getInt("codBruxo"));
            venda.setCodFunc(rs.getInt("codFunc"));
        } else {
            venda = null;
        }
        Banco.desconectar();
        return venda;
    }
}
