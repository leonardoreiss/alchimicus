package com.Model;

import javafx.stage.Stage;

import java.util.Objects;

public class Produto {
    private String nomeProd, descricao, foto;
    private float precoProd;

    private Categoria categoria;
    private Fornecedor fornecedor;

    private int codProd, qtdEstoqueProd;
    public static Stage tela;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }



    public Produto() {
    }

    public Produto(String nomeProd, String descricao, String foto, float precoProd, Categoria categoria, Fornecedor fornecedor, int codCat, int codForn, int codProd, int qtdEstoqueProd) {
        this.nomeProd = nomeProd;
        this.descricao = descricao;
        this.foto = foto;
        this.precoProd = precoProd;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
        this.codProd = codProd;
        this.qtdEstoqueProd = qtdEstoqueProd;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public int getQtdEstoqueProd() {
        return qtdEstoqueProd;
    }

    public void setQtdEstoqueProd(int qtdEstoqueProd) {
        this.qtdEstoqueProd = qtdEstoqueProd;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public float getPrecoProd() {
        return precoProd;
    }

    public void setPrecoProd(float precoProd) {
        this.precoProd = precoProd;
    }

    public int getCodProd() {
        return codProd;
    }

    public void setCodProd(int codProd) {
        this.codProd = codProd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto produto)) return false;
        return getCodProd() == produto.getCodProd();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodProd());
    }


    @Override
    public String toString() {
        return getNomeProd();
    }

}
