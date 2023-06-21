package com.controller;

import com.Tela.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class EstoqueController {
    public Label alchimicus_logo;
    public Label administratione;
    public Button btn_adicionarItens;
    public Button btn_novaCategoria;
    public Button btn_Fornecedor;
    public Button btn_back;
    public void btnadicionarItens_Click(ActionEvent actionEvent) throws IOException {
        EstoqueTela.tela.close();
        ProdutoTela produtotela = new ProdutoTela();
        produtotela.start(new Stage());
    }
    public void btnNovoCategoria_Click(ActionEvent actionEvent) throws IOException{
        EstoqueTela.tela.close();
        CategoriaTela categoriaTela = new CategoriaTela();
        categoriaTela.start(new Stage());
    }
    public void btnFornecedor_Click(ActionEvent actionEvent) throws IOException{
        EstoqueTela.tela.close();
        FornecedorTela fornecedorTela = new FornecedorTela();
        fornecedorTela.start(new Stage());
    }
    public void btnBack_Click(ActionEvent actionEvent) throws IOException{
        EstoqueTela.tela.close();
        Adm adm = new Adm();
        adm.start(new Stage());
    }

    public void btnVerEstoque_Click(ActionEvent actionEvent) throws IOException{
        EstoqueTela.tela.close();
        TabelaEstoqueTela tabelaEstoque = new TabelaEstoqueTela();
        tabelaEstoque.start(new Stage());
    }
}