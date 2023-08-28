package com.jscomp.jspdv;

    public class ItemVenda {
    private String nome;
    private double preco;

    public ItemVenda(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}
