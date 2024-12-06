/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo11.mycontacts;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

/**
 * FXML Controller class
 *
 * @author lupo
 */
public class LeftViewController implements Initializable {

    @FXML
    private Menu addButton;
    @FXML
    private Menu importButton;
    @FXML
    private Menu exportButton;
    @FXML
    private TextField searchField;
    @FXML
    private ToggleButton prefToggle;
    @FXML
    private TableView<?> contattiTable;
    @FXML
    private TableColumn<?, ?> nomeColumn;
    @FXML
    private TableColumn<?, ?> cognomeColumn;
    
    private MainViewController mainViewController;

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
    }

    @FXML
    private void loadView1() {
        if (mainViewController != null) {
            mainViewController.loadView1();
        }else System.err.print("Errore caricamento");
    }
    
}
