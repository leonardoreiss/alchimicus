package com.Tela;

import com.controller.App;
import com.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class Login extends Application {
    public static Stage tela;

    @Override
    public void start(Stage stage) throws Exception {
        setStage(tela);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login.fxml"));
        Image icon = new Image(getClass().getResource("/images/logo.png").toExternalForm());
        tela.getIcons().add(icon);
        Parent root = fxmlLoader.load();
        LoginController controler = fxmlLoader.getController();
        Scene scene = new Scene(root, 376, 351);
        tela.setScene(scene);
        tela.show();
    }

    public static void setStage(Stage t) {
        tela = t;
    }


    public boolean validarLogin(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/alchimicus";
        String user = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
            String sql = "SELECT * FROM login WHERE user = ? AND senha = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

    }

}


