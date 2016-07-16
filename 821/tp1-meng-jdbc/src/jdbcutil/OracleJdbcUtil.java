package jdbcutil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleJdbcUtil extends JdbcUtil {

    public OracleJdbcUtil() throws ClassNotFoundException, IOException {
        this.dbms = "oracle";
        this.dbms_properties_file = dbms + "_properties.xml";
        this.driver = "oracle.jdbc.driver.OracleDriver";
        this.readProperties();
        Class.forName(driver);
    }

    @Override
    void initializeDatabase() {

        /*
        
         CREATE TABLE passager (
         codepassager number(10) NOT NULL constraint passager_pk primary key,
         nom varchar2(20) not null,
         prenom varchar2(20) not null,
         adresse varchar2(30) not null,
         telephone varchar2(20) not null,
         ville varchar2(20) not null,
         pays varchar2(20) not null,
         statut varchar2(20) not null
         );

         CREATE SEQUENCE codepassager_seq;


         CREATE OR REPLACE TRIGGER passager_tgr 
         BEFORE INSERT ON passager 
         FOR EACH ROW

         BEGIN
         SELECT codepassager_seq.NEXTVAL
         INTO   :new.codepassager
         FROM   dual;
         END;
         /
    
        
         CREATE TABLE reservation (
         codereservation number(10) NOT NULL constraint reservation_pk primary key,
         statutreservation varchar2(20) not null,
         datereservation date  not null,
         codepassager number(10) not null ,
         constraint  reservation_fk foreign key  (codepassager)references passager(codepassager)
         );

         CREATE SEQUENCE codereservation_seq;


         CREATE OR REPLACE TRIGGER reservation_tgr 
         BEFORE INSERT ON reservation 
         FOR EACH ROW

         BEGIN
         SELECT codereservation_seq.NEXTVAL
         INTO   :new.codereservation
         FROM   dual;
         END;
         /
        
        
         */
    }

    @Override
    Connection newConnection() throws SQLException {

        // oracle url example: "jdbc:oracle:thin:@myhost:1521:orcl"
        this.url = "jdbc:" + this.dbms + ":thin:@//" + server + ":" + port + "/" + this.database;
        System.out.println(url);
        Connection conn =  DriverManager.getConnection(this.url, username, password);
        conn.setAutoCommit(false);
        return conn;
    }

}
