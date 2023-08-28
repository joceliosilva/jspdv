/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jscomp.jspdv;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
public class TelaFlash extends JFrame {

    private JTextArea flashTextArea;

    public TelaFlash() {
        initComponents();
      String dados = DadosVendas.recuperarDadosDeVendas();
    exibirDadosDeVendas(dados);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela de Flash");

        flashTextArea = new JTextArea();
        flashTextArea.setEditable(false);
        flashTextArea.setRows(10);
        flashTextArea.setColumns(40);

        JPanel panel = new JPanel();
        panel.add(flashTextArea);

        getContentPane().add(panel);
        pack();
    }

    public void exibirDadosDeVendas(String dados) {
        flashTextArea.setText(dados);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            TelaFlash telaFlash = new TelaFlash();
            telaFlash.setVisible(true);
            // Aqui você pode chamar o método para exibir os dados de vendas
            telaFlash.exibirDadosDeVendas("Dados de vendas aqui...");
        });
    }
}
