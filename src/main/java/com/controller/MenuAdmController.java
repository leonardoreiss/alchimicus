package com.controller;

import com.Tela.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

import static com.Tela.Adm.tela;

public class MenuAdmController {
    public void btnEstoque_Click(ActionEvent actionEvent) throws IOException{
        tela.close();
        EstoqueTela estoqueTela = new EstoqueTela();
        estoqueTela.start(new Stage());
    }
    public void btnClientes_Click(ActionEvent actionEvent) throws IOException{
        tela.close();
        HistoricoClienteTela historicoClienteTela = new HistoricoClienteTela();
        historicoClienteTela.start(new Stage());
    }
    public void btnHistoricoVendas_Click(ActionEvent actionEvent) throws IOException {
        tela.close();
        HistoricoTela historicoTela = new HistoricoTela();
        historicoTela.start(new Stage());
    }
    @FXML
    public void btnLogout_Click(ActionEvent actionEvent) throws Exception {
        tela.close();
    }
}
