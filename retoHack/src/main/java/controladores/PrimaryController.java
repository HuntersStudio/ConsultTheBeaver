package controladores;

import creativehunters.creativehunters.App;
import java.io.IOException;
import javafx.fxml.FXML;
import java.net.Socket;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import metodos.Metodos;

public class PrimaryController {

    @FXML
    private Button switchToSecondary;
    @FXML
    private Button exit;
    @FXML
    private Button ayuda;

    private void cambiarVentana(MouseEvent event) {

    }

    @FXML
    private void exit(ActionEvent event) {
       
        
     /*   try {
            
            App.socket.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       */
        Platform.exit();
        
    }

   

    @FXML
    private void ayuda(ActionEvent event) {
       
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Metodos m = new Metodos();

        Stage newStage = m.cargarVentana("/ventanas/ayuda.fxml");
        newStage.show();

        stage.close();
    }

    @FXML
    private void zoomDown(MouseEvent event) {
        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), ayuda);
        scaleOut.setFromX(1.3);
        scaleOut.setFromY(1.3);
        scaleOut.setToX(1);
        scaleOut.setToY(1);
        
         scaleOut.playFromStart();
    }

    @FXML
    private void zoomUp(MouseEvent event) {
         ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), ayuda);
        scaleIn.setFromX(1);
        scaleIn.setFromY(1);
        scaleIn.setToX(1.3);
        scaleIn.setToY(1.3);

        scaleIn.playFromStart();
    }

    @FXML
    private void zoom_b(MouseEvent event) {
         ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), switchToSecondary);
        scaleOut.setFromX(1.3);
        scaleOut.setFromY(1.3);
        scaleOut.setToX(1);
        scaleOut.setToY(1);
        
         scaleOut.playFromStart();
    }

    @FXML
    private void zoom_a(MouseEvent event) {
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), switchToSecondary);
        scaleIn.setFromX(1);
        scaleIn.setFromY(1);
        scaleIn.setToX(1.3);
        scaleIn.setToY(1.3);

        scaleIn.playFromStart();
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
    private void cambiarVentana(ActionEvent event) {
        
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Metodos m = new Metodos();

        Stage newStage = m.cargarVentana("/ventanas/secondary.fxml");
        newStage.show();

        stage.close();
    }
}
