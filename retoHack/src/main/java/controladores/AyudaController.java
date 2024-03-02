/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import metodos.Metodos;

/**
 * FXML Controller class
 *
 * @author aleja
 */
public class AyudaController implements Initializable {

    @FXML
    private Button volver;
    @FXML
    private Button exit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void volver(ActionEvent event) {
    
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Metodos m = new Metodos();

        Stage newStage = m.cargarVentana("/ventanas/Inicio.fxml");
        newStage.show();

        stage.close();
    }

    @FXML
    private void exit(ActionEvent event) {
        
        Platform.exit();
    }
    
    
}
