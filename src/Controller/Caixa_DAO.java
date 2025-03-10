
package Controller;

import Connection.ConnectionFactory;
import Model.Produto;
import static View.Caixa_GUI.nomepdt_txt1;
import static View.Produtos_GUI.preco_txt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Caixa_DAO {
    
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
    
}
