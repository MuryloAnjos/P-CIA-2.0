package Controller;

import Connection.ConnectionFactory;
import Model.Cliente;
import Model.Item_Venda;
import Model.Produto;
import static View.Caixa_GUI.Id_venda_txt;
import static View.Caixa_GUI.item_table;
import static View.Caixa_GUI.nomepdt_txt1;
import static View.Caixa_GUI.preco_txt;

import static View.ddscliente_GUI.cli_cpf;
import static View.ddscliente_GUI.cli_nome;
import static View.ddscliente_GUI.cli_tel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Venda_DAO {

    public void create(Cliente c) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("INSERT INTO vendas (cli_cpf) VALUES(?)");
            stmt.setString(1, c.getCpf());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venda Aberta");

        } catch (SQLException ex) {
            Logger.getLogger(Venda_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Salvar" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public void abrir_Venda() {
        Cliente c = new Cliente();
        Cliente_DAO dao = new Cliente_DAO();

        c.setNome(cli_nome.getText());
        String telefone = cli_tel.getText();
        if (!telefone.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "O telefone deve conter apenas números.");
            return;
        }
        c.setTel(telefone);
        c.setCpf(cli_cpf.getText());

        dao.cadastrar(c);

        Venda_DAO dao2 = new Venda_DAO();
        dao2.create(c);

        
        // TODO add your handling code here:

    }

    public void pegarId() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM vendas\n"
                    + "ORDER BY ven_id DESC\n"
                    + "LIMIT 1");
            rs = stmt.executeQuery();  // Use executeQuery para uma consulta de leitura

            if (rs.next()) { // Verifica se há algum resultado
                int idVenda = rs.getInt("ven_id");  // Pega o ID da venda
                Id_venda_txt.setText(String.valueOf(idVenda));  // Define o ID no campo de texto
            };

        } catch (SQLException ex) {
            Logger.getLogger(Venda_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Salvar" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    
    public void inserir_item(Item_Venda iv){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
                        
            stmt = con.prepareStatement("INSERT INTO item_venda (ven_id, pdt_id, quantidade) VALUES(?, ?, ?)");
            stmt.setInt(1, iv.getVen_id());
            stmt.setInt(2, iv.getPdt_id());
            stmt.setInt(3, iv.getQuantidade());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Item Adicionado");
                                                 
        } catch (SQLException ex) {
            Logger.getLogger(Venda_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Salvar " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public void read(Item_Venda iv) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Item_Venda> itens = new ArrayList<>();

        try {
            stmt = con.prepareStatement(
                      "SELECT "
                    + "pr.nome AS pdt_nome, "
                    + "pe.quantidade AS qtd_item, "
                    + "pr.preco AS pdt_preco, "
                    + "ven.id AS ven_id "
                    + "FROM item_venda pe "
                    + "JOIN produtos pr ON pe.pdt_id = pr.prod_id "
                    + "JOIN vendas ven ON pe.ven_id = ven.ven_id "
                    + "WHERE ven.ven_id = ? ");
            
            stmt.setInt(1, iv.getVen_id());
            rs = stmt.executeQuery();

             DefaultTableModel modelo = (DefaultTableModel) item_table.getModel();
            modelo.setRowCount(0);//isso aqui serve para remover os os registro anteriores

            while (rs.next()) {
                Object[] linha = {
                    rs.getString("pdt_nome"),
                    rs.getDouble("pdt_preco"),
                    rs.getInt("qtd_item"),
                    rs.getInt("ven_id")
                };
                modelo.addRow(linha);
                //o new serve pra colocar um array diretamente no vetor
            }

        } catch (SQLException ex) {
            Logger.getLogger(Produto_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

    }
    
    public void consultar(Produto p) {
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
            Logger.getLogger(Venda_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

    }
}
