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

public class PassagerDao {

    // db connection
    private Connection conn;

    //constructeur
    public PassagerDao(Connection conn) {
        this.conn = conn;
    }

    // chercher tous les  passagers
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

    //chercher les passagers par statut
    public List<Passager> lirePassagerParStatut(String statut) {
        List<Passager> listePassager = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select codepassager, nom, prenom, adresse, telephone, ville, pays, statut from passager where statut = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, statut);
            rs = ps.executeQuery();
            while (rs.next()) {
                listePassager.add(result2Passager(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs, ps);
        }

        return listePassager;
    }

    // inserer une liste de passager
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
            ps.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtil.close(ps);
        }

    }

    // inserer un passager
    public void creerPassager(Passager p) {
        String sql = "insert into passager (nom, prenom, adresse, telephone, ville, pays, statut) values(?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(3, p.getAdresse());
            ps.setString(4, p.getTelephone());
            ps.setString(5, p.getVille());
            ps.setString(6, p.getPays());
            ps.setString(7, p.getStatut());

            int r = ps.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtil.close(ps);
        }

    }

    //convertir resultset a passager objet
    public Passager result2Passager(ResultSet rs) throws SQLException {
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
