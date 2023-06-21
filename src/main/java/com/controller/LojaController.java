package com.controller;

import com.DAO.ProdutoDAO;
import com.DAO.VendaDAO;
import com.Model.Venda;
import com.Model.Produto;
import com.Model.ProdutoVenda;
import com.Tela.LojaTela;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;

public class LojaController implements Initializable {
    public ListView<ProdutoVenda> ListView_sacola;

    public Button btnBack;
    public Button btnComprar;
    public TextField txtnomeProduto;
    public TextField txtCategoria;
    public TextField txtQtd;
    public TextField txtpreco;
    public Button btnPesquisar;
    public TextField txtTotal;
    public Button btnRemover;
    public Button btnAdicionar;
    public TextField txtCodProd;
    public TextField txtTotalCompra;
    public TextField txtQtdDesejado;
    public Label Quantidade;
    private Produto produto;
    private Venda venda;
    private ProdutoDAO daoProd = new ProdutoDAO();
    private VendaDAO vendaDAO = new VendaDAO();
    Stack<ProdutoVenda> produtoVendas = new Stack<>();
    Stack<Produto> produtos = new Stack<>();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            produtos = daoProd.listaProdutoDisponivel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        atualizaValorTotal();
        configuraLostFocusQuantidade();
    }

    private void mensagem(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo, texto, ButtonType.OK);
        alerta.showAndWait();
    }

    private void moveDadosModelTela(Produto p) {
        txtCodProd.setText((Integer.toString(produto.getCodProd())));
        txtnomeProduto.setText(p.getNomeProd());
        txtpreco.setText((Float.toString(produto.getPrecoProd())));
        txtQtd.setText((Integer.toString(produto.getQtdEstoqueProd())));
        txtCategoria.setText(p.getCategoria().getNomeCat());
    }

    public void btnBack_Click(ActionEvent event) throws IOException {
        LojaTela.tela.close();
    }

    public void btnPesquisar_Click(ActionEvent actionEvent) {
        int codProd;
        txtQtd.setText("");
        txtTotal.setText("");
        txtQtdDesejado.setText("");
        produto = new Produto();
        int indiceProd = -1;
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getNomeProd() == txtnomeProduto.getText() ||
                    produtos.get(i).getCodProd() == Integer.parseInt(txtCodProd.getText())) {
                indiceProd = i;
            }
        }
        if (indiceProd > -1) {
            produto = produtos.get(indiceProd);
            codProd = produto.getCodProd();
            if (produto.getQtdEstoqueProd() > 0) {
                moveDadosModelTela(produto);
            } else {
                boolean emListaVenda = false;
                for (int i = 0; i < produtoVendas.size(); i++) {
                    if (produtoVendas.get(i).getCodProd() == codProd) {
                        emListaVenda = true;
                    }
                }

                if (emListaVenda) {
                    moveDadosModelTela(produto);
                }
            }

        } else {
            mensagem("Produto Não Existe!!!",
                    Alert.AlertType.INFORMATION);
        }
    }

    public void btnRemover_Click(ActionEvent actionEvent) {
        if (!produtoVendas.isEmpty()) {
            int qtdDesejadaAnterior = produtoVendas.peek().getQtdProduto();

            ProdutoVenda produtoVendaRemovido = produtoVendas.pop();

            for (int i = 0; i < produtos.size(); i++) {
                if (produtos.get(i).getCodProd() == produtoVendaRemovido.getCodProd()) {
                    produtos.get(i).setQtdEstoqueProd(produtos.get(i).getQtdEstoqueProd() + produtoVendaRemovido.getQtdProduto());
                }
            }

            atualizaValorTotal();
            limparCampos();
            ListView_sacola.getItems().setAll(produtoVendas);
        }
    }

    public void btnAdicionar_Click(ActionEvent actionEvent) {
        String qtdDesejadaText = txtQtdDesejado.getText();
        double totalAnterior = Double.parseDouble(txtTotal.getText());

        ProdutoVenda produtoVenda = new ProdutoVenda();
        produtoVenda.setNomeProd(txtnomeProduto.getText());
        produtoVenda.setCodProd(Integer.parseInt(txtCodProd.getText()));
        produtoVenda.setQtdProduto(Integer.parseInt(qtdDesejadaText));

        produtoVendas.push(produtoVenda);

        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getCodProd() == Integer.parseInt(txtCodProd.getText())) {
                produtos.get(i).setQtdEstoqueProd(produtos.get(i).getQtdEstoqueProd() - Integer.parseInt(qtdDesejadaText));
            }
        }

        atualizaValorTotal();
        ListView_sacola.getItems().setAll(produtoVendas);
    }

    public void btnComprar_Click(ActionEvent actionEvent) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date data = new Date();
        venda = new Venda();
        vendaDAO = new VendaDAO();
        venda.setCodBruxo(1);
        venda.setData(dateFormat.format(data));
        venda.setValorTotal(Float.valueOf(txtTotalCompra.getText()));
        venda.setCodFunc(1);
        venda.setProdutoVendas(produtoVendas);

        try {
            if (vendaDAO.insere(venda)) {

                //atualizar a quantidade dos produtos

                atualizarQtdProdutos();
                mensagem("Dados Incluídos com Sucesso",
                        Alert.AlertType.INFORMATION);
                produtos.clear();
                produtoVendas.clear();
                ListView_sacola.getItems().setAll(produtoVendas);
                produtos = daoProd.listaProdutoDisponivel();
                limparCampos();
                txtTotalCompra.setText("");

            }
        } catch (SQLException ex) {
            mensagem("Erro na Inclusão: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Inclusão" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }


    }

    public void atualizarQtdProdutos() {
        produto = new Produto();
        int codProd;

        try {
            for (int i = 0; i < produtoVendas.size(); i++) {
                codProd = produtoVendas.get(i).getCodProd();

                for (int j = 0; j < produtos.size(); j++) {
                    if (produtos.get(j).getCodProd() == codProd) {
                        produto.setCodProd(produtos.get(j).getCodProd());
                        produto.setQtdEstoqueProd(produtos.get(j).getQtdEstoqueProd());
                        produto.setCategoria(produtos.get(j).getCategoria());
                        produto.setFoto(produtos.get(j).getFoto());
                        produto.setDescricao(produtos.get(j).getDescricao());
                        produto.setFornecedor(produtos.get(j).getFornecedor());
                        produto.setNomeProd(produtos.get(j).getNomeProd());
                        produto.setPrecoProd(produtos.get(j).getPrecoProd());
                    }
                }

                daoProd.altera(produto);
            }
        } catch (SQLException ex) {
            mensagem("Erro na Alteração: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            mensagem("Erro Genérico na Alteração" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }

    }

    public void atualizaValorTotal() {
        double totalProdutosVenda = 0.0;
        int codProd;
        double precoProd = 0.0;

        if (produtoVendas.size() > 0) {
            for (int i = 0; i < produtoVendas.size(); i++) {

                codProd = produtoVendas.get(i).getCodProd();

                for (int j = 0; j < produtos.size(); j++) {

                    if (produtos.get(j).getCodProd() == codProd) {
                        precoProd = produtos.get(j).getPrecoProd();
                    }
                }

                double totalProdVenda =  precoProd * produtoVendas.get(i).getQtdProduto();
                totalProdutosVenda = totalProdutosVenda + totalProdVenda;
            }
        }

        txtTotalCompra.setText(Double.toString(totalProdutosVenda));
    }

    private void configuraLostFocusQuantidade() {
        txtQtdDesejado.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                                Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    if (txtQtdDesejado.getText().length() > 0) {
                        int quantidade = Integer.parseInt(txtQtdDesejado.getText());
                        double preco = Double.parseDouble(txtpreco.getText());
                        double precoTotal = 0.0;
                        precoTotal = quantidade * preco;
                        txtTotal.setText(String.valueOf(precoTotal));
                    }
                }
            }
        });
    }

    private void limparCampos() {
        txtTotal.setText("");
        txtQtd.setText("");
        txtQtdDesejado.setText("");
        txtpreco.setText("");
        txtCategoria.setText("");
        txtnomeProduto.setText("");
        txtCodProd.setText("");
    }
}
