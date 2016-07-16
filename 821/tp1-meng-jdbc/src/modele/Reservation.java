
package modele;

import java.time.LocalDate;

public class Reservation {
    private long codeReservation;
    private String statutReservation;
    private LocalDate dateReservation;
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

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public long getCodePassager() {
        return codePassager;
    }

    public void setCodePassager(long codePassager) {
        this.codePassager = codePassager;
    }   
}
