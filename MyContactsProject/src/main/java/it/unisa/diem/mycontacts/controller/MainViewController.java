/**
 * @file MainViewController.java
 * @brief Controller principale per gestire la vista principale dell'applicazione.
 * Gestisce il caricamento dinamico delle viste a sinistra (leftPane) e a destra (rightPane).
 */

package it.unisa.diem.mycontacts.controller;

import it.unisa.diem.mycontacts.data.Contatto;
import it.unisa.diem.mycontacts.datastructure.Rubrica;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @class MainViewController
 * @brief Classe controller per la gestione della vista principale dell'applicazione.
 */
public class MainViewController implements Initializable {

    /**
     * @brief Pannello per il contenuto della vista sinistra.
     */
    @FXML
    private StackPane leftPane;

    /**
     * @brief Pannello per il contenuto della vista destra.
     */
    @FXML
    private StackPane rightPane;

    /**
     * @brief Rubrica per la gestione dei contatti.
     */
    private Rubrica rubrica;

    /**
     * @brief Metodo di inizializzazione del controller.
     * 
     * @param url URL della risorsa.
     * @param rb ResourceBundle associato.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rubrica = new Rubrica();

        try {
            // Carica il layout per il pannello sinistro
            FXMLLoader view = new FXMLLoader(getClass().getResource("../view/leftView.fxml"));
            leftPane.getChildren().clear();
            leftPane.getChildren().add(view.load());

            // Imposta il controller per il pannello sinistro
            LeftViewController leftPanelController = view.getController();
            leftPanelController.setMainViewController(this);

        } catch (IOException e) {
            Logger.getLogger(MainViewController.class.getName())
                  .severe("Errore durante il caricamento di leftView: " + e.getMessage());
        }
    }

    /**
     * @brief Restituisce la rubrica associata al controller.
     * 
     * @return L'istanza della rubrica.
     */
    public Rubrica getRubrica() {
        return rubrica;
    }

    /**
     * @brief Carica una vista specifica nel pannello destro.
     * 
     * @param fxmlFileName Nome del file FXML da caricare.
     * @param contatto Oggetto Contatto da passare al controller della vista.
     */
    private void loadView(String fxmlFileName, Contatto contatto) {
        try {
            FXMLLoader view = new FXMLLoader(getClass().getResource("../view/" + fxmlFileName));
            VBox root = view.load();
            rightPane.getChildren().clear();
            rightPane.getChildren().add(root);

            // Configura il controller in base al file FXML
            if (fxmlFileName.equals("rightView1.fxml")) {
                RightView1Controller ctrl = view.getController();
                ctrl.setMainViewController(this);
                ctrl.setContatto(contatto);
            } else if (fxmlFileName.equals("rightView2.fxml")) {
                RightView2Controller ctrl = view.getController();
                ctrl.setMainViewController(this);
                ctrl.setContatto(contatto);
            } else {
                throw new IOException("File non trovato");
            }

        } catch (IOException e) {
            Logger.getLogger(MainViewController.class.getName())
                  .severe("Errore durante il caricamento di " + fxmlFileName + ": " + e.getMessage());
        }
    }

    /**
     * @brief Carica la vista "rightView1.fxml" nel pannello destro.
     * 
     * @param contatto Oggetto Contatto da passare al controller della vista.
     */
    public void loadView1(Contatto contatto) {
        loadView("rightView1.fxml", contatto);
    }

    /**
     * @brief Carica la vista "rightView2.fxml" nel pannello destro.
     * 
     * @param contatto Oggetto Contatto da passare al controller della vista.
     */
    public void loadView2(Contatto contatto) {
        loadView("rightView2.fxml", contatto);
    }
}
