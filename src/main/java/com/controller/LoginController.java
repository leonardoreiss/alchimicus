package com.controller;

import com.DAO.FuncionarioDAO;
import com.Model.Funcionario;
import com.Tela.Login;
import com.Tela.MenuPrincipalTela;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    public TextField txt_login;
    public Button btnSignIn;
    public PasswordField txt_password;
    private Funcionario funcionario;
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private boolean validarCampos() {
        if(txt_login.getText().length() == 0 ||
                txt_password.getText().length() == 0) {
            return false;
        } else {
            return true;
        }
    }
    private Funcionario moveDadosTelaModel() {
        funcionario = new Funcionario();
        funcionario.setUser(txt_login.getText());
        funcionario.setSenha(txt_password.getText());
        return funcionario;
    }
    private void mensagem(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo, texto, ButtonType.OK);
        alerta.showAndWait();
    }
    private void limparCampos() {
        txt_login.setText("");
        txt_password.setText("");
    }
    public void btnSignIn_Click(ActionEvent actionEvent) throws IOException {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos",
                    Alert.AlertType.INFORMATION);
            return;
        }

        funcionario = moveDadosTelaModel();

        try {

            funcionario = funcionarioDAO.buscaLogin(funcionario);

            if(funcionario != null) {
                mensagem("Logado com Sucesso",
                        Alert.AlertType.INFORMATION);
                limparCampos();
                MenuPrincipalTela menu = new MenuPrincipalTela();
                menu.start(new Stage());
            }
        } catch (SQLException ex) {
            mensagem("Erro na Consulta: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Consulta" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }
}