package com.controller;

import com.DAO.CategoriaDAO;
import com.DAO.FornecedorDAO;
import com.DAO.ProdutoDAO;
import com.Model.Categoria;
import com.Model.Fornecedor;
import com.Model.Produto;
import com.Tela.EstoqueTela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.Tela.ProdutoTela.tela;

public class NovoProdutoController implements Initializable {
    @FXML
    public TextField txt_nome;
    @FXML
    public TextField txt_code;
    @FXML
    public TextField txt_price;
    @FXML
    public TextArea txtDescription;
    @FXML
    public Button btnBack;
    @FXML
    public TextField txtQtd;
    @FXML
    public ComboBox<Categoria> cbCategoria;
    public Button btnDelete;
    public Button btnInclude;
    public Button btnUpdate;
    public ComboBox<Fornecedor> cbFornecedor;
    public Button btnSearch;
    public TextField txtFoto;
    public Button btnSelecionaFoto;
    private Produto produto;
    private ProdutoDAO daoProd = new ProdutoDAO();
    private FornecedorDAO daoForn = new FornecedorDAO();
    private CategoriaDAO daoCat = new CategoriaDAO();
    private ObservableList<Categoria> categorias = FXCollections.observableArrayList();
    private ObservableList<Fornecedor> fornecedores = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        preencheCombo();
        cbCategoria.setItems(categorias);
        cbFornecedor.setItems(fornecedores);
        habilitaInclusao();
    }

    private Produto moveDadosTelaModel() {
        produto = new Produto();
        File f;
        produto.setCodProd(Integer.parseInt(txt_code.getText()));
        produto.setNomeProd(txt_nome.getText());
        produto.setPrecoProd(Float.parseFloat(txt_price.getText()));
        produto.setQtdEstoqueProd(Integer.parseInt(txtQtd.getText()));
        produto.setDescricao(txtDescription.getText());
        produto.setCategoria(cbCategoria.getValue());
        produto.setFornecedor(cbFornecedor.getValue());
        f = new File(txtFoto.getText());
        produto.setFoto(f.getName());
        return produto;
    }

    private void moveDadosModelTela(Produto p) {
        txt_code.setText((Integer.toString(produto.getCodProd())));
        txt_nome.setText(p.getNomeProd());
        txt_price.setText((Float.toString(produto.getPrecoProd())));
        txtQtd.setText((Integer.toString(produto.getQtdEstoqueProd())));
        cbCategoria.setValue(p.getCategoria());
        cbFornecedor.setValue(p.getFornecedor());
        txtDescription.setText(p.getDescricao());
        txtFoto.setText(p.getFoto());
    }

    private void mensagem(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo, texto, ButtonType.OK);
        alerta.showAndWait();
    }

    private void preencheCombo() {
        try {
            categorias.addAll(daoCat.lista(""));
            fornecedores.addAll(daoForn.lista(""));
        } catch (SQLException ex) {
            mensagem("Erro no preenchimento da Combo: " +
                    ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void btnSearch_click(ActionEvent actionEvent) {

        produto = new Produto();
        produto.setCodProd(Integer.parseInt(txt_code.getText()));
        try {
            produto = daoProd.buscaID(produto);
            if (produto == null) {
                mensagem("Produto Não Existe!!!",
                        Alert.AlertType.INFORMATION);

                limparCampos();
                habilitaInclusao();
            } else {
                moveDadosModelTela(produto);
                habilitaAlteracaoExclusao();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Pesquisa: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }

    public void btnInclude_click(ActionEvent actionEvent) {
        if (!validarCampos()) {
            mensagem("Preencher todos os campos",
                    Alert.AlertType.INFORMATION);
            return;
        }
        produto = moveDadosTelaModel();
        try {
            if (daoProd.insere(produto)) {
                File f;
                f = new File(txtFoto.getText());
                String nomeArquivo = f.getName();
                String user = System.getProperty("user.dir");
                String caminhoOrigem = f.getAbsolutePath();
                String caminhoDestino = user + "\\src\\main\\resources\\img\\" + nomeArquivo;
                try {
                    Path origem = Path.of(caminhoOrigem);
                    Path destino = Path.of(caminhoDestino);

                    Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

                } catch (IOException e) {
                    System.out.println("Erro ao copiar o arquivo: " + e.getMessage());
                }

                mensagem("Dados Incluídos com Sucesso",
                        Alert.AlertType.INFORMATION);
                limparCampos();
                txt_code.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Inclusão: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Inclusão" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }

    public void btnUpdate_Click(ActionEvent actionEvent) {
        if (!validarCampos()) {
            mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
            return;
        }

        produto = moveDadosTelaModel();

        try {
            if (daoProd.altera(produto)) {
                File f;
                f = new File(txtFoto.getText());
                String nomeArquivo = f.getName();
                String user = System.getProperty("user.dir");
                String caminhoOrigem = f.getAbsolutePath();
                String caminhoDestino = user + "\\src\\main\\resources\\img\\" + nomeArquivo;
                try {
                    Path origem = Path.of(caminhoOrigem);
                    Path destino = Path.of(caminhoDestino);

                    Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.out.println("Erro ao copiar o arquivo: " + e.getMessage());
                }
                mensagem("Dados Alterados com Sucesso",
                        Alert.AlertType.INFORMATION);
                limparCampos();
                habilitaInclusao();
                txt_code.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Alteração: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Alteração" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }

    public void btnDelete_Click(ActionEvent actionEvent) {
        if (!validarCampos()) {
            mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mensagem ao Usuário");
        alert.setHeaderText("Aviso de Exclusão");
        alert.setContentText("Confirma a Exclusão deste Produto?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.NO) {
            return;
        }

        //recebe todos os dados da tela
        produto = moveDadosTelaModel();
        //vamos Excluir
        try {
            if (daoProd.remove(produto)) {
                mensagem("Dados Excluídos com Sucesso",
                        Alert.AlertType.INFORMATION);
                limparCampos();
                habilitaInclusao();
                txt_code.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Exclusão: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Exclusão" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }

    }

    private boolean validarCampos() {
        if (txt_nome.getText().length() == 0 ||
                txtQtd.getText().length() == 0 ||
                txt_price.getText().length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    private void limparCampos() {
        txt_code.setText("");
        txt_nome.setText("");
        txt_price.setText("");
        txtQtd.setText("");
        cbFornecedor.getSelectionModel().clearSelection();
        txtDescription.setText("");
        cbCategoria.getSelectionModel().clearSelection();
        txtFoto.setText("");
    }

    private void habilitaInclusao() {
        btnInclude.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void habilitaAlteracaoExclusao() {
        btnInclude.setDisable(true);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
    }

    public void btnBack_Click(ActionEvent actionEvent) throws IOException {
        tela.close();
        EstoqueTela estoqueTela = new EstoqueTela();
        estoqueTela.start(new Stage());
    }

    public void btnSelecionaFoto_Click(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        File f;
        f = chooser.showOpenDialog(null);
        txtFoto.setText(f.getAbsolutePath());
    }
}
