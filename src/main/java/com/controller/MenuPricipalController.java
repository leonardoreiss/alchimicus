package com.controller;

import com.Tela.Adm;
import com.Tela.Login;
import com.Tela.LojaTela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPricipalController {
    public Button btn_logout;
    public Button btn_abrirLoja;
    public Button btn_ADM;
    public Label administratione;
    public Label alchimicus_logo;

    public void btnAbrirLoja_Click(ActionEvent actionEvent) throws IOException {
        LojaTela lojaTela = new LojaTela();
        lojaTela.start(new Stage());
    }
    public void btnADM_Click(ActionEvent actionEvent) throws Exception{
        Adm adm = new Adm();
        adm.start(new Stage());
    }
}
