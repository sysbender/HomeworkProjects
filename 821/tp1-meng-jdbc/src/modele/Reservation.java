/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Date;

/**
 *
 * @author jason
 */
public class Reservation {
    private long codeReservation;
    private String statutReservation;
    private Date dateReservation;
    private long codePassager;

    public long getCodeReservation() {
        return codeReservation;
    }

    public void setCodeReservation(long codeReservation) {
        this.codeReservation = codeReservation;
    }

    public String getStatutReservation() {
        return statutReservation;
    }

    public void setStatutReservation(String statutReservation) {
        this.statutReservation = statutReservation;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public long getCodePassager() {
        return codePassager;
    }

    public void setCodePassager(long codePassager) {
        this.codePassager = codePassager;
    }

  
    
}
