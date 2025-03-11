
package Controller;

import Connection.ConnectionFactory;
import Model.Produto;
import Model.Venda;
import View.Caixa_GUI;
import static View.Caixa_GUI.item_table;
import static View.Caixa_GUI.nomepdt_txt1;
import static View.Caixa_GUI.valortl_txt;
import static View.Produtos_GUI.preco_txt;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import static com.itextpdf.text.pdf.PdfName.op;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Caixa_DAO {
    
    boolean taxei = false;
    public String res = "";
    int op = 0;
    
     public void consultar_produto(Produto p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT prod_nome, prod_preco FROM produtos WHERE prod_id = ?");
            stmt.setInt(1, p.getId());
            rs = stmt.executeQuery();

            String nome = "";
            double preco = 0;

            while (rs.next()) {
                nome = (rs.getString("prod_nome"));
                preco = rs.getDouble("prod_preco");
            }

            nomepdt_txt1.setText(nome);
            preco_txt.setText(String.valueOf(preco));


        } catch (SQLException ex) {
            Logger.getLogger(Caixa_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

    }
     
      public static void totalizar() {

        double total = 0;

        for (int c = 0; c < item_table.getRowCount(); c++) {

            double preco = Double.parseDouble(String.format("%.2f", item_table.getValueAt(c, 1)));
            int qtd = Integer.parseInt(item_table.getValueAt(c, 2).toString());

            double mult = (preco * qtd);

            total = total + mult;

        }

        View.Caixa_GUI.valortl_txt.setText(String.format("%.2f", total));
    }
      
      
    public void Relatorio(Venda v) throws Exception {

        Document doc = null;
        OutputStream os = null;

        double total = 0;
        double preco = 0;

        String pedidos = "";

        for (int i = 0; i < item_table.getRowCount(); i++) {

            String nome = (item_table.getValueAt(i, 0).toString());
            int qtd = Integer.parseInt(item_table.getValueAt(i, 2).toString());
            preco = Double.parseDouble(item_table.getValueAt(i, 1).toString());

            double mult = (preco * qtd);

            total = total + mult;

            pedidos += qtd + "x - " + nome + " (" + preco + " cada) = " + mult + "\n";

        }

        try {

            //cria o documento tamanho A4, margens de 2,54cm
            doc = new Document(PageSize.A4, 72, 72, 72, 72);

            //cria a stream de saída
            os = new FileOutputStream("/C:/Users/Murylo/Documents/notafiscal.pdf");

            //associa a stream de saída ao
            PdfWriter.getInstance(doc, os);

            //abre o documento
            doc.open();

//            HELVETICA: Uma fonte sans-serif muito comum e amplamente usada.
//            TIMES_ROMAN: Uma fonte serifada clássica.
//            COURIER: Uma fonte monoespaçada, adequada para código ou formatação de texto que exige largura fixa.
//            SMALL_ROMAN: Uma variação da fonte "Times Roman", mas com tamanho menor.
//            LATIN1: Uma fonte projetada para suportar os caracteres latinos.
            Font f = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);

            Paragraph por = new Paragraph("P&CIA", f);
            por.setSpacingAfter(20);
            doc.add(por);

            String x = JOptionPane.showInputDialog(null, "Qual é o método de pagamento ?\n"
                    + "1 - Débito\n"
                    + "2 - Crédito\n"
                    + "3 - Pix\n"
                    + "4 - Dinheiro");
            op = Integer.parseInt(x);

            double cmp = Double.parseDouble(valortl_txt.getText());

            if (op == 1) {
                res = "Débito";
            } else if (op == 2){
                res = "Crédito";
            }else if(op == 3){
                res = "Pix";
            }else {
                res = "Dinheiro";
            }
            
            v.setMet_pagamento(res);

            int ven_id = v.getId();
            double valor = v.getValor_total();

            Font f2 = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            Paragraph pf1 = new Paragraph("Id Venda : " + ven_id + "\n"
                    + "Itens: \n" + pedidos + "\n"
                    + "Método de Pagamento : " + res + "\n"
                    + "Valor Total : " + valor + "\n", f2);

            doc.add(pf1);

        } finally {

            if (doc != null) {

                //fechamento do documento
                doc.close();
            }

            if (os != null) {
                //fechamento da stream de saída
                os.close();
            }
        }

        Desktop.getDesktop().open(new File("/C:/Users/Murylo/Documents/notafiscal.pdf"));
    }
    
     public void fecharComanda(Venda v) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT ven_status FROM vendas WHERE ven_id = ?");
            stmt.setInt(1, v.getId());
            rs = stmt.executeQuery();

            if (rs.next()) {
                String stts = rs.getString("ven_status");

                if (stts.equalsIgnoreCase("fechada")) {
                    JOptionPane.showMessageDialog(null, "Venda já foi fechada.");
                    return;
                }
            }
            stmt = con.prepareStatement("UPDATE vendas SET ven_valor_total = ?, ven_met_pagamento = ?, ven_status = ? WHERE ven_id = ?");
            stmt.setDouble(1, v.getValor_total());
            stmt.setString(2, v.getMet_pagamento());
            stmt.setString(3, v.getStatus());
            stmt.setInt(4, v.getId());

            stmt.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Comanda " + c.getId() + " fechada com sucesso.");
            try {
                Relatorio(v);
            } catch (Exception ex) {
                Logger.getLogger(Caixa_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Caixa_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
