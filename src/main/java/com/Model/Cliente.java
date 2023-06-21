package com.Model;

public class Cliente {
    private int codBruxo;
    private String nomeCliente, nascCliente, enderecoCliente, emailCliente;

    public Cliente() {
    }

    public Cliente(int codBruxo, String nomeCliente, String nascCliente, String enderecoCliente, String emailCliente) {
        this.codBruxo = codBruxo;
        this.nomeCliente = nomeCliente;
        this.nascCliente = nascCliente;
        this.enderecoCliente = enderecoCliente;
        this.emailCliente = emailCliente;
    }

    @Override
    public String toString() {
        return getNomeCliente();
    }

    public int getCodBruxo() {
        return codBruxo;
    }

    public void setCodBruxo(int codBruxo) {
        this.codBruxo = codBruxo;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNascCliente() {
        return nascCliente;
    }

    public void setNascCliente(String nascCliente) {
        this.nascCliente = nascCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        return this.codBruxo == other.codBruxo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.codBruxo;
        return hash;
    }

}
