/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo11.mycontacts;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
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
public class LeftViewController implements Initializable {

    @FXML
    private Menu addMB;
    @FXML
    private Menu importMB;
    @FXML
    private Menu exportMB;
    @FXML
    private Menu resetMB;
    @FXML
    private TextField searchField;
    @FXML
    private ToggleButton prefToggle;
    @FXML
    private TableColumn<?, ?> nomeColumn;
    @FXML
    private TableColumn<?, ?> cognomeColumn;
    
    private Consumer<String> onViewChangeRequested;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void setOnViewChangeRequested(Consumer<String> handler) {
        this.onViewChangeRequested = handler;
    }  

    @FXML
    private void addContatto(ActionEvent event) {
    }

    @FXML
    private void importRubrica(ActionEvent event) {
        if (onViewChangeRequested != null) {
            onViewChangeRequested.accept("RightView1.fxml");
        }
    }

    @FXML
    private void exportRubrica(ActionEvent event) {
        if (onViewChangeRequested != null) {
            onViewChangeRequested.accept("RightView2.fxml");
        }
    }

    @FXML
    private void resetRubrica(ActionEvent event) {
    }

    @FXML
    private void ricercaContatti(ActionEvent event) {
    }

    @FXML
    private void visualizzaPreferiti(ActionEvent event) {
    }
    
}
