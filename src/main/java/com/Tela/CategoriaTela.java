package com.Tela;

import com.controller.App;
import com.controller.NovaCategoriaController;
import com.controller.NovoClienteController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class CategoriaTela extends Application {
    public static Stage tela;

    @Override
    public void start(Stage tela) throws IOException {
        setStage(tela);
        Image icon = new Image(getClass().getResource("/images/logo.png").toExternalForm());
        tela.getIcons().add(icon);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("novaCategoria.fxml"));
        Parent root = fxmlLoader.load();
        NovaCategoriaController controler = fxmlLoader.getController();
        Scene scene = new Scene(root, 529, 289);
        tela.setScene(scene);
        tela.show();

    }

    public static void setStage(Stage t) {
        tela = t;
    }
}
