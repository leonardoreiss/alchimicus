package com.controller;

import com.DAO.ClienteDAO;
import com.Model.Cliente;
import com.Tela.Adm;
import com.Tela.ClienteTela;
import com.Tela.HistoricoClienteTela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientesController implements Initializable {

    public Button btnBack;
    public Button btnNovoCliente;
    @FXML
    public TableView<Cliente> tabela;
    @FXML
    public TableColumn<Cliente, Integer> clCodBruxo;
    @FXML
    public TableColumn<Cliente, String> clNome;
    @FXML
    public TableColumn<Cliente, String> clNascimento;
    @FXML
    public TableColumn<Cliente, String> clEndereco;
    @FXML
    public TableColumn<Cliente, String> clEmail;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clCodBruxo.setCellValueFactory(new PropertyValueFactory<>("codBruxo"));
        clNome.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        clNascimento.setCellValueFactory(new PropertyValueFactory<>("nascCliente"));
        clEndereco.setCellValueFactory(new PropertyValueFactory<>("enderecoCliente"));
        clEmail.setCellValueFactory(new PropertyValueFactory<>("emailCliente"));
        tabela.setItems(preencheTabela());
    }

    public ObservableList<Cliente> preencheTabela() {
        ClienteDAO dao = new ClienteDAO();
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        try {
            clientes.addAll(dao.lista(""));
        }catch (SQLException ex){
            Alert alerta = new Alert(Alert.AlertType.ERROR,
                    "Erro Preenche Tabela: " + ex.getMessage(),
                    ButtonType.OK);
            alerta.showAndWait();
        }
        return clientes;
    }

    public void btnBack_Click(ActionEvent actionEvent) throws IOException {
        HistoricoClienteTela.tela.close();
    }

    public void btnNocoCliente_Click(ActionEvent actionEvent) throws IOException{
        ClienteTela clienteTela = new ClienteTela();
        clienteTela.start(new Stage());
    }
}
