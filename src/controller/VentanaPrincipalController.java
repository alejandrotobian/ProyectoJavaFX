package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import conexion.ConexionMysql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class VentanaPrincipalController implements Initializable {
    
    @FXML private AnchorPane AnchorPane;
    
    @FXML private Label LabelRelojerua;    
    
    @FXML private GridPane grindPaneInicio;    
    @FXML private GridPane gridPaneButton;
    
    @FXML private Button BtnIngresar;
    @FXML private Button BtnRecuperacion;
    @FXML private Button BtnRegistro;

    @FXML private PasswordField Textfiel_password;
    @FXML private TextField Textfiel_usuario;

    @FXML private ImageView imgIcono;
    
    ConexionMysql conexion = new ConexionMysql();
    Connection con = conexion.getConnection(); 
    
    @FXML
    public void BtnIngresar(ActionEvent event) throws IOException, SQLException {       
        
        int resultado = 0;  
        
        try {            
           String usuario = Textfiel_usuario.getText();
           String password = String.valueOf(Textfiel_password.getText());
           String sql = "SELECT * FROM basedato_relojeria.tbl_usuarios WHERE usuario = '"+usuario+"'"
                   + " AND password = '"+password+"' ";
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery(sql);
           
           if(rs.next()){
               
               resultado = 1;               
               
               if(resultado==1){                   
                   
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanaSecundaria.fxml"));
                   VentanaSecundariaController control = loader.getController();
                   Parent root = loader.load();                   
                   Stage stage = new Stage();
                   Scene scene = new Scene(root);
                   stage.setScene(scene);
                   stage.show();
                   Image image =  new Image("/Img/icono-tiempo.png");
                   stage.getIcons().add(image);
                   stage.setTitle("LARELOJERIA_MED");
                   Stage myStage = (Stage) BtnIngresar.getScene().getWindow();
                   myStage.close();
                }
               else{
                   System.out.println("Error al ingresar, por favor verifique los datos");
                   conexion.getConnection();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println("Error al ingresar, por favor verifique los datos");
    }    
     
    }
    
    @FXML
    public void BtnRecuperarCuenta(ActionEvent event) throws IOException, SQLException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanaRecuperar.fxml"));
         VentanaRecuperarController control = loader.getController();
         Parent root = loader.load();                   
         Stage stage = new Stage();
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
         Image image =  new Image("/Img/icono-tiempo.png");
         stage.getIcons().add(image);
         stage.setTitle("LARELOJERIA_MED");
         Stage myStage = (Stage) BtnIngresar.getScene().getWindow();
         myStage.close();        
    }
    
    @FXML
    void BtnRegistroEvent(ActionEvent event) throws IOException, SQLException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanaRegistro.fxml"));
         VentanaRegistroController control = loader.getController();
         Parent root = loader.load();                   
         Stage stage = new Stage();
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
         Image image =  new Image("/Img/icono-tiempo.png");
         stage.getIcons().add(image);
         stage.setTitle("LARELOJERIA_MED");
         Stage myStage = (Stage) BtnIngresar.getScene().getWindow();
         myStage.close();  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Duration duration = Duration.seconds(1);
        
        grindPaneInicio.translateXProperty();
        TranslateTransition translateX = new TranslateTransition( duration,grindPaneInicio);
        translateX.setByX(400);
        translateX.play();
        
        gridPaneButton.translateXProperty();
        TranslateTransition transX = new TranslateTransition( duration,gridPaneButton);
        transX.setByX(405);
        transX.play();
        
        BtnRegistro.translateXProperty();
        TranslateTransition translaX = new TranslateTransition( duration,BtnRegistro);
        translaX.setByX(190);
        translaX.play();
        
        LabelRelojerua.translateXProperty();
        TranslateTransition labelRelojeria = new TranslateTransition(duration,LabelRelojerua);
        labelRelojeria.setByX(310);
        labelRelojeria.play();
        
        imgIcono.translateXProperty();
        TranslateTransition imageIcono = new TranslateTransition(duration,imgIcono);
        imageIcono.setByX(300);
        imageIcono.play();
    }
}
