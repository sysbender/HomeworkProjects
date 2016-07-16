
package control;

import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jdbcutil.JdbcUtil;
import jdbcutil.OracleJdbcUtil;


public class AppCtr extends Application {

    // connection
    private static Connection conn;


    public static void main(String[] args) {
        try {
            //JdbcUtil jdbcutil = new OracleJdbcUtil();
            JdbcUtil jdbcutil = new OracleJdbcUtil();
            conn = jdbcutil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        // passer connection a FXML controller
        FXMLDocumentController.setConn(conn);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop(); //To change body of generated methods, choose Tools | Templates.
        System.out.println("-------------=============close connection ========-------------");
        JdbcUtil.close(null, null, conn);
    }

}
