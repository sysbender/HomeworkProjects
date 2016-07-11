/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Connection;
import java.util.List;
import modele.Reservation;

/**
 *
 * @author jason
 */
public class ReservationDao {
    private Connection conn;

    public ReservationDao(Connection conn) {
        this.conn = conn;
    }

    public List<Reservation> lireReservationParPassager(long codePassager){
        return null;
    }
    
    public void creerRerservation(List<Reservation> listeReservation){
        
    }
}
