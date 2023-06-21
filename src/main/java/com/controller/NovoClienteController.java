package com.controller;

import com.DAO.ClienteDAO;
import com.Model.Cliente;
import com.Tela.Adm;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static com.Tela.ClienteTela.tela;

public class NovoClienteController {
    public TextField txt_CodBru;
    public TextField txt_Nome;
    public TextField txt_dateBirth;
    public TextField txt_email;

    public Button btnBack;
    public Button btnInclude;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnSearch;
    public TextField txt_endereco;

    private Cliente cliente;
    private ClienteDAO clienteDAO = new ClienteDAO();
    private boolean validarCampos() {
        if( txt_Nome.getText().length() == 0 ||
                txt_dateBirth.getText().length() == 0 ||
                txt_endereco.getText().length() == 0) {
            return false;
        } else {
            return true;
        }
    }
    private Cliente moveDadosTelaModel() {
        cliente = new Cliente();
        cliente.setCodBruxo(Integer.parseInt(Integer.toString(cliente.getCodBruxo())));
        cliente.setNomeCliente(txt_Nome.getText());
        cliente.setNascCliente(txt_dateBirth.getText());
        cliente.setEnderecoCliente(txt_endereco.getText());
        cliente.setEmailCliente(txt_email.getText());
        return cliente;
    }
    private void mensagem(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo, texto, ButtonType.OK);
        alerta.showAndWait();
    }
    private void limparCampos(){
        txt_CodBru.setText("");
        txt_Nome.setText("");
        txt_dateBirth.setText("");
        txt_endereco.setText("");
        txt_email.setText("");
    }

    public void btnBack_Click(ActionEvent actionEvent) throws IOException {
        tela.close();
    }

    public void btnInclude_Click(ActionEvent actionEvent) {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos",
                    Alert.AlertType.INFORMATION);
            return;
        }

        cliente = moveDadosTelaModel();

        try {
            if(clienteDAO.insere(cliente)) {
                mensagem("Dados Incluídos com Sucesso",
                        Alert.AlertType.INFORMATION);
                limparCampos();
                txt_CodBru.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Inclusão: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Inclusão" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }

    public void btnDelete_Click(ActionEvent actionEvent) {

        if(!validarCampos()) {
            mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mensagem ao Usuário");
        alert.setHeaderText("Aviso de Exclusão");
        alert.setContentText("Confirma a Exclusão deste Cliente?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.NO){
            return;
        }
        cliente = moveDadosTelaModel();
        try {
            if(clienteDAO.remove(cliente)) {
                mensagem("Dados Excluídos com Sucesso",
                        Alert.AlertType.INFORMATION);
                limparCampos();
                habilitaInclusao();
                txt_CodBru.requestFocus();
            }else{
                throw new Exception("Erro na Exclusão: Há dados do cliente associados em uma venda.");
            }
        } catch (SQLException ex) {
            mensagem("Erro na Exclusão: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Exclusão" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }

    }

    public void btnUpdate_Click(ActionEvent actionEvent) {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
            return;
        }

        cliente = moveDadosTelaModel();

        try {
            if(clienteDAO.altera(cliente)) {
                mensagem("Dados Alterados com Sucesso",
                        Alert.AlertType.INFORMATION);
                limparCampos();
                habilitaInclusao();
                txt_CodBru.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Alteração: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Alteração" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }

    public void btnSearch_Click(ActionEvent actionEvent) {
        cliente = new Cliente();
        cliente.setCodBruxo(Integer.parseInt(txt_CodBru.getText()));
        try {
            cliente = clienteDAO.buscaID(cliente);
            if(cliente == null) {
                mensagem("Cliente Não Existe!!!",
                        Alert.AlertType.INFORMATION);
            }
            else {
                moveDadosModelTela(cliente);
                habilitaAlteracaoExclusao();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Pesquisa: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }

    private void moveDadosModelTela(Cliente cli) {
        txt_CodBru.setText(Integer.toString(cli.getCodBruxo()));
        txt_Nome.setText(cli.getNomeCliente());
        txt_dateBirth.setText(cli.getNascCliente());
        txt_endereco.setText(cli.getEnderecoCliente());
        txt_email.setText(cli.getEmailCliente());
    }

    private void habilitaAlteracaoExclusao() {
        btnInclude.setDisable(true);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
    }
    private void habilitaInclusao() {
        btnInclude.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }
}
