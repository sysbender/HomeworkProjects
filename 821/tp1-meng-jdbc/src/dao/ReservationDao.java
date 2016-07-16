
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbcutil.JdbcUtil;
import modele.Reservation;

public class ReservationDao {

    private Connection conn;

    public ReservationDao(Connection conn) {
        this.conn = conn;
    }
     // lire reservation 
    public List<Reservation> lireReservationParPassager(long codePassager) {
        List<Reservation> listReservation = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select codeReservation, statutreservation, datereservation, codepassager from reservation where codepassager = ? ";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, codePassager);
            rs = ps.executeQuery();
            while(rs.next()){
                listReservation.add(result2Reservation(rs));
            }
            return listReservation;
            
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }       finally {
            JdbcUtil.close(rs,ps);
        }

    }
    // inserer reservation
    public void creerRerservation(Reservation reservation) {
        PreparedStatement ps =null;
        String sql = "insert into reservation (statutReservation, datereservation,codepassager)values(?,?,?)";
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, reservation.getStatutReservation());
            ps.setDate(2, java.sql.Date.valueOf(reservation.getDateReservation()));
            
            ps.setLong(3, reservation.getCodePassager());
            ps.executeUpdate();
            conn.commit();
        }catch(SQLException e){
            throw new DaoException(e.getMessage(), e);
        }finally{
            JdbcUtil.close(ps);
        }
    }
    
    
    //convertir resultset a passager objet
    public Reservation result2Reservation(ResultSet rs) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setCodeReservation(rs.getLong("codereservation"));
        reservation.setCodePassager(rs.getLong("codepassager"));
        reservation.setStatutReservation(rs.getString("statutreservation"));
        reservation.setDateReservation( rs.getDate("datereservation").toLocalDate());
        return reservation;
    }
    
}
