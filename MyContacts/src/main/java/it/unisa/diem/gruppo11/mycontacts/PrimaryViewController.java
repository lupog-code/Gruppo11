/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo11.mycontacts;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

/**
 * FXML Controller class
 *
 * @author lupo
 */
public class PrimaryViewController implements Initializable {

    @FXML
    private Menu aggiungiBar;
    @FXML
    private Menu importaBar;
    @FXML
    private Menu esportaBar;
    @FXML
    private Menu resetBar;
    @FXML
    private TextField searchField;
    @FXML
    private ToggleButton preferitiToggle;
    @FXML
    private TableColumn<?, ?> nomeColonna;
    @FXML
    private TableColumn<?, ?> cognomeColonna;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
