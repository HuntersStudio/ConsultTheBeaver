package creativehunters.creativehunters;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import metodos.Metodos;

/**
 * JavaFX App
 */
public class App extends Application {

    public static String servidor = "127.0.0.1";
    public static int puerto= 8888;
     public static Socket socket;
    
       @Override
    public void start(Stage stage) throws IOException {
        Metodos m =new Metodos();
        
        Stage newStage = m.cargarVentana("/ventanas/Inicio.fxml");
                    newStage.show();
                    newStage.setResizable(false);

                    stage.close();
    }
    
    public static void main(String[] args) {
        launch();
    }

}