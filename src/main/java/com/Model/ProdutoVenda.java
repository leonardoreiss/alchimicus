package com.Model;

import java.util.Objects;

public class ProdutoVenda {

    private int condVend, codProd, qtdProduto;
    private String nomeProd;

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    @Override
    public String toString() {
        return nomeProd;
    }

    public ProdutoVenda() {
    }

    public ProdutoVenda(int condVend, int codProd, int qtdProduto) {
        this.condVend = condVend;
        this.codProd = codProd;
        this.qtdProduto = qtdProduto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoVenda that)) return false;
        return getCondVend() == that.getCondVend();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCondVend());
    }

    public int getCondVend() {
        return condVend;
    }

    public void setCondVend(int condVend) {
        this.condVend = condVend;
    }

    public int getCodProd() {
        return codProd;
    }

    public void setCodProd(int codProd) {
        this.codProd = codProd;
    }

    public int getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }
}
