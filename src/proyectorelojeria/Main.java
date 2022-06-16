package proyectorelojeria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Tobian
 */
public class Main extends Application{

    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/VentanaPrincipal.fxml"));        
        Scene scene = new Scene(root);
        Image image =  new Image("/Img/icono-tiempo.png");
        stage.getIcons().add(image);
        stage.setTitle("PROYECTO PORTAFOLIO");
        stage.setScene(scene);
        stage.show();
    }
    
}
