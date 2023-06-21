package com.Model;



public class Categoria {
    private int codCat;
    private String nomeCat, descricaoCat;

    public Categoria(int codCat, String nomeCat, String descricaoCat) {
        this.codCat = codCat;
        this.nomeCat = nomeCat;
        this.descricaoCat = descricaoCat;
    }

    public String getDescricaoCat() {
        return descricaoCat;
    }

    public void setDescricaoCat(String descricaoCat) {
        this.descricaoCat = descricaoCat;
    }

    @Override
    public String toString() {
        return getNomeCat();
    }

    public Categoria(){}


    public int getCodCat() {
        return codCat;
    }

    public void setCodCat(int codCat) {
        this.codCat = codCat;
    }

    public String getNomeCat() {
        return nomeCat;
    }

    public void setNomeCat(String nomeCat) {
        this.nomeCat = nomeCat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.codCat;
        return hash;
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
        final Categoria other = (Categoria) obj;
        return this.codCat == other.codCat;
    }
}
