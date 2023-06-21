package com.Model;

import java.util.Objects;

public class Fornecedor {
    private int codForn;
    private String cnBruxo, nomeForn, emailForn, enderecoForn, telefoneForn;
    public Fornecedor() {
    }

    public Fornecedor(int codForn, String cnBruxo, String nomeForn, String emailForn, String enderecoForn, String telefoneForn) {
        this.codForn = codForn;
        this.cnBruxo = cnBruxo;
        this.nomeForn = nomeForn;
        this.emailForn = emailForn;
        this.enderecoForn = enderecoForn;
        this.telefoneForn = telefoneForn;
    }

    public String getEmailForn() {
        return emailForn;
    }

    public void setEmailForn(String emailForn) {
        this.emailForn = emailForn;
    }

    public String getEnderecoForn() {
        return enderecoForn;
    }

    public void setEnderecoForn(String enderecoForn) {
        this.enderecoForn = enderecoForn;
    }

    public String getTelefoneForn() {
        return telefoneForn;
    }

    public void setTelefoneForn(String telefoneForn) {
        this.telefoneForn = telefoneForn;
    }

    @Override
    public String toString() {
        return getNomeForn();
    }

    public int getCodForn() {
        return codForn;
    }

    public void setCodForn(int codForn) {
        this.codForn = codForn;
    }

    public String getCnBruxo() {
        return cnBruxo;
    }

    public void setCnBruxo(String cnBruxo) {
        this.cnBruxo = cnBruxo;
    }

    public String getNomeForn() {
        return nomeForn;
    }

    public void setNomeForn(String nomeForn) {
        this.nomeForn = nomeForn;
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
        final Fornecedor other = (Fornecedor) obj;
        return this.codForn == other.codForn;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.codForn;
        return hash;
    }
}
