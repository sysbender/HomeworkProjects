/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.PassagerDao;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import jdbcutil.JdbcUtil;
import jdbcutil.OracleJdbcUtil;
import modele.Passager;

/**
 *
 * @author jason
 */
public class AppCtr {

    private static Connection conn;

    public static void main(String[] args) {
        List<Passager> listePassager = new ArrayList<>();
        try {
            JdbcUtil jdbcutil = new OracleJdbcUtil();
            conn = jdbcutil.getConnection();
            String[] s = {"FlouFlou","Alain", "78 avenue du ciel", "514-555-8575", "Montreal", "Canada", "Frequent Flyer"};
            Passager p = new Passager();
            p.saisirPassager(s);
            System.out.println("===========new passager============");
            System.out.println(p);
            listePassager.add(p);
            System.out.println("=============new passager dao -------------");
            PassagerDao pDao = new PassagerDao(conn);
            pDao.creerPassager(listePassager);
            System.out.println("-------------insert ------------------");
            List<Passager> plist = pDao.lirePassagerTous();
            for(Passager passager : plist){
                System.out.println(passager);  
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("can not connect to oracle");

        }finally{
            System.out.println("closing  jdbc to oracle");
            JdbcUtil.close(null, null, conn);
        }
        
        
        
    }

    

}
