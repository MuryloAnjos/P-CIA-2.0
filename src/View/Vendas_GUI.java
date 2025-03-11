/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Venda_DAO;
import Model.Venda;
import static View.Produtos_GUI.id_txt;
import static View.Produtos_GUI.nomepro_txt;
import static View.Produtos_GUI.preco_txt;
import static View.Produtos_GUI.tipo_txt;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.MenuElement;
import javax.swing.plaf.basic.BasicMenuBarUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Kaua
 */
public class Vendas_GUI extends javax.swing.JFrame {

    private void customizeMenuBar(JMenuBar menuBar) {

        menuBar.setUI(new BasicMenuBarUI() {

            @Override
            public void paint(Graphics g, JComponent c) {
                g.setColor(Color.black);
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }

        });

        MenuElement[] menus = menuBar.getSubElements();

        for (MenuElement menuElement : menus) {

            JMenu menu = (JMenu) menuElement.getComponent();
            changeComponentColors(menu);
            menu.setOpaque(true);

            MenuElement[] menuElements = menu.getSubElements();

            for (MenuElement popupMenuElement : menuElements) {

                JPopupMenu popupMenu = (JPopupMenu) popupMenuElement.getComponent();
                popupMenu.setBorder(null);

                MenuElement[] menuItens = popupMenuElement.getSubElements();

                for (MenuElement menuItemElement : menuItens) {

                    JMenuItem menuItem = (JMenuItem) menuItemElement.getComponent();
                    changeComponentColors(menuItem);
                    menuItem.setOpaque(true);

                }
            }
        }
    }

    private void changeComponentColors(Component comp) {
        comp.setBackground(Color.black);
        comp.setForeground(Color.white);
    }

    /**
     * Creates new form Vendas_GUI
     */
    public Vendas_GUI() {
        initComponents();
        DefaultTableModel modelo = (DefaultTableModel) vendas_table.getModel();
        vendas_table.setRowSorter(new TableRowSorter(modelo));
        vendas_table.setAutoCreateRowSorter(false);
        readJTable();
        customizeMenuBar(barrinha);

    }

