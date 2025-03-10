
package Controller;

import Connection.ConnectionFactory;
import Model.Produto;
import static View.Produtos_GUI.nomepro_txt;
import static View.Produtos_GUI.preco_txt;
import static View.Produtos_GUI.tipo_txt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Produto_DAO {

    public void create(Produto p) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO produtos (prod_nome, prod_preco, prod_tipo) VALUES(?, ?, ?)");
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setString(3, p.getTipo());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto Adicionado");
        } catch (SQLException ex) {
            Logger.getLogger(Produto_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Salvar" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Produto> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produtos");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("prod_id"));
                produto.setNome(rs.getString("prod_nome"));
                produto.setPreco(rs.getDouble("prod_preco"));
                produto.setTipo(rs.getString("prod_tipo"));
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Produto_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return produtos;

    }

    public void consul(Produto p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT prod_nome, prod_preco, prod_tipo FROM produtos WHERE prod_id = ?");
            stmt.setInt(1, p.getId());
            rs = stmt.executeQuery();

            String tipo = "";
            String nome = "";
            double preco = 0;

            while (rs.next()) {
                nome = (rs.getString("prod_nome"));
                preco = rs.getDouble("prod_preco");
                tipo = (rs.getString("prod_tipo"));
            }

            nomepro_txt.setText(nome);
            preco_txt.setText(String.valueOf(preco));
            tipo_txt.setText(tipo);
            
            nomepro_txt.setText(nome);
            
//            int id = Integer.parseInt(idpdt_txt.getText());
//            
//            produtos_table.setRowSelectionAllowed(true);
//            for(int k = 0; k < produtos_table.getRowCount(); k++){
//                
//                if(id == Integer.parseInt(produtos_table.getValueAt(k, 0).toString())){
//                    produtos_table.setRowSelectionInterval(k ,k);
//                }
//                
//            }

        } catch (SQLException ex) {
            Logger.getLogger(Produto_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

    }

    public void update(Produto p) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE produtos SET prod_nome = ?, prod_preco = ?, prod_tipo = ? WHERE prod_id = ?");
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setString(3, p.getTipo());
            stmt.setInt(4, p.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto Atualizado");
        } catch (SQLException ex) {
            Logger.getLogger(Produto_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public void delete(Produto p) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM produtos WHERE prod_id = ?");
            stmt.setInt(1, p.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto Deletado");
        } catch (SQLException ex) {
            Logger.getLogger(Produto_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Excluir" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

}
