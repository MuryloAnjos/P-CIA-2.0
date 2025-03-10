
package Controller;

import Connection.ConnectionFactory;
import Model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Cliente_DAO {
    
    public void cadastrar(Cliente f){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        try  {
                
            stmt = con.prepareStatement("INSERT INTO cliente (cli_nome, cli_cpf, cli_tel) VALUES(?, ?, ?)");
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCpf());
            stmt.setString(3, f.getTel());
                        
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente Pumba");
        
        } catch (SQLException ex) {
            Logger.getLogger(Cliente_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
    }
    
    public boolean check(String logs, String sen){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt= null;
        ResultSet rs = null;

        boolean log = false;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM func WHERE nome_usu = ? and senha = ?");
            stmt.setString(1, logs);
            stmt.setString(2, sen);            
            rs = stmt.executeQuery();
            
            if(rs.next()){
               log = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Cliente_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return log;
        
    }
    

    
}
