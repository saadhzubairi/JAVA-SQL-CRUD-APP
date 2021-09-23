package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main_controller implements Initializable {
    public Button SUPBT;
    public Button PRODBT;
    public Button SUPPSSSBT;
    public Button PURCHBT;
    public Button POBT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SUPBT.setOnAction(actionEvent -> {
            try {
                launchWindows("SUPPLIER.fxml","SUPPLIER");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        PRODBT.setOnAction(actionEvent -> {
            try {
                launchWindows("PRODUCT.fxml","PRODUCT");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        SUPPSSSBT.setOnAction(actionEvent -> {
            try {
                launchWindows("SUPPLIES.fxml","Supplies");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        PURCHBT.setOnAction(actionEvent -> {
            try {
                launchWindows("PO.fxml","Purchase Orders");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void launchWindows(String fxmlname, String name) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("/sample/"+fxmlname));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle(name);
        stage.setScene(new Scene(root1));
        stage.show();
    }

}
