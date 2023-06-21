package com.DAO;

import com.Model.Cliente;
import com.banco.Banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ClienteDAO implements DAO<Cliente> {
    private Cliente cliente;
    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public boolean insere(Cliente dado) throws SQLException {
        boolean inseriu;

        Banco.conectar();

        String sql = "INSERT INTO clientes (codBruxo, nomeCliente, nascCliente, enderecoCliente, emailCliente) values (?, ?, ?, ?, ?)";

        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setInt(1, dado.getCodBruxo());
        pst.setString(2, dado.getNomeCliente());
        pst.setString(3, dado.getNascCliente());
        pst.setString(4, dado.getEnderecoCliente());
        pst.setString(5, dado.getEmailCliente());

        if (pst.executeUpdate() > 0)
            inseriu = true;
        else
            inseriu = false;
        Banco.desconectar();
        return inseriu;
    }

    @Override
    public boolean remove(Cliente dado) throws SQLException {
        boolean removeu;
        Banco.conectar();

        String sql = "DELETE FROM clientes WHERE codBruxo = ?";

        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, dado.getCodBruxo());

        if (pst.executeUpdate() > 0)
            removeu = true;
        else
            removeu = false;

        Banco.desconectar();

        return removeu;
    }

    @Override
    public boolean altera(Cliente dado) throws SQLException {
        boolean alterou;
        Banco.conectar();

        String sql = "UPDATE clientes SET nomeCliente = ?, nascCliente = ?,"
                + " enderecoCliente = ?, fotoCliente = ? WHERE codBruxo = ?";

        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setString(1, dado.getNomeCliente());
        pst.setString(2, dado.getNascCliente());
        pst.setString(3, dado.getEnderecoCliente());
        pst.setString(4, dado.getEmailCliente());
        pst.setInt(5, dado.getCodBruxo());

        if (pst.executeUpdate() > 0)
            alterou = true;
        else
            alterou = false;

        Banco.desconectar();
        return alterou;
    }

    @Override
    public Cliente buscaID(Cliente dado) throws SQLException {
        cliente = null;
        String sql = "SELECT * FROM clientes WHERE codBruxo = ?";

        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, dado.getCodBruxo());
        rs = pst.executeQuery();

        if (rs.next()) {
            cliente = new Cliente();
            cliente.setCodBruxo(rs.getInt("codBruxo"));
            cliente.setNomeCliente(rs.getString("nomeCliente"));
            cliente.setNascCliente(rs.getString("nascCliente"));
            cliente.setEnderecoCliente(rs.getString("enderecoCliente"));
            cliente.setFotoCliente(rs.getString("emailCliente"));
        }
        Banco.desconectar();
        return cliente;
    }
    @Override
    public Collection<Cliente> lista(String filtro) throws SQLException {
        Collection<Cliente> listagem = new ArrayList<>();

        cliente = null;
        String sql = "SELECT * FROM clientes ";

        if (filtro.length() != 0) {
            sql += "WHERE " + filtro;
        }

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        rs = pst.executeQuery();

        while (rs.next()) {
            cliente = new Cliente();
            cliente.setCodBruxo(rs.getInt("codBruxo"));
            cliente.setNomeCliente(rs.getString("nomeCliente"));
            cliente.setNascCliente(rs.getString("nascCliente"));
            cliente.setEnderecoCliente(rs.getString("enderecoCliente"));
            cliente.setFotoCliente(rs.getString("emailCliente"));

            listagem.add(cliente);
        }

        Banco.desconectar();
        return listagem;
    }
}
