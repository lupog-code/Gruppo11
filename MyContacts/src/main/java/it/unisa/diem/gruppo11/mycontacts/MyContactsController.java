package it.unisa.diem.gruppo11.mycontacts;

import java.io.IOException;
import javafx.fxml.FXML;

public class MyContactsController {

    @FXML
    private void switchToSecondary() throws IOException {
        MyContacts.setRoot("secondary");
    }
}
