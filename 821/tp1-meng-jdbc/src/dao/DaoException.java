/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

/**
 *
 * @author jason
 */
public class DaoException extends RuntimeException{

    public DaoException() {
    }

    public DaoException(String string) {
        super(string);
    }

    public DaoException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public DaoException(Throwable thrwbl) {
        super(thrwbl);
    }

    public DaoException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
    
}
