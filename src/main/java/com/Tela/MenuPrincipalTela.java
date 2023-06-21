package com.Tela;

import com.Model.Funcionario;
import com.controller.App;
import com.controller.MenuPricipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class MenuPrincipalTela extends Application {
    public static Stage tela;

    @Override
    public void start(Stage tela) throws IOException {
        setStage(tela);
        Image icon = new Image(getClass().getResource("/images/logo.png").toExternalForm());
        tela.getIcons().add(icon);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menuPrincipal.fxml"));
        Parent root = fxmlLoader.load();
        MenuPricipalController controler = fxmlLoader.getController();
        Scene scene = new Scene(root, 600, 400);
        tela.setScene(scene);
        tela.show();
    }

    public static void setStage(Stage t) {
        tela = t;
    }

    private void mensagem(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo, texto, ButtonType.OK);
        alerta.showAndWait();
    }
    public void info_func(Funcionario dados){
        mensagem(dados.getNomeFunc(), Alert.AlertType.INFORMATION);
    }
}
