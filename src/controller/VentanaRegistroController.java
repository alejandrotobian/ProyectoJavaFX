package controller;

import conexion.ConexionMysql;
import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 *
 * @author Tobi
 */
public class VentanaRegistroController implements Initializable {
        
    @FXML private GridPane grindPaneButton;    
    @FXML private GridPane GrindPane;

    @FXML private Label LabelPrincipal;

    @FXML private TextField TxtApellido;
    @FXML private TextField TxtNombre;
    @FXML private TextField TxtNumeroId;
    @FXML private TextField TxtUsuario;
    @FXML private PasswordField TxtPassword;
    
    @FXML private Button btnGuardar;
    @FXML private Button btnLimpiar;    
    @FXML private Button BtnAtras;
    
    ConexionMysql conexion = new ConexionMysql();
    Connection con = conexion.getConnection(); 

 @FXML
 public void BtnGuardar(ActionEvent event) {
     
        String password = String.valueOf(TxtPassword.getText());
        
        String sql = "INSERT INTO `basedato_relojeria`.`tbl_usuarios` "
                + "(`nombre`, `apellido`, `id_numeroId`, `usuario`, password ) "
                + "VALUES (?, ?, ?, ?,?)";
        try{
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, TxtNombre.getText());
            pst.setString(2, TxtApellido.getText());            
            pst.setString(3, TxtNumeroId.getText());
            pst.setString(4, TxtUsuario.getText());
            pst.setString(5, password);
            
            pst.executeUpdate();           
            
           JOptionPane.showMessageDialog(null,"Registro exitoso");
           System.out.println("Registro exitoso");
           
           TxtNombre.setText("");
           TxtApellido.setText("");
           TxtNumeroId.setText("");
           TxtUsuario.setText("");
           TxtPassword.setText("");
           
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanaSecundaria.fxml"));
           VentanaPrincipalController control = loader.getController();
           Parent root = loader.load();        
           Stage stage = new Stage();
           Scene scene = new Scene(root);
           stage.setScene(scene);
           Image image =  new Image("/Img/icono-tiempo.png");
           stage.getIcons().add(image);
           stage.show();
           stage.setTitle("PROYECTO PORTAFOLIO");
           Stage myStage = (Stage) BtnAtras.getScene().getWindow();
           myStage.close();         
           
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Fallo en registro");
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    public void BtnLimpiarcampos(ActionEvent event) {
        
        TxtNombre.setText("");
        TxtApellido.setText("");
        TxtNumeroId.setText("");
        TxtUsuario.setText("");
        TxtPassword.setText("");
        
    }    
    
    @FXML
    public void BtnAtrasVentanaPrincipal(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanaPrincipal.fxml"));
        VentanaPrincipalController control = loader.getController();
        Parent root = loader.load();        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Image image =  new Image("/Img/icono-tiempo.png");
        stage.getIcons().add(image);
        stage.show();
        stage.setTitle("PROYECTO PORTAFOLIO");
        Stage myStage = (Stage) BtnAtras.getScene().getWindow();
        myStage.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        Duration duration = Duration.seconds(1); 
        
        BtnAtras.translateYProperty();        
        TranslateTransition transition = new TranslateTransition(duration, BtnAtras);
        transition.setByY(-400);
        transition.play();                       
        RotateTransition rotateTransition = new RotateTransition(duration, BtnAtras); 
        rotateTransition.setByAngle(360);
        rotateTransition.play();
                           
        GrindPane.translateXProperty();
        TranslateTransition translateX = new TranslateTransition( duration,GrindPane);
        translateX.setByX(680);
        translateX.play();
        
        LabelPrincipal.translateXProperty();
        TranslateTransition labelX = new TranslateTransition(duration,LabelPrincipal);
        labelX.setByX(420);
        labelX.play();
        
        grindPaneButton.translateXProperty();
        TranslateTransition translateGrind = new TranslateTransition( duration,grindPaneButton);
        translateGrind.setByX(680);
        translateGrind.play();
 
  }
    }
    

