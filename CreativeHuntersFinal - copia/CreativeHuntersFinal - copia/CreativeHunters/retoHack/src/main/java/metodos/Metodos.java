/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodos;


import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author aleja
 */
public class Metodos {
   
     private double xOffset = 0;
    private double yOffset = 0;
    

    
    public Stage cargarVentana(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage newStage = new Stage();

            newStage.setScene(scene);
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.setResizable(false);
            
            // Agregar evento de mouse para arrastrar la ventana
            scene.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            scene.setOnMouseDragged(event -> {
                newStage.setX(event.getScreenX() - xOffset);
                newStage.setY(event.getScreenY() - yOffset);
            });

            return newStage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
 
    
     public void loadPage(String page,Pane bp) {
     
         
         try {
             // Cargamos el nuevo AnchorPane desde el archivo FXML
             FXMLLoader loader = new FXMLLoader(getClass().getResource(page + ".fxml"));
             AnchorPane newAnchorPane = loader.load();
             
             // Limpiamos el Pane principal y agregamos el nuevo AnchorPane
             
             bp.getChildren().add(newAnchorPane);
             
         } catch (IOException ex) {
             ex.printStackTrace();
         }           
    }

}

