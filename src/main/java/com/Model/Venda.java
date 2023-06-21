package com.Model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class Venda {
    private int codBruxo;
    private int codFunc;
    private int codVend;
    private String data;
    private float valorTotal;
    Stack<ProdutoVenda> produtoVendas = new Stack<>();


    public Stack<ProdutoVenda> getProdutoVendas() {
        return produtoVendas;
    }

    public void setProdutoVendas(Stack<ProdutoVenda> produtoVendas) {
        this.produtoVendas = produtoVendas;
    }

    public Venda(){};

    public Venda(int codBruxo, int codFunc, int codVend, String data, float valorTotal, Stack<ProdutoVenda> produtoVendas) {
        this.codBruxo = codBruxo;
        this.codFunc = codFunc;
        this.codVend = codVend;
        this.data = data;
        this.valorTotal = valorTotal;
        this.produtoVendas = produtoVendas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venda venda)) return false;
        return getCodVend() == venda.getCodVend();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodVend());
    }

    //getters e setters
    public int getCodBruxo() {
        return codBruxo;
    }

    public void setCodBruxo(int codBruxo) {
        this.codBruxo = codBruxo;
    }

    public int getCodFunc() {
        return codFunc;
    }

    public void setCodFunc(int codFunc) {
        this.codFunc = codFunc;
    }

    public int getCodVend() {
        return codVend;
    }

    public void setCodVend(int codVend) {
        this.codVend = codVend;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }
}
