package controladores;

import creativehunters.creativehunters.App;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javafx.fxml.FXML;
import java.net.Socket;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import metodos.Metodos;

public class SecondaryController {

    @FXML
    private Button volver;
    @FXML
    private Button exit;
    @FXML
    private Pane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button panel2;
    @FXML
    private TextArea ubicacion;
    @FXML
    private TextField personas;

    @FXML
    private Label error;
    private RadioButton actividad1;
    private RadioButton actividad2;
    @FXML
    private RadioButton estacion2;
    @FXML
    private RadioButton estacion1;
    private RadioButton actividad3;
    @FXML
    private RadioButton estacion3;
    @FXML
    private RadioButton estacion4;
    private ToggleGroup grupoEstaciones = new ToggleGroup();
    @FXML
    private TextArea actividad;
    
    public void initialize() {
      
       
        
        estacion1.setToggleGroup(grupoEstaciones);
        estacion2.setToggleGroup(grupoEstaciones);
        estacion3.setToggleGroup(grupoEstaciones);
        estacion4.setToggleGroup(grupoEstaciones);
    }

    @FXML
    private void volver(MouseEvent event) {

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

    
    @FXML
private void cambiarPanel(ActionEvent event) {
    DataInputStream dis = null;
    
    try {
        App.socket = new Socket(App.servidor, App.puerto);
    } catch (IOException ex) {
        ex.printStackTrace();
    }
     
    try {
        Metodos m = new Metodos();
        dis = new DataInputStream(App.socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(App.socket.getOutputStream());

        if (!ubicacion.getText().isBlank() && !personas.getText().isBlank() && !actividad.getText().isBlank() && grupoEstaciones.getSelectedToggle() != null) {
            try {
                // Convertimos el texto del campo personas a un entero
                int cantidadPersonas = Integer.parseInt(personas.getText());

                // Si la conversión tiene éxito, continuamos con el resto del proceso
                dos.writeInt(1);   
            
                String texto = "";
            
                if (!actividad.getText().isBlank()) {
                    
                    texto = texto + "La actividad que voy a hacer es " + actividad.getText();
                }
                if (!ubicacion.getText().isBlank()) {
                    texto = texto + " en " + ubicacion.getText();
                }
                if (grupoEstaciones.getSelectedToggle() != null) {
                    RadioButton selectedRadioButton = (RadioButton) grupoEstaciones.getSelectedToggle();
                    texto = texto + " en la estacion de " + selectedRadioButton.getText();
                }
           
                texto = texto + " somos " + cantidadPersonas + " personas.";

                dos.writeUTF(texto);
            
                ap.setVisible(false);
                m.loadPage("/ventanas/respuesta", bp);
                
                dos.close();
            } catch (NumberFormatException e) {
                // El texto ingresado en el campo personas no es un número válido
                error.setText("El campo 'personas' debe ser un número válido.");
            }
        } else { 
            error.setText("Faltan campos por rellenar");  
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (dis != null) {
                dis.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
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
         ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), exit);
        scaleOut.setFromX(1);
        scaleOut.setFromY(1);
        scaleOut.setToX(1.3);
        scaleOut.setToY(1.3);
        
         scaleOut.playFromStart();
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
         ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), volver);
        scaleOut.setFromX(1);
        scaleOut.setFromY(1);
        scaleOut.setToX(1.3);
        scaleOut.setToY(1.3);
        
         scaleOut.playFromStart();
    }

    @FXML
    private void zoomDownEnviar(MouseEvent event) {
          ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), panel2);
        scaleOut.setFromX(1.3);
        scaleOut.setFromY(1.3);
        scaleOut.setToX(1);
        scaleOut.setToY(1);
        
         scaleOut.playFromStart();
    }

    @FXML
    private void zoomUpEnviar(MouseEvent event) {
         ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), panel2);
        scaleOut.setFromX(1);
        scaleOut.setFromY(1);
        scaleOut.setToX(1.3);
        scaleOut.setToY(1.3);
        
         scaleOut.playFromStart();
    }


}
