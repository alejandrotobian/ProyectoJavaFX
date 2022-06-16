package controller;


import conexion.ConexionMysql;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Tobi
 */
public class VentanaRecuperarController implements Initializable {
    
    @FXML private Label LabelRecuperar; 

    @FXML private Button btnAtras;
    @FXML private Button btnGuardar;
    @FXML private Button BtnBuscar;

    @FXML private GridPane grindPaneRecuperacion;
    @FXML private Pane paneRecupar;

    @FXML private TextField txtNombre;
    @FXML private TextField txtNumeroId;
    
    @FXML private PasswordField nuevaPassword;
    @FXML private PasswordField nuevaPasswordDos;
    
    ConexionMysql conexion = new ConexionMysql();
    Connection con = conexion.getConnection();
    
    Duration duration = Duration.seconds(1);

    @FXML
    void BtnAtrasVentanaPrincipal(ActionEvent event) throws IOException {
        
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
        Stage myStage = (Stage) btnAtras.getScene().getWindow();
        myStage.close();

    }

    @FXML
    void BtnGuardarCambios(ActionEvent event) throws SQLException {       
         
            String password = String.valueOf(nuevaPassword.getText());
            String passwordDos = String.valueOf(nuevaPasswordDos.getText());
            
            
            String sql = "UPDATE basedato_relojeria.tbl_usuarios SET "
            + "password = '" + password + "' WHERE( id_numeroId` = '" + txtNumeroId.getText() + "') ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            JOptionPane.showMessageDialog(null, "Cambio de contraseña exitoso");
           
            JOptionPane.showMessageDialog(null, "Las contraseñas ingresadas no coinciden");
                        
            nuevaPassword.setText("");
            nuevaPasswordDos.setText("");
    }
          
    
    @FXML
    void BtnBuscar(ActionEvent event) {
 
        int resultado = 0;  
        
        try {            
           String nombre = txtNombre.getText();           
           String numeroId = txtNumeroId.getText();
           String sql = "SELECT * FROM basedato_relojeria.tbl_usuarios WHERE nombre = '"+nombre+"'"
                   + " AND id_numeroId = '"+numeroId+"' ";
           
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery(sql);
           
           if(rs.next()){
               
               resultado = 1;               
               
               if(resultado==1){                   
                   System.out.println("Busqueda Exitosa");
                   
                   BtnBuscar.setVisible(false);
                   
                   grindPaneRecuperacion.translateYProperty();        
                   TranslateTransition transition = new TranslateTransition
                   (duration, grindPaneRecuperacion);
                   transition.setByY(-70);
                   transition.play(); 
                   
                   paneRecupar.translateXProperty();
                   TranslateTransition translateX = new TranslateTransition
                   ( duration,paneRecupar);
                   translateX.setByX(680);
                   translateX.play();
                }
               else{
                   System.out.println("Error buscar datos");
                   conexion.getConnection();                   
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            JOptionPane.showMessageDialog(null, "No se encontro el usuario");
    }    
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {                 
              
        btnAtras.translateYProperty();        
        TranslateTransition transition = new TranslateTransition(duration, btnAtras);
        transition.setByY(-400);
        transition.play();                       
        RotateTransition rotateTransition = new RotateTransition(duration, btnAtras); 
        rotateTransition.setByAngle(360);
        rotateTransition.play();
                           
        grindPaneRecuperacion.translateXProperty();
        TranslateTransition translateX = new TranslateTransition( duration,grindPaneRecuperacion);
        translateX.setByX(680);
        translateX.play();
        
        LabelRecuperar.translateXProperty();
        TranslateTransition labelX = new TranslateTransition(duration,LabelRecuperar);
        labelX.setByX(410);
        labelX.play();
        
        btnGuardar.translateXProperty();
        TranslateTransition translateGrind = new TranslateTransition( duration,btnGuardar);
        translateGrind.setByX(680);
        translateGrind.play();
        
        BtnBuscar.translateXProperty();
        TranslateTransition translatebuscar= new TranslateTransition( duration,BtnBuscar);
        translatebuscar.setByX(690);
        translatebuscar.play();
    }
    
}
