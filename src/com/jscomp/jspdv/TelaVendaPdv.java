/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jscomp.jspdv;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Esc_Big03
 */
public class TelaVendaPdv extends javax.swing.JFrame implements ProdutoSelecionadoListener  {
    Preferences prefs = Preferences.userNodeForPackage(ConfiguracaoBancoDados.class);
    String urlBanco = prefs.get("urlBanco", "");
    String usuario = prefs.get("usuario", "");
    String senha = prefs.get("senha", "");
    private DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private List<Produto> carrinho = new ArrayList<>();
    private double valorTotal = 0.0;
    String nomeUsuario = UsuarioLogado.getNomeUsuario();
    String nomeLoja = carregarNomeLoja();
    
    public TelaVendaPdv() throws SQLException {
        initComponents();
   
        Color corDeFundo = new Color(189, 198, 199); 
        getContentPane().setBackground(corDeFundo);
        BuscarProdutoDialog buscarProdutoDialog;
        TelaVendaPdv telaVendaPdv = this;
        telaVendaPdv.setLocationRelativeTo(null);
        telaVendaPdv.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaVendaPdv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        Connection conexao = DriverManager.getConnection(urlBanco, usuario, senha);
        String sql = "SELECT logo FROM loja WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, 1); // Substitua idDaLoja pelo ID correto da loja
        ResultSet rs = stmt.executeQuery();

        
       
        txtnomeloja.setText(nomeLoja);
   
if (rs.next()) {
    byte[] imagemBytes = rs.getBytes("logo");
    ImageIcon imagemLogo = new ImageIcon(imagemBytes);
     Image imagem = imagemLogo.getImage();
     Image novaImagem = imagem.getScaledInstance(labelImage.getWidth(), labelImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon novaImagemIcon = new ImageIcon(novaImagem);
        labelImage.setIcon(novaImagemIcon);
}

rs.close();
stmt.close();
conexao.close();


      if (nomeUsuario != null) {
    // Defina o texto do JLabel para mostrar o nome de usuário
    userLog.setText( nomeUsuario);
} else {
    userLog.setText("Usuário não logado");
}
       
        codigoBarrasTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    adicionarProduto();
                }
            }
            
        } 
        
        );
        valorPagoTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    atualizarTroco();
                }
            }
    
    
    });}
  
    public String carregarNomeLoja() {
    String nomeLoja = "";

    try {
        Connection conexao = DriverManager.getConnection(urlBanco, usuario, senha);
        String sql = "SELECT nome FROM loja WHERE id = 1"; // Supondo que a loja tenha um ID de 1
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            nomeLoja = rs.getString("nome");
        }

        rs.close();
        stmt.close();
        conexao.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao carregar o nome da loja: " + e.getMessage());
    }

    return nomeLoja;
}

    public void produtoSelecionado(Produto produto) {
        carrinho.add(produto);
        valorTotal += produto.getPreco();
        atualizarCarrinho();
    }
    
   
   
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            
            JList<Produto> lista = (JList<Produto>) e.getSource();

            if (!lista.isSelectionEmpty()) {
                Produto produtoSelecionado = lista.getSelectedValue();
                
                carrinho.add(produtoSelecionado);
                valorTotal += produtoSelecionado.getPreco();
                atualizarCarrinho();
                lista.clearSelection();
            }
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        codigoBarrasTextField = new javax.swing.JTextField();
        btnBuscarProd = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        carrinhoTextArea = new javax.swing.JTextArea();
        valorTotalLabel = new javax.swing.JTextField();
        valorPagoTextField = new javax.swing.JTextField();
        trocoLabel = new javax.swing.JTextField();
        bntFinalizar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelImage = new javax.swing.JLabel();
        txtnomeloja = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        userLog = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JS PDV");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.green);
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        codigoBarrasTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("DIGITE OU BIPE O CÓDIGO")));
        codigoBarrasTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoBarrasTextFieldActionPerformed(evt);
            }
        });

        btnBuscarProd.setText("BUSCAR ");
        btnBuscarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProdActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        carrinhoTextArea.setEditable(false);
        carrinhoTextArea.setColumns(20);
        carrinhoTextArea.setRows(5);
        carrinhoTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("CUPOM"));
        carrinhoTextArea.setFocusable(false);
        jScrollPane1.setViewportView(carrinhoTextArea);

        valorTotalLabel.setEditable(false);
        valorTotalLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        valorTotalLabel.setForeground(new java.awt.Color(0, 102, 255));
        valorTotalLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("VALOR TOTAL")));
        valorTotalLabel.setFocusable(false);

        valorPagoTextField.setBorder(javax.swing.BorderFactory.createTitledBorder("VALOR PAGO"));
        valorPagoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorPagoTextFieldActionPerformed(evt);
            }
        });

        trocoLabel.setEditable(false);
        trocoLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        trocoLabel.setForeground(new java.awt.Color(0, 102, 0));
        trocoLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("TROCO"));

        bntFinalizar.setBackground(new java.awt.Color(0, 102, 0));
        bntFinalizar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        bntFinalizar.setForeground(new java.awt.Color(255, 255, 255));
        bntFinalizar.setText("FINALIZAR");
        bntFinalizar.setBorder(null);
        bntFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntFinalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valorTotalLabel)
                    .addComponent(valorPagoTextField)
                    .addComponent(trocoLabel)
                    .addComponent(bntFinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(valorTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(valorPagoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(trocoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(bntFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelImage.setBackground(new java.awt.Color(255, 153, 153));
        labelImage.setFont(new java.awt.Font("Sitka Text", 0, 24)); // NOI18N

        txtnomeloja.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtnomeloja.setForeground(new java.awt.Color(255, 255, 255));
        txtnomeloja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtnomeloja.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(138, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelImage, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtnomeloja, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)))
                .addGap(96, 96, 96))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtnomeloja, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelImage, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190))
        );

        jButton1.setText("SAIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        userLog.setBackground(new java.awt.Color(255, 255, 255));
        userLog.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        userLog.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(userLog, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(codigoBarrasTextField, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(codigoBarrasTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscarProd)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(userLog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void codigoBarrasTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoBarrasTextFieldActionPerformed

    }//GEN-LAST:event_codigoBarrasTextFieldActionPerformed

    private void bntFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntFinalizarActionPerformed
        finalizarVenda();
    }//GEN-LAST:event_bntFinalizarActionPerformed

    private void valorPagoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorPagoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valorPagoTextFieldActionPerformed

    private void btnBuscarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProdActionPerformed
       BuscarProdutoDialog dialog = new BuscarProdutoDialog(this,TelaVendaPdv.this);
       dialog.exibirDialog();
    }//GEN-LAST:event_btnBuscarProdActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setLocationRelativeTo(null); 
            telaLogin.setVisible(true);
            dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    
    public static void main(String args[]) {
       
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaVendaPdv().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaVendaPdv.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
        });
    }
    public void adicionarProduto() {
        String codigoBarras = codigoBarrasTextField.getText();
        Produto produto = buscarProdutoNoBanco(codigoBarras);

        if (produto != null) {
            carrinho.add(produto);
            valorTotal += produto.getPreco();
            atualizarCarrinho();
            codigoBarrasTextField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Produto não encontrado.");
        }
    }

    private Produto buscarProdutoNoBanco(String codigoBarras) {
        try (Connection conexao = DriverManager.getConnection(urlBanco, usuario, senha)) {
            String sql = "SELECT * FROM produtos WHERE ean = ?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, codigoBarras);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt("id");
                        String nome = rs.getString("nome");
                        double preco = rs.getDouble("preco");

                        return new Produto(id, nome, preco);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void atualizarCarrinho() {
        carrinhoTextArea.setText("");
        for (Produto produto : carrinho) {
            carrinhoTextArea.append(produto.getNome() + " - R$" + decimalFormat.format(produto.getPreco()) + "\n");
        }
        valorTotalLabel.setText("R$" + String.format("%.2f", valorTotal));

        // Atualiza o troco ao adicionar um produto
        atualizarTroco();
    }

    private void finalizarVenda() {
        try {
            double valorPago = Double.parseDouble(valorPagoTextField.getText());
            double troco = valorPago - valorTotal;
            if (troco >= 0) {
                List<ItemVenda> itensVenda = new ArrayList<>();
                for (Produto produto : carrinho) {
                    ItemVenda item = new ItemVenda(produto.getNome(), produto.getPreco());
                    itensVenda.add(item);
                }

                String cupomText = gerarCupom(itensVenda, valorTotal, valorPago, troco);
                 salvarHistoricoVenda(cupomText, valorTotal, valorPago, troco);
                carrinho.clear();
                valorTotal = 0.0;
                atualizarCarrinho();
                abrirJanelaCupom(cupomText);
            } else {
                JOptionPane.showMessageDialog(this, "Valor pago insuficiente. Verifique o valor pago.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Insira um valor pago válido.");
        }
    }
    
    
    private String gerarCupom(List<ItemVenda> itens, double valorTotal, double valorPago, double troco) {
        StringBuilder cupomText = new StringBuilder();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        cupomText.append("========== Cupom de Venda ==========\n");
        cupomText.append("Loja:"+nomeLoja+"\n");
        cupomText.append("Data da Venda: " + sdf.format(new java.util.Date()) + "\n");
        cupomText.append("====================================\n");
        cupomText.append("Itens Comprados:\n");

        for (ItemVenda item : itens) {
            cupomText.append(item.getNome() + " - R$" + item.getPreco() + "\n");
        }

        cupomText.append("====================================\n");
        cupomText.append("Valor Total: R$" + String.format("%.2f", valorTotal) + "\n");
        cupomText.append("Valor Pago: R$" + String.format("%.2f", valorPago) + "\n");
        cupomText.append("Troco: R$" + String.format("%.2f", troco) + "\n");

        return cupomText.toString();
    }

    private void abrirJanelaCupom(String cupomText) {
        JFrame cupomFrame = new JFrame("Cupom de Venda");
        cupomFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cupomFrame.setSize(400, 400);
        CupomView cupomView = new CupomView(cupomText);
        cupomFrame.add(cupomView);
       cupomFrame.setLocationRelativeTo(null);
        cupomFrame.setVisible(true);
        trocoLabel.setText(" ");
        valorPagoTextField.setText(" ");
}
        
        
    
        private void atualizarTroco() {
        try {
            double valorPago = Double.parseDouble(valorPagoTextField.getText());
            double troco = valorPago - valorTotal;
            trocoLabel.setText("R$" + String.format("%.2f", troco));
        } catch (NumberFormatException e) {
            trocoLabel.setText(" ");
        }
    }
        private void salvarHistoricoVenda(String cupomText, double valorTotal, double valorPago, double troco) {
    try (Connection conexao = DriverManager.getConnection(urlBanco, usuario, senha)) {
        String sql = "INSERT INTO historico_vendas (data_venda, itens_vendidos, valor_total, valor_pago, troco) VALUES (NOW(), ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cupomText);
            stmt.setDouble(2, valorTotal);
            stmt.setDouble(3, valorPago);
            stmt.setDouble(4, troco);
            stmt.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntFinalizar;
    public javax.swing.JButton btnBuscarProd;
    private javax.swing.JTextArea carrinhoTextArea;
    public javax.swing.JTextField codigoBarrasTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelImage;
    private javax.swing.JTextField trocoLabel;
    private javax.swing.JLabel txtnomeloja;
    public javax.swing.JLabel userLog;
    private javax.swing.JTextField valorPagoTextField;
    private javax.swing.JTextField valorTotalLabel;
    // End of variables declaration//GEN-END:variables

   
}
