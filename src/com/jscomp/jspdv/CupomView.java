package com.jscomp.jspdv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CupomView extends JPanel {
    private JTextArea cupomTextArea;
    private JButton imprimirButton;

    public CupomView(String cupomText) {
        setLayout(new BorderLayout());
        
        cupomTextArea = new JTextArea();
        cupomTextArea.setText(cupomText);
        cupomTextArea.setEditable(false);

        imprimirButton = new JButton("Imprimir");
        imprimirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirCupom();
            }
        });

        add(new JScrollPane(cupomTextArea), BorderLayout.CENTER);
        add(imprimirButton, BorderLayout.SOUTH);
    }

    private void imprimirCupom() {
        // Coloque aqui a lógica para imprimir o cupom.
        // Pode variar dependendo do ambiente e dos requisitos do sistema.
        // Você pode usar uma biblioteca de impressão, como o Java Print API, para fazer isso.
        // Exemplo simplificado:
        try {
            cupomTextArea.print();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
