package com.controller;

import com.DAO.VendaDAO;
import com.Model.Venda;
import com.Tela.Adm;
import com.Tela.HistoricoClienteTela;
import javafx.beans.property.IntegerProperty;
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

public class HistoricoVendasController implements Initializable {
    public Button btnBack;
    public TextField txt_pesquisa;

    @FXML
    private TableView<Venda> tabela;
    @FXML
    private TableColumn<Venda, Integer> clCliente;
    @FXML
    private TableColumn<Venda, Integer> clFuncionario;
    @FXML
    private TableColumn<Venda, Integer> clVenda;
    @FXML
    private TableColumn<Venda, String> clHorario;
    @FXML
    private TableColumn<Venda, Integer> clTotal;

    public void btnBack_Click(ActionEvent event) throws IOException {
        HistoricoClienteTela.tela.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clCliente.setCellValueFactory(new PropertyValueFactory<>("codBruxo"));
        clFuncionario.setCellValueFactory(new PropertyValueFactory<>("codFunc"));
        clVenda.setCellValueFactory(new PropertyValueFactory<>("codVend"));
        clHorario.setCellValueFactory(new PropertyValueFactory<>("data"));
        clTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        tabela.setItems(preencheTabela(""));

        txt_pesquisa.setText(""); // Define um valor padrão (vazio) no campo de pesquisa

        txt_pesquisa.setOnAction(event -> btnPesquisa_Click(null)); // Listener para o evento de pressionar Enter
    }

    public ObservableList<Venda> preencheTabela(String clientePesquisa) {
        VendaDAO dao = new VendaDAO();
        ObservableList<Venda> vendas = FXCollections.observableArrayList();
        try {
            vendas.addAll(dao.lista(clientePesquisa));
        } catch (SQLException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR,
                    "Erro Preenche Tabela: " + ex.getMessage(),
                    ButtonType.OK);
            alerta.showAndWait();
        }
        return vendas;
    }

    public void btnPesquisa_Click(ActionEvent actionEvent) {
        String clientePesquisa = txt_pesquisa.getText();
        ObservableList<Venda> vendasFiltradas = preencheTabela(clientePesquisa);

        tabela.setItems(vendasFiltradas);
    }
}
