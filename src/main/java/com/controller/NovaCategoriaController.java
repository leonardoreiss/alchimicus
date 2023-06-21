package com.controller;

import com.DAO.CategoriaDAO;
import com.Model.Categoria;
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

import static com.Tela.CategoriaTela.tela;

public class NovaCategoriaController {
    public TextField txt_nomeCat;
    public TextField txt_codCat;

    public Button btnBack;
    public Button btnInclude;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnSearch;
    public TextField txtDescricaoCat;
    private Categoria categoria;
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private boolean validarCampos() {
        if(txt_codCat.getText().length() == 0 ||
                txt_nomeCat.getText().length() == 0 ||
                txtDescricaoCat.getText().length()==0) {
            return false;
        } else {
            return true;
        }
    }
    private Categoria moveDadosTelaModel() {
        categoria = new Categoria();
        categoria.setCodCat(Integer.parseInt(txt_codCat.getText()));
        categoria.setNomeCat(txt_nomeCat.getText());
        categoria.setDescricaoCat(txtDescricaoCat.getText());
        return categoria;
    }
    private void mensagem(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo, texto, ButtonType.OK);
        alerta.showAndWait();
    }
    private void limparCampos() {
        txt_codCat.setText("");
        txt_nomeCat.setText("");
        txtDescricaoCat.setText("");
    }

    public void btnBack_Click(ActionEvent actionEvent) throws IOException {
        tela.close();
        EstoqueTela estoqueTela = new EstoqueTela();
        estoqueTela.start(new Stage());
    }
    public void btnInclude_Click(ActionEvent actionEvent) {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos",
                    Alert.AlertType.INFORMATION);
            return;
        }

        categoria = moveDadosTelaModel();

        try {

            if(categoriaDAO.insere(categoria)) {
                mensagem("Dados Incluídos com Sucesso",
                        Alert.AlertType.INFORMATION);
                limparCampos();
                txt_codCat.requestFocus();
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
        alert.setContentText("Confirma a Exclusão desta Categoria");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.NO){
            return;
        }
        categoria = moveDadosTelaModel();
        try {
            if(categoriaDAO.remove(categoria)) {
                mensagem("Dados Excluídos com Sucesso",
                        Alert.AlertType.INFORMATION);
                limparCampos();
                habilitaInclusao();
                txt_codCat.requestFocus();
            }
            else{
                throw new Exception("Erro na Exclusão: Há dados da categoria associados em produtos do estoque");
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
        categoria = moveDadosTelaModel();

        try {
           if(categoriaDAO.altera(categoria)) {
               mensagem("Dados Alterados com Sucesso",
                       Alert.AlertType.INFORMATION);
               limparCampos();
               habilitaInclusao();
               txt_codCat.requestFocus();
           }
        } catch (SQLException ex) {
            mensagem("Erro na Alteração: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Alteração" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }
    private void moveDadosModelTela(Categoria c) {
        txt_nomeCat.setText(c.getNomeCat());
        txt_codCat.setText(Integer.toString(c.getCodCat()));
        txtDescricaoCat.setText(c.getDescricaoCat());
    }
    public void btnSearch_Click(ActionEvent actionEvent){
        categoria = new Categoria();
        categoria.setCodCat(Integer.parseInt(txt_codCat.getText()));

        try {
            categoria = categoriaDAO.buscaID(categoria);
            if(categoria == null) {
                mensagem("Categoria Não Existe!!!",
                        Alert.AlertType.INFORMATION);
                habilitaInclusao();
            }
            else { //achou
                moveDadosModelTela(categoria);
                habilitaExclusaoAlteracao();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Pesquisa: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }
    private void habilitaInclusao() {
        btnInclude.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void habilitaExclusaoAlteracao() {
        btnInclude.setDisable(true);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
    }
}
