/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.PassagerDao;
import dao.ReservationDao;
import java.net.URL;
import java.sql.Connection;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import modele.Passager;
import modele.Reservation;

/**
 *
 * @author jason
 */
public class FXMLDocumentController implements Initializable {

    private static Connection conn;
    private Long codePassagerSelected;   //passager choisi dan le tableview
    private int derniereRecherche = 0;         // 1 - tout, 2 - Occasionnel , 3 - Frequent
    //TableColumns
    private TableColumn<Passager, Long> colCodepassager;
    private TableColumn<Passager, String> colNom;
    private TableColumn<Passager, String> colPrenom;
    private TableColumn<Passager, String> colAdresse;
    private TableColumn<Passager, String> colTelephone;
    private TableColumn<Passager, String> colVille;
    private TableColumn<Passager, String> colPays;
    private TableColumn<Passager, String> colStatutPassager;
    private TableColumn<Reservation, Long> colCodeReservation;
    private TableColumn<Reservation, Date> colDate;
    private TableColumn<Reservation, String> colStatutReservation;

    // UI 
    @FXML
    private Label lblCodePassager;
    @FXML
    private BorderPane border;
    @FXML
    private HBox hBoxTop, hBoxCenter, hBoxPassager, hBoxReservation;
    @FXML
    private VBox vBoxLeft, vBoxRight;
    @FXML
    private TableView tablePassager, tableReservation;

    @FXML
    private Button btnTous, btnOccasionnel, btnFrequent, btnAddPassager, btnAddReservation;
    @FXML
    private TextField inNom, inPrenom, inAdresse, inTelephone, inVille, inPays;
    @FXML
    private DatePicker inDate;
    @FXML
    private ChoiceBox inStatut, inStatut2;

    // intiliazation
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // tableView Passager

        //colCodepassager.setCellValueFactory( new PropertyValueFactory<Passager, String>("codePassager"));
        // configurer ChoiceBox
        inStatut.getItems().add("Occasionnel");
        inStatut.getItems().add("Frequent Flyer");
        inStatut.getSelectionModel().selectFirst();
        inStatut.setTooltip(new Tooltip("Choisir Statut de Passager"));

        inStatut2.getItems().add("Standby");
        inStatut2.getItems().add("OK");
        inStatut2.getSelectionModel().selectFirst();
        inStatut2.setTooltip(new Tooltip("Choisir Statut de Reservation"));
        // Date picker = aujourdhui
        inDate.setValue(LocalDate.now());

        //ajouter des tableColumn au tableView
        initTableViewPassager();
        initTableViewReservation();

