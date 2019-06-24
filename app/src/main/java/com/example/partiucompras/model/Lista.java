package com.example.partiucompras.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Lista implements Serializable {

    private Long id;
    private String nome;
    private Boolean situacao;
    private Date encerramento;
    private ArrayList<Produto> listaProdutos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(Boolean situacao) {
        this.situacao = situacao;
    }

    public Date getEncerramento() {
        return encerramento;
    }

    public void setEncerramento(Date encerramento) {
        this.encerramento = encerramento;
    }

    public ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(ArrayList<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    @Override
    public String toString() {
        return nome;
    }
}
