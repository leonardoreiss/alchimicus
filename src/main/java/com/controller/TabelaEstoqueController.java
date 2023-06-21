package com.controller;

import com.DAO.ProdutoDAO;
import com.Model.Produto;
import com.Tela.EstoqueTela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TabelaEstoqueController implements Initializable {

    @FXML
    private TableView<Produto> tabela;
    @FXML
    private TableColumn<Produto, Integer> clCodigo;
    @FXML
    private TableColumn<Produto, String> clNome;
    @FXML
    private TableColumn<Produto, Integer> clPreco;
    @FXML
    private TableColumn<Produto, Integer> clQuantidade;

    public void btnBack_Click(ActionEvent actionEvent) throws IOException{
        com.Tela.TabelaEstoqueTela.tela.close();
        EstoqueTela estoqueTela = new EstoqueTela();
        estoqueTela.start(new Stage());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clCodigo.setCellValueFactory(new PropertyValueFactory<>("codProd"));
        clNome.setCellValueFactory(new PropertyValueFactory<>("nomeProd"));
        clPreco.setCellValueFactory(new PropertyValueFactory<>("precoProd"));
        clQuantidade.setCellValueFactory(new PropertyValueFactory<>("qtdEstoqueProd"));
        tabela.setItems(preencheTabela(""));
    }

    private ObservableList<Produto> preencheTabela(String s) {
        ProdutoDAO dao = new ProdutoDAO();
        ObservableList<Produto> produtos = FXCollections.observableArrayList();
        try {
            produtos.addAll(dao.listaProdutoDisponivel());
        } catch (SQLException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR,
                    "Erro ao preencher a tabela: " + ex.getMessage(),
                    ButtonType.OK);
            alerta.showAndWait();
        }
        return produtos;
    }
}