        // binding - varifier que editField nest pas vide
        BooleanBinding bbPassager = new BooleanBinding() {
            {
                super.bind(inNom.textProperty(), inPrenom.textProperty(), inAdresse.
                        textProperty(), inTelephone.textProperty(),
                        inVille.textProperty(), inPays.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return (inNom.getText().isEmpty()
                        || inPrenom.getText().isEmpty()
                        || inAdresse.getText().isEmpty()
                        || inTelephone.getText().isEmpty()
                        || inVille.getText().isEmpty()
                        || inPays.getText().isEmpty());
            }
        };

        btnAddPassager.disableProperty().bind(bbPassager);

        // row select listener - si il n'y pas passager choisi, disable btnAddReservation
        tablePassager.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            System.out.println(newSelection);
            if (newSelection == null) {
                setCodePassagerSelected(null);
            } else {
                setCodePassagerSelected(((Passager) newSelection).getCodePassager());
            }

        });

    }

    // si le passager currant change, traiter : changer codePassagerSelected ,lblCodePassager,  disable/enable btnAddReservation
    public void setCodePassagerSelected(Long codePassagerSelected) {
        this.codePassagerSelected = codePassagerSelected;

        if (codePassagerSelected == null) {
            btnAddReservation.setDisable(true);
            lblCodePassager.setText("");
            tableReservation.getItems().clear();
        } else {
            ReservationDao rDao = new ReservationDao(conn);
            btnAddReservation.setDisable(false);
            lblCodePassager.setText(String.valueOf(codePassagerSelected));
            tableReservation.setItems(list2Observable(rDao.lireReservationParPassager(codePassagerSelected)));
        }
    }

    // button click handler - creer et ajouter un passager
    @FXML
    public void btnAddPassagerClicked() {
        Passager p = new Passager();
        //inNom, inPrenom, inAdresse, inTelephone, inVille, inPays;
        p.setNom(inNom.getText());
        p.setPrenom(inPrenom.getText());
        p.setAdresse(inAdresse.getText());
        p.setTelephone(inTelephone.getText());
        p.setVille(inVille.getText());
        p.setPays(inPays.getText());
        p.setStatut(inStatut.getValue().toString());

        // inserer p
        PassagerDao pDao = new PassagerDao(conn);
        pDao.creerPassager(p);
        // refresh tableview
        switch (derniereRecherche) {
            case 1:
                btnChercherTousClicked();
                break;                
            case 2:
                btnChercherOccasionnelClicked();
                break;
            case 3:
                btnChercherFrequentClicked();
                break;
            default:
                btnChercherTousClicked();
                break;
        }
        
        // clear

        inNom.clear();
        inPrenom.clear();
        inAdresse.clear();
        inTelephone.clear(); 
        inVille.clear(); 
        inPays.clear();
        inStatut.getSelectionModel().selectFirst();

    }

    // add reservation button click handler
    @FXML
    public void btnAddReservationClicked() {
        Reservation r = new Reservation();
        r.setCodePassager(codePassagerSelected);
        r.setStatutReservation(inStatut2.getValue().toString());

        LocalDate localDate = inDate.getValue();
        r.setDateReservation(localDate);
        ReservationDao rDao = new ReservationDao(conn);
        rDao.creerRerservation(r);
        // rafraichir reservation tableview 
        setCodePassagerSelected(codePassagerSelected);
        
        //reinitiliaser
        inStatut2.getSelectionModel().selectFirst();
        inDate.setValue(LocalDate.now());

    }

    // btn afficher tous les passager
    @FXML
    public void btnChercherTousClicked() {
        derniereRecherche = 1;
        PassagerDao pDao = new PassagerDao(conn);

        tablePassager.setItems(list2Observable(pDao.lirePassagerTous()));

    }

    // afficher les passager Occasionnel
    @FXML
    public void btnChercherOccasionnelClicked() {
        derniereRecherche = 2;
        PassagerDao pDao = new PassagerDao(conn);

        tablePassager.setItems(list2Observable(pDao.lirePassagerParStatut("Occasionnel")));
    }

    // afficher les passager Frequent
    @FXML
    public void btnChercherFrequentClicked() {
        derniereRecherche = 3;
        PassagerDao pDao = new PassagerDao(conn);
        tablePassager.setItems(list2Observable(pDao.lirePassagerParStatut("Frequent Flyer")));
    }

    public Long getCodePassagerSelected() {
        return codePassagerSelected;
    }

    // setter pour : connection
    public static void setConn(Connection conn) {
        FXMLDocumentController.conn = conn;
    }

    // convetir list a observable
    public <T> ObservableList<T> list2Observable(List<T> list) {
        ObservableList<T> observableList = FXCollections.observableArrayList();
        observableList.addAll(list);
        return observableList;
    }

    // initilize tableview : reservation
    private void initTableViewReservation() {
        //colCodeReservation, 
        colCodeReservation = new TableColumn<>("Code");
        colCodeReservation.setMinWidth(20);
        colCodeReservation.setCellValueFactory(new PropertyValueFactory<>("codeReservation"));
        //colDate
        colDate = new TableColumn<>("Date");
        colDate.setMinWidth(100);
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));

        //colStatutReservation
        colStatutReservation = new TableColumn<>("Statut");
        colStatutReservation.setMinWidth(50);
        colStatutReservation.setCellValueFactory(new PropertyValueFactory<>("statutReservation"));

        tableReservation.getColumns().addAll(colCodeReservation, colDate, colStatutReservation);
    }

    private void initTableViewPassager() {
        //colCodepassager, colNom, colPrenom, colAdresse, colTelephone, colVille, colPays, colStatutPassager

        colCodepassager = new TableColumn<>("Code");
        colCodepassager.setMinWidth(20);
        colCodepassager.setPrefWidth(50);
        colCodepassager.setCellValueFactory(new PropertyValueFactory<>("codePassager"));
        //colNom
        colNom = new TableColumn<>("Nom");
        colNom.setMinWidth(30);
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        //colPrenom
        colPrenom = new TableColumn<>("Prenom");
        colPrenom.setMinWidth(30);
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        //colAdresse
        colAdresse = new TableColumn<>("Adresse");
        colAdresse.setMinWidth(100);
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        //colTelephone
        colTelephone = new TableColumn<>("Telephone");
        colTelephone.setMinWidth(100);
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        //colVille
        colVille = new TableColumn<>("Ville");
        colVille.setMinWidth(20);
        colVille.setCellValueFactory(new PropertyValueFactory<>("ville"));
        //colPays
        colPays = new TableColumn<>("Pays");
        colPays.setMinWidth(20);
        colPays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        //colStatutPassager
        colStatutPassager = new TableColumn<>("Statut");
        colStatutPassager.setMinWidth(100);
        colStatutPassager.setCellValueFactory(new PropertyValueFactory<>("statut"));

        tablePassager.getColumns().addAll(colCodepassager, colNom, colPrenom, colAdresse, colTelephone, colVille, colPays, colStatutPassager);
    }

}
