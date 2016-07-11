/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbcutil.JdbcUtil;
import modele.Passager;

/**
 * Create : créer Read : lire Update : mettre à jour Delete : supprimer
 *
 * @author jason
 */
public class PassagerDao {

    private Connection conn;

    public PassagerDao(Connection conn) {
        this.conn = conn;
    }

    public List<Passager> lirePassagerTous() {
        List<Passager> listePassager = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        String sql = "select codepassager, nom, prenom, adresse, telephone, ville, pays, statut from passager";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {                
               listePassager.add(result2Passager(rs)); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs, st);
        }

        return listePassager;
    }

    public List<Passager> lirePassagerParStatut(String statut) {
        return null;
    }

    public void creerPassager(List<Passager> listePassager) {
        String sql = "insert into passager (nom, prenom, adresse, telephone, ville, pays, statut) values(?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            for (Passager p : listePassager) {
                ps.setString(1, p.getNom());
                ps.setString(2, p.getPrenom());
                ps.setString(3, p.getAdresse());
                ps.setString(4, p.getTelephone());
                ps.setString(5, p.getVille());
                ps.setString(6, p.getPays());
                ps.setString(7, p.getStatut());
                ps.addBatch();
            }
            int[] r = ps.executeBatch();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtil.close(ps);
        }

    }
    
    public Passager result2Passager(ResultSet rs) throws SQLException{
        Passager passager = new Passager();
        passager.setCodePassager(rs.getLong("codepassager"));
        passager.setNom(rs.getString("nom"));
        passager.setPrenom(rs.getString("prenom"));
        passager.setAdresse(rs.getString("adresse"));
        passager.setTelephone(rs.getString("telephone"));
        passager.setVille(rs.getString("ville"));
        passager.setPays(rs.getString("pays"));
        passager.setStatut(rs.getString("statut"));
        
        return passager;
    }
    
}
