/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.mycontacts.controller;



import it.unisa.diem.mycontacts.data.Contatto;
import it.unisa.diem.mycontacts.datastructure.Rubrica;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author lupo
 */
public class RightView2Controller implements Initializable {

    @FXML
    private MenuItem stopButton;
    @FXML
    private MenuItem confirmButton;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField cognomeField;
    @FXML
    private TextField numero1Field;
    @FXML
    private TextField email1Field;
    @FXML
    private TextField numero2Field;
    @FXML
    private TextField email2Field;
    @FXML
    private TextField numero3Field;
    @FXML
    private TextField email3Field;
    @FXML
    private CheckBox preferitoCheck;
    
    private MainViewController mainViewController;
    
    private Rubrica rubrica;
    
    private Contatto contatto;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
        
        this.rubrica = mainViewController.getRubrica();
    }
    
    public void setContatto(Contatto contatto) {
        this.contatto = contatto;
        
        if (contatto != null) {
            nomeField.setText(contatto.getNome());
            cognomeField.setText(contatto.getCognome());
            preferitoCheck.setSelected(contatto.isPreferito());

            // Popola i campi dei numeri di telefono e delle email
            if (contatto.getNumeri().size() > 0) numero1Field.setText(contatto.getNumeri().toArray()[0].toString());
            if (contatto.getNumeri().size() > 1) numero2Field.setText(contatto.getNumeri().toArray()[1].toString());
            if (contatto.getNumeri().size() > 2) numero3Field.setText(contatto.getNumeri().toArray()[2].toString());

            if (contatto.getEmail().size() > 0) email1Field.setText(contatto.getEmail().toArray()[0].toString());
            if (contatto.getEmail().size() > 1) email2Field.setText(contatto.getEmail().toArray()[1].toString());
            if (contatto.getEmail().size() > 2) email3Field.setText(contatto.getEmail().toArray()[2].toString());
        }
    }

    @FXML
    private void annullaAzione(ActionEvent event) {
        mainViewController.loadView1(null);//gestire
    }

    @FXML
    private void confermaAzione(ActionEvent event) {
        
        /*Set <Integer> numeri = new HashSet<>();
        numeri.add(Integer.parseInt(numero1Field.getText()));
        numeri.add(Integer.parseInt(numero2Field.getText()));
        numeri.add(Integer.parseInt(numero3Field.getText()));
        
        Set <String> email = new HashSet<>();
        email.add(email1Field.getText());
        email.add(email2Field.getText());
        email.add(email3Field.getText());*/
        
        Contatto c = new Contatto(nomeField.getText(), cognomeField.getText(), null, null, preferitoCheck.isSelected());
        
        if(rubrica.aggiungiContatto(c))
            showAlert("Dati sbagliati", "I dati inseriti non sono compatibili");
        
        mainViewController.loadView1(c);
        
    }

    @FXML
    private void switchPreferito(ActionEvent event) {
        
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
}