    public void readJTable() {
        DefaultTableModel modelo = (DefaultTableModel) vendas_table.getModel();
        Venda_DAO ven = new Venda_DAO();

        modelo.setNumRows(0);
        for (Venda v : ven.read1()) {
            modelo.addRow(new Object[]{
                v.getId(),
                v.getData(),
                v.getMet_pagamento(),
                v.getValor_total()
            });

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        valortl_txt = new javax.swing.JTextField();
        id_compra_txt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        vendas_table = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        quant_text = new javax.swing.JLabel();
        preco_text = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        limpar_BTN = new javax.swing.JButton();
        editar_BTN = new javax.swing.JButton();
        cli_cpf_txt = new javax.swing.JLabel();
        barrinha = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel1.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Valor Total :");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(310, 100, 90, 30);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("VENDAS");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(370, 10, 170, 40);
        jPanel1.add(valortl_txt);
        valortl_txt.setBounds(400, 100, 120, 30);

        id_compra_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_compra_txtActionPerformed(evt);
            }
        });
        jPanel1.add(id_compra_txt);
        id_compra_txt.setBounds(130, 100, 60, 30);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Cpf do Cliente :");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(560, 100, 110, 30);

        vendas_table.setBackground(new java.awt.Color(103, 103, 103));
        vendas_table.setForeground(new java.awt.Color(255, 255, 255));
        vendas_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id_Venda", "Data", "Método_Pagamento", "Valor_Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        vendas_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vendas_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(vendas_table);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(0, 250, 880, 250);

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("ID_Venda :");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(40, 90, 100, 50);
        jPanel1.add(quant_text);
        quant_text.setBounds(160, 220, 140, 30);
        jPanel1.add(preco_text);
        preco_text.setBounds(140, 180, 140, 30);

        jButton1.setBackground(new java.awt.Color(37, 36, 36));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/lupa (1).png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(210, 100, 50, 30);

        limpar_BTN.setBackground(new java.awt.Color(37, 36, 36));
        limpar_BTN.setForeground(new java.awt.Color(255, 255, 255));
        limpar_BTN.setText("Limpar");
        limpar_BTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpar_BTNActionPerformed(evt);
            }
        });
        jPanel1.add(limpar_BTN);
        limpar_BTN.setBounds(510, 180, 130, 32);

        editar_BTN.setBackground(new java.awt.Color(37, 36, 36));
        editar_BTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/editar (1).png"))); // NOI18N
        editar_BTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar_BTNActionPerformed(evt);
            }
        });
        jPanel1.add(editar_BTN);
        editar_BTN.setBounds(290, 180, 60, 40);
        jPanel1.add(cli_cpf_txt);
        cli_cpf_txt.setBounds(680, 100, 170, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 880, 510);

        barrinha.setBackground(new java.awt.Color(0, 0, 0));

        jMenu1.setBackground(new java.awt.Color(0, 0, 0));
        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Início");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem2.setText("Tela Inicial");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        barrinha.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(0, 0, 0));
        jMenu2.setForeground(new java.awt.Color(255, 255, 255));
        jMenu2.setText("Produtos");

        jMenuItem3.setText("Produtos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        barrinha.add(jMenu2);

        jMenu3.setBackground(new java.awt.Color(0, 0, 0));
        jMenu3.setForeground(new java.awt.Color(255, 255, 255));
        jMenu3.setText("Caixa");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jMenuItem4.setText("Caixa");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        barrinha.add(jMenu3);

        jMenu4.setBackground(new java.awt.Color(0, 0, 0));
        jMenu4.setForeground(new java.awt.Color(255, 255, 255));
        jMenu4.setText("Vendas");
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });

        jMenuItem6.setText("Vendas");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        barrinha.add(jMenu4);

        jMenu8.setBackground(new java.awt.Color(0, 0, 0));
        jMenu8.setForeground(new java.awt.Color(255, 255, 255));
        jMenu8.setText("Sair");
        jMenu8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu8ActionPerformed(evt);
            }
        });

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem1.setText("Fechar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem1);

        barrinha.add(jMenu8);

        setJMenuBar(barrinha);

        setSize(new java.awt.Dimension(877, 526));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        new Inicio_GUI().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        new Produtos_GUI().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        new Caixa_GUI().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        new Vendas_GUI().setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed

    }//GEN-LAST:event_jMenu4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:

        String x = JOptionPane.showInputDialog(null, "Deseja realmente fechar ?"
                + " \n1 - Sim"
                + " \n2 - Não "
        );
        int op = Integer.parseInt(x);

        if (op == 1) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenu8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu8ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenu8ActionPerformed

    private void vendas_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendas_tableMouseClicked
        // TODO add your handling code here:
        if (vendas_table.getSelectedRow() != -1) {
            id_compra_txt.setText(vendas_table.getValueAt(vendas_table.getSelectedRow(), 0).toString());
            valortl_txt.setText(vendas_table.getValueAt(vendas_table.getSelectedRow(), 3).toString());

        }
    }//GEN-LAST:event_vendas_tableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Venda v = new Venda();
        Venda_DAO dao = new Venda_DAO();
        v.setId(Integer.parseInt(id_compra_txt.getText()));
        dao.consul(v);
        readJTable();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void id_compra_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_compra_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_compra_txtActionPerformed

    private void limpar_BTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpar_BTNActionPerformed
        // TODO add your handling code here:
        
        id_compra_txt.setText("");
        valortl_txt.setText("");
        cli_cpf_txt.setText("");
    }//GEN-LAST:event_limpar_BTNActionPerformed

    private void editar_BTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_BTNActionPerformed
        // TODO add your handling code here:
        
        Venda c = new Venda();
        Venda_DAO dao = new Venda_DAO();
        c.setValor_total(Double.parseDouble(valortl_txt.getText()));
        c.setId(Integer.parseInt(id_compra_txt.getText()));
        dao.update(c);
        readJTable();
        id_compra_txt.setText("");
        valortl_txt.setText("");
        
    }//GEN-LAST:event_editar_BTNActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vendas_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vendas_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vendas_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vendas_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vendas_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JMenuBar barrinha;
    public static javax.swing.JLabel cli_cpf_txt;
    private javax.swing.JButton editar_BTN;
    public static javax.swing.JTextField id_compra_txt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpar_BTN;
    private javax.swing.JLabel preco_text;
    private javax.swing.JLabel quant_text;
    public static javax.swing.JTextField valortl_txt;
    private javax.swing.JTable vendas_table;
    // End of variables declaration//GEN-END:variables
}
