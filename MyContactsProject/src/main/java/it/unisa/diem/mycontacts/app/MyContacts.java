package it.unisa.diem.mycontacts.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @class MyContacts
 * @brief La classe MyContacts è l'entry point dell'applicazione JavaFX per la gestione dei contatti.
 *        Configura e avvia la finestra principale dell'applicazione.
 */
public class MyContacts extends Application {

    // La scena principale dell'applicazione.
    private static Scene scene;

    /**
     * @brief Metodo che avvia l'applicazione e imposta la finestra principale.
     *        Carica il file FXML e configura la scena.
     * 
     * @param stage La finestra principale dell'applicazione (Stage).
     * @throws IOException Se non riesce a caricare il file FXML, viene lanciata un'eccezione.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Carica il file FXML per la vista principale e imposta la scena.
        scene = new Scene(loadFXML("../view/mainView"), 1000, 600);
        stage.setScene(scene); // Imposta la scena sulla finestra.
        stage.setMinHeight(500); // Imposta l'altezza minima della finestra.
        stage.setMinWidth(800); // Imposta la larghezza minima della finestra.
        stage.setTitle("MyContacts"); // Imposta il titolo della finestra.
        stage.show(); // Mostra la finestra.
    }

    /**
     * @brief Carica un file FXML dato il nome del file.
     * 
     * @param fxml Il nome del file FXML da caricare.
     * @return Un oggetto Parent che rappresenta il contenuto della vista.
     * @throws IOException Se il file FXML non può essere caricato, viene lanciata un'eccezione.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MyContacts.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load(); // Carica e restituisce il contenuto FXML.
    }

    /**
     * @brief Metodo principale per avviare l'applicazione JavaFX.
     *        Chiamato all'avvio del programma per lanciare l'interfaccia grafica.
     * 
     * @param args Argomenti passati all'applicazione (non utilizzati in questa classe).
     */
    public static void main(String[] args) {
        launch(); // Avvia l'applicazione JavaFX.
    }
}
