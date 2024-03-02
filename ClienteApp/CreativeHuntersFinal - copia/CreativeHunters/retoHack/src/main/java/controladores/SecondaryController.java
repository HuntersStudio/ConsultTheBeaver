package controladores;

import creativehunters.creativehunters.App;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javafx.fxml.FXML;
import java.net.Socket;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
    private TextField ubicacion;
    @FXML
    private TextField personas;
    @FXML
    private ComboBox<String> actividad;
    @FXML
    private ComboBox<String> estacion;
    @FXML
    private Label error;

    public void initialize() {
        // TODO       
        ObservableList<String> listaactividad = FXCollections.observableArrayList("Acampada", "Barbacoa", "Senderismo");
        actividad.setItems(listaactividad);

        ObservableList<String> listaestacion = FXCollections.observableArrayList("Primaver", "Verano", "Otoño", "Invierno");
        estacion.setItems(listaestacion);

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
            
            if( !ubicacion.getText().isBlank()  && !personas.getText().isBlank() && actividad.getValue()!= null && estacion.getValue()!= null){   
            
            String texto = "";
            
            if(actividad.getValue()!= null){    
                texto = texto +"La actividad que voy a hacer es "+actividad.getValue();
            }
            if(!ubicacion.getText().isBlank()){
                texto = texto + " en "+ubicacion.getText();
            }
            if(estacion.getValue()!= null){
                texto= texto + " en la estacion de "+estacion.getValue();
            }
            if(!personas.getText().isBlank()){
                texto = texto + " somos "+personas.getText()+" personas.";
            }
            
            dos.writeInt(1);
            dos.writeUTF(texto);
            
              
            ap.setVisible(false);
            m.loadPage("/ventanas/respuesta", bp);
            
            }else{    
             error.setText("Faltan campos por rellenar");  
           
            }
            

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                dis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

}
