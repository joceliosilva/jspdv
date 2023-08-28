/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jscomp.jspdv;

import java.util.EventObject;

public class ProdutoSelecionadoEvent extends EventObject {
     private final Produto produtoSelecionado;

    public ProdutoSelecionadoEvent(Object source, Produto produtoSelecionado) {
        super(source);
        this.produtoSelecionado = produtoSelecionado;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }
}
