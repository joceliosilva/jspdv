/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jscomp.jspdv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.prefs.Preferences;

public class DadosVendas {

    public static String recuperarDadosDeVendas() {
        String dados = "";

        // Configurações de conexão com o banco de dados
         Preferences prefs = Preferences.userNodeForPackage(ConfiguracaoBancoDados.class);
        String urlBanco = prefs.get("urlBanco", "");
        String usuario = prefs.get("usuario", "");
        String senha = prefs.get("senha", "");

        try (Connection conexao = DriverManager.getConnection(urlBanco, usuario, senha);
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM historico_vendas")) {

            // Processar os resultados da consulta
            while (rs.next()) {
                int id = rs.getInt("id");
                String dataVenda = rs.getString("data_venda");
                // Recupere outros campos conforme necessário

                // Adicione os dados à string de exibição
                dados += "ID: " + id + ", Data: " + dataVenda + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            dados = "Erro ao recuperar dados de vendas.";
        }

        return dados;
    }

    public static void main(String[] args) {
        String dados = recuperarDadosDeVendas();
        System.out.println(dados);
    }
}
