package com.controller;

import com.DAO.FornecedorDAO;
import com.Model.Fornecedor;
import com.Tela.EstoqueTela;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class NovoFornecedorController {
    public TextField txt_cnbruxo;
    public TextField txt_nomeForn;
    public TextField txt_codForn;

    public Button btnBack;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnInclude;
    public Button btnSearch;
    public TextField txt_telForn;
    public TextField txt_emailForn;
    public TextField txt_enderecoForn;
    private Fornecedor fornecedor;
    private FornecedorDAO fornecedorDAO = new FornecedorDAO();
    private boolean validarCampos() {
        if(txt_cnbruxo.getText().length() == 0 ||
                txt_codForn.getText().length() == 0 ||
                txt_nomeForn.getText().length() == 0) {
            return false;
        } else {
            return true;
        }
    }
    private Fornecedor moveDadosTelaModel() {
        fornecedor = new Fornecedor();
        fornecedor.setCodForn(Integer.parseInt(txt_codForn.getText()));
        fornecedor.setNomeForn(txt_nomeForn.getText());
        fornecedor.setCnBruxo(txt_cnbruxo.getText());
        fornecedor.setEnderecoForn(txt_enderecoForn.getText());
        fornecedor.setEmailForn(txt_emailForn.getText());
        fornecedor.setTelefoneForn(txt_telForn.getText());
        return fornecedor;
    }
    private void mensagem(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo, texto, ButtonType.OK);
        alerta.showAndWait();
    }
    private void limparCampos() {
        txt_telForn.setText("");
        txt_codForn.setText("");
        txt_nomeForn.setText("");
        txt_emailForn.setText("");
        txt_enderecoForn .setText("");
        txt_cnbruxo.setText("");
    }

    public void btnBack_Click(ActionEvent actionEvent) throws IOException{
        com.Tela.FornecedorTela.tela.close();
        EstoqueTela estoqueTela = new EstoqueTela();
        estoqueTela.start(new Stage());
    }
    public void btnDelete_Click(ActionEvent actionEvent) throws Exception{
        if(!validarCampos()) {
            mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mensagem ao Usuário");
        alert.setHeaderText("Aviso de Exclusão");
        alert.setContentText("Confirma a Exclusão deste Fornecedor?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.NO){
            return;
        }
        fornecedor = moveDadosTelaModel();
        try {
            if(fornecedorDAO.remove(fornecedor)) {
                mensagem("Dados Excluídos com Sucesso",
                        Alert.AlertType.INFORMATION);
                limparCampos();
                habilitaInclusao();
                txt_cnbruxo.requestFocus();
            }
            else{
                throw new Exception("Erro na Exclusão: Há dados do fornecedor associados em produtos do estoque");
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
        fornecedor = moveDadosTelaModel();
        try {
            if(fornecedorDAO.altera(fornecedor)) {
                mensagem("Dados Alterados com Sucesso",
                        Alert.AlertType.INFORMATION);
                limparCampos();
                habilitaInclusao();
                txt_cnbruxo.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Alteração: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Alteração" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }
    public void btnInclude_Click(ActionEvent actionEvent) {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos",
                    Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }

        fornecedor = moveDadosTelaModel();

        //vamos inserir
        try {

            if(fornecedorDAO.insere(fornecedor)) {
                mensagem("Fornecedor incluídos com sucesso",
                        Alert.AlertType.INFORMATION);
                limparCampos();
                txt_codForn.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Inclusão: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Inclusão" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }
    public void btnSearch_Click(ActionEvent actionEvent) {
        fornecedor = new Fornecedor();
        //quem será pesquisado
        fornecedor.setCodForn(Integer.parseInt(txt_codForn.getText()));
        try {
            fornecedor = fornecedorDAO.buscaID(fornecedor);
            //se não achou
            if(fornecedor == null) {
                mensagem("Fornecedor Não Existe!!!",
                        Alert.AlertType.INFORMATION);
            }
            else {
                moveDadosModelTela(fornecedor);
                habilitaAlteracaoExclusao();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Pesquisa: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }
    private void moveDadosModelTela(Fornecedor f) {
        txt_codForn.setText(Integer.toString(f.getCodForn()));
        txt_nomeForn.setText(f.getNomeForn());
        txt_cnbruxo.setText(f.getCnBruxo());
        txt_enderecoForn.setText(f.getEnderecoForn());
        txt_emailForn.setText(f.getEmailForn());
        txt_telForn.setText(f.getTelefoneForn());
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
