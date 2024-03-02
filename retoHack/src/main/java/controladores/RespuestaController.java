/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import creativehunters.creativehunters.App;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import metodos.Metodos;
/**
 * FXML Controller class
 *
 * @author aleja
 */
public class RespuestaController implements Initializable {


    @FXML
    private AnchorPane img2;
    @FXML
    private TextArea textoRespuesta;
    @FXML
    private Button exit;
    @FXML
    private Button volver;
    /**
     * Initializes the controller class.
     */
     private String textToLoad;
    private int currentIndex = 0;
    private Timeline timeline;
    
    @Override
public void initialize(URL url, ResourceBundle rb) {
    try {
        // Establecer el texto a cargar poco a poco
        DataInputStream dis = new DataInputStream(App.socket.getInputStream());
        textToLoad = dis.readUTF(); // Asigna el texto recibido a textToLoad
        App.socket.close();

        // Configurar el Timeline
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.01), event -> {
            if (currentIndex <= textToLoad.length()) {
                textoRespuesta.setText(textToLoad.substring(0, currentIndex));
                currentIndex++;
            } else {
                timeline.stop(); // Detener el Timeline cuando se carga todo el texto
            }
        }));
        timeline.setCycleCount(textToLoad.length() + 1); // Duración total del Timeline

        // Iniciar el Timeline
        timeline.play();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}
    /*
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            DataInputStream dis = new DataInputStream(App.socket.getInputStream());
            
            String t = dis.readUTF();
            
            textoRespuesta.setText(t);
            
                    
            App.socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }    */

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void volver(ActionEvent event) {
        
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Metodos m = new Metodos();

        Stage newStage = m.cargarVentana("/ventanas/secondary.fxml");
        newStage.show();

        stage.close();
    }

    @FXML
    private void zoomDownExit(MouseEvent event) {
        
         ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), exit);
        scaleOut.setFromX(1.3);
        scaleOut.setFromY(1.3);
        scaleOut.setToX(1);
        scaleOut.setToY(1);
        
         scaleOut.playFromStart();
    }

    @FXML
    private void zoomUpExit(MouseEvent event) {
         ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), exit);
        scaleIn.setFromX(1);
        scaleIn.setFromY(1);
        scaleIn.setToX(1.3);
        scaleIn.setToY(1.3);

        scaleIn.playFromStart();
    }

    @FXML
    private void zoomDownVolver(MouseEvent event) {
        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), volver);
        scaleOut.setFromX(1.3);
        scaleOut.setFromY(1.3);
        scaleOut.setToX(1);
        scaleOut.setToY(1);
        
         scaleOut.playFromStart();
        
    }

    @FXML
    private void zoomUpVolver(MouseEvent event) {
         ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), volver);
        scaleIn.setFromX(1);
        scaleIn.setFromY(1);
        scaleIn.setToX(1.3);
        scaleIn.setToY(1.3);

        scaleIn.playFromStart();
    }
    
}
