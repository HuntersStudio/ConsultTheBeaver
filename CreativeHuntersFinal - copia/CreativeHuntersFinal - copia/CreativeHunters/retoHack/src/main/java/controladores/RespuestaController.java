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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;


import javafx.scene.layout.AnchorPane;
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
    /**
     * Initializes the controller class.
     */
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
    }    
    
}
