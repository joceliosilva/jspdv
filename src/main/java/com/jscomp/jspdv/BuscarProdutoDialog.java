/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jscomp.jspdv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BuscarProdutoDialog extends JDialog {
    private JTextField campoPesquisa;
    private JList resultadoPesquisa;
    private TelaVendaPdv telaVendaPdv;
    private static final String URL = "jdbc:mysql://localhost:3306/pdv";
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    private List<Produto> resultados;

    BuscarProdutoDialog() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private List<Produto> pesquisarProdutos(String termoPesquisa) {
    List<Produto> resultados = new ArrayList<>();
    resultadoPesquisa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        

       

        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String sql = "SELECT * FROM produtos WHERE nome LIKE ?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, "%" + termoPesquisa + "%");
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String nome = rs.getString("nome");
                        double preco = rs.getDouble("preco");

                        Produto produto = new Produto(id, nome, preco);
                        resultados.add(produto);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultados;
    }
     
     private ProdutoSelecionadoListener produtoSelecionadoListener;
    
     
    private void selecionarProduto() {
        String termoPesquisa = campoPesquisa.getText();
    resultados = pesquisarProdutos(termoPesquisa);

    // Exibir os resultados na lista
    DefaultListModel<String> listModel = new DefaultListModel<>();
    for (Produto produto : resultados) {
        listModel.addElement(produto.getNome() + " - R$" + produto.getPreco());
    }
    resultadoPesquisa.setModel(listModel);
}

  private void adicionarListenerClique() {
    resultadoPesquisa.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int indexSelecionado = resultadoPesquisa.getSelectedIndex();

            // Verifique se o índice selecionado é válido
            if (indexSelecionado >= 0 && indexSelecionado < resultados.size()) {
                Produto produtoSelecionado = resultados.get(indexSelecionado);
                notificarProdutoSelecionado(produtoSelecionado);
                dispose(); // Fechar a janela após a seleção
            }
        }
    });
}

    private void produtoSelecionado() {
        String termoPesquisa = campoPesquisa.getText();
        List<Produto> resultados = pesquisarProdutos(termoPesquisa);

        if (resultados.size() == 1) {
            // Se houver apenas um resultado, selecione-o automaticamente
            Produto produtoSelecionado = resultados.get(0);
            if (produtoSelecionadoListener != null) {
                produtoSelecionadoListener.produtoSelecionado(produtoSelecionado);
            }
            dispose(); // Fechar a janela após a seleção
        }
    }
     
    private void notificarProdutoSelecionado(Produto produto) {
    if (produtoSelecionadoListener != null) {
        produtoSelecionadoListener.produtoSelecionado(produto);
    }
}
    
    
    public BuscarProdutoDialog(JFrame parent, TelaVendaPdv telaVendaPdv) {
    super(parent, "Buscar Produto", true);
    this.telaVendaPdv = telaVendaPdv;
    campoPesquisa = new JTextField(20);
    resultadoPesquisa = new JList(); // Altere JTextArea para JList
    resultadoPesquisa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Certifique-se de que apenas um item seja selecionável
    resultadoPesquisa.setVisibleRowCount(10); // Defina o número de itens visíveis
     resultadoPesquisa.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                int selectedIndex = resultadoPesquisa.getSelectedIndex();
                if (selectedIndex != -1) {
                    Produto produtoSelecionado = resultados.get(selectedIndex);
                    
                    TelaVendaPdv tela = new TelaVendaPdv();
                    // Chame o método na TelaVendaPdv para adicionar ao carrinho
                   telaVendaPdv.produtoSelecionado(produtoSelecionado);

                    // Feche a janela da BuscarProdutoDialog
                    dispose();
                }
            }
        }
    });

    JButton btnPesquisar = new JButton("Pesquisar");
    btnPesquisar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            selecionarProduto();
        }
    });

    JPanel painel = new JPanel();
    painel.setLayout(new BorderLayout());
    painel.add(new JLabel("Digite o nome ou descrição do produto:"), BorderLayout.NORTH);
    painel.add(campoPesquisa, BorderLayout.CENTER);
    painel.add(btnPesquisar, BorderLayout.EAST);

    getContentPane().add(painel, BorderLayout.NORTH);
    getContentPane().add(new JScrollPane(resultadoPesquisa), BorderLayout.CENTER);

    pack();
    setLocationRelativeTo(parent);
}

    // Método para exibir a caixa de diálogo de pesquisa
    public void exibirDialog() {
        setVisible(true);
    }

public void setProdutoSelecionadoListener(ProdutoSelecionadoListener listener) {
    this.produtoSelecionadoListener = listener;
}

 

}

