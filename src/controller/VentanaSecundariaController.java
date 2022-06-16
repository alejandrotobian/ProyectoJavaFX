package controller;

import conexion.ConexionMysql;
import constructor.CalidadRelojConstructor;
import constructor.CaracteristicaAlarmaConstructor;
import constructor.CaracteristicaCalendarioConstructor;
import constructor.CaracteristicaCronometroConstructor;
import constructor.CaracteristicaHoraConstructor;
import constructor.CaracteristicaIluminacionConstructor;
import constructor.CaracteristicaSumergibleConstructor;
import constructor.GuardarImgConstructor;
import constructor.MarcaRelojConstructor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 *
 * @author Tobi
 */
public class VentanaSecundariaController implements Initializable{
    
    @FXML private Button btnInicio;
    @FXML private Button btnPromedio;
    @FXML private Button btnRelojes;
    @FXML private Button btnSalir;
    @FXML private Button btnUsuarios;
    @FXML private Button btnGuardarImg;
    @FXML private Button btnBuscarImg;
    @FXML private Button btnNuevoImg;
    @FXML private Button btnBuscarNumeroIdReloj;
    @FXML private Button btnNuevaVenta;
   
    private FileInputStream fis;
    
    
    @FXML private Pane paneLateral;
    @FXML private Pane paneInicio;
    @FXML private Pane panePromedio;
    @FXML private Pane paneRelojes;
    @FXML private Pane paneImgReloj;
    @FXML private Pane paneImg;
    
    
    
    
    @FXML private TextField txtRutaImg;
    @FXML private TextField txtNombreReloj;
    @FXML private TextField txtValorProducto;
    @FXML private TextField txtValorCliente;
    @FXML private TextField txtNumeroIdReloj;
    @FXML private TextField txtNombreCliente;
    @FXML private TextField txtNumeroContacto;
    @FXML private TextField txtDireccionEnvio;
    
    @FXML private ImageView imgReloj;
    @FXML private ImageView imgBuscarReloj;
    
//    Combobox 
    
    @FXML private ComboBox<MarcaRelojConstructor> cmbMarcaReloj;
    private ObservableList<MarcaRelojConstructor> listaCmbMarcaR;
    
    @FXML private ComboBox<CalidadRelojConstructor> cmbCalidadProducto;
    private ObservableList<CalidadRelojConstructor> listaCmbCalidadR;
    
    @FXML private ComboBox<CaracteristicaHoraConstructor> cmbHora;
    private ObservableList<CaracteristicaHoraConstructor> listaCmbH;
    
    @FXML private ComboBox<CaracteristicaAlarmaConstructor> cmbAlarma;
    private ObservableList<CaracteristicaAlarmaConstructor> listaCmbAlar;
    
    @FXML private ComboBox<CaracteristicaIluminacionConstructor> cmbIluminacion;
    private ObservableList<CaracteristicaIluminacionConstructor> listaCmbIlum;

    @FXML private ComboBox<CaracteristicaCalendarioConstructor> cmbCalendario;
    private ObservableList<CaracteristicaCalendarioConstructor> listaCmbCalen;
    
    @FXML private ComboBox<CaracteristicaCronometroConstructor> cmbCronometro;
    private ObservableList<CaracteristicaCronometroConstructor> listaCrono;
    
    @FXML private ComboBox<CaracteristicaSumergibleConstructor> cmbSumergible;
    private ObservableList<CaracteristicaSumergibleConstructor> listaCmbSum;
    
    ConexionMysql conexion = new ConexionMysql();
    Connection con = conexion.getConnection();    
    
    
        
    @FXML
    void btnNavegarPantalla(ActionEvent event) {
        if(event.getSource()==btnInicio){
            paneInicio.toFront();
            limpiarCampos();
        }else
            if(event.getSource()==btnPromedio){
                panePromedio.toFront();
                limpiarCampos();
            }else
                if(event.getSource()==btnRelojes){
                    paneRelojes.toFront();
                    limpiarCampos();
                }

    }
    
    @FXML
    private void NuevaVenta(ActionEvent event) {
    }
    
    @FXML
    void BtnBuscarImg(ActionEvent event){
        
        FileChooser ruta = new FileChooser();
        String nombreRuta = null; 
//        
//        ruta.setInitialDirectory(new File(System.getProperty("user.home")));
//        ruta.getExtensionFilters().clear();
//        ruta.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg"));
//        File estado = ruta.showOpenDialog(null);      
//        
//        if(estado != null){
//            imgReloj.setImage(new Image(estado.toURI().toString()));
//            nombreRuta = ruta.toString();
//            txtRutaImg.setText(nombreRuta);
//        }
        ruta.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("All images","*.*"),
                new FileChooser.ExtensionFilter("JPG","*.jpg*"),
                new FileChooser.ExtensionFilter("PNG","*.png*"));
        ruta.setTitle("IMAGEN A SUBIR");
        File fl = ruta.showOpenDialog(null);
        if(fl != null){
            Image img = new Image(fl.getAbsolutePath());
            imgReloj.setImage(img);
            nombreRuta = ruta.toString();
            txtRutaImg.setText(nombreRuta);
        }
        

    }
    
    @FXML
    private void btnGuardarInf(ActionEvent event) {
        
        GuardarImgConstructor gic = null;
        byte[] by = txtRutaImg.getText().getBytes();
        gic.setImgReloj(by);        
         try {
           String sql = "INSERT INTO tbl_relojes (id_reloj, marcaReloj, calidadReloj, nombreReloj, preciaPagar,"
                   + "precioVender,imgReloj)VALUES (null,?,?,?,?,?,?)";
           PreparedStatement pst = con.prepareStatement(sql);
           
            pst.setString(1, cmbMarcaReloj.getValue().getMarcaReloj());
            pst.setString(2, cmbCalidadProducto.getValue().getCalidadReloj());
            pst.setString(3, txtNombreReloj.getText());
            pst.setString(4, txtValorProducto.getText());
            pst.setString(5, txtValorCliente.getText());
            pst.setBytes(6, gic.getImgReloj());
            pst.executeUpdate();                   
                        
           JOptionPane.showMessageDialog(null,"Registro exitoso");
           
           }catch(SQLException e){
           JOptionPane.showMessageDialog(null,"Fallo en guardar los datos");            
           System.out.println(e.getLocalizedMessage());
    }
    }
    @FXML
    private void btnNuevaInf(ActionEvent event) {
    limpiarCampos();
    }

        
    @FXML
    void btnSalirPantallaPrincipal(ActionEvent event) throws IOException {
        
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
        Stage myStage = (Stage) btnSalir.getScene().getWindow();
        myStage.close();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Duration duration = Duration.seconds(1); 
        
        paneLateral.translateXProperty();        
        TranslateTransition transitionPane = new TranslateTransition(duration, paneLateral);
        transitionPane.setByX(130);
        transitionPane.play(); 
        
        btnInicio.translateYProperty();        
        TranslateTransition transition = new TranslateTransition(duration, btnInicio);
        transition.setByY(-380);
        transition.play();
        
        btnRelojes.translateYProperty();        
        TranslateTransition transitionRelojes = new TranslateTransition(duration, btnRelojes);
        transitionRelojes.setByY(-310);
        transitionRelojes.play();
        
        btnPromedio.translateYProperty();        
        TranslateTransition transitionPromedio = new TranslateTransition(duration, btnPromedio);
        transitionPromedio.setByY(-240);
        transitionPromedio.play();
        
        btnUsuarios.translateYProperty();        
        TranslateTransition transitionUsuario = new TranslateTransition(duration, btnUsuarios);
        transitionUsuario.setByY(-170);
        transitionUsuario.play();  
        
        btnSalir.translateYProperty();        
        TranslateTransition transitionSalir = new TranslateTransition(duration, btnSalir);
        transitionSalir.setByY(-100);
        transitionSalir.play(); 
        
        
        
//        COMBOBOX 

        listaCmbMarcaR = FXCollections.observableArrayList();
        listaCmbCalidadR = FXCollections.observableArrayList();
        listaCmbH = FXCollections.observableArrayList();
        listaCmbAlar = FXCollections.observableArrayList();
        listaCmbIlum = FXCollections.observableArrayList();
        listaCmbCalen = FXCollections.observableArrayList();
        listaCrono = FXCollections.observableArrayList();
        listaCmbSum = FXCollections.observableArrayList();
        
        MarcaRelojConstructor.cmbMarcaRelojes(con, listaCmbMarcaR);
        CalidadRelojConstructor.cmbCalidadReloj(con, listaCmbCalidadR);
        CaracteristicaHoraConstructor.cmbCaracteristicaHora(con, listaCmbH);
        CaracteristicaAlarmaConstructor.CmbCaracteristicaHora(con, listaCmbAlar);
        CaracteristicaIluminacionConstructor.CmbIluminacion(con, listaCmbIlum);
        CaracteristicaCalendarioConstructor.CmbCalendario(con, listaCmbCalen);
        CaracteristicaCronometroConstructor.CmbCronometro(con, listaCrono);
        CaracteristicaSumergibleConstructor.CmbSumergible(con, listaCmbSum);
        
        cmbMarcaReloj.setItems(listaCmbMarcaR);
        cmbCalidadProducto.setItems(listaCmbCalidadR);
        cmbHora.setItems(listaCmbH);
        cmbAlarma.setItems(listaCmbAlar);
        cmbIluminacion.setItems(listaCmbIlum);
        cmbCalendario.setItems(listaCmbCalen);
        cmbCronometro.setItems(listaCrono);
        cmbSumergible.setItems(listaCmbSum);
    }

    @FXML
    private void BuscarIdentReloj(ActionEvent event) {
    }

    
    public void limpiarCampos(){
    txtRutaImg.setText("");
    txtNombreReloj.setText("");
    txtValorProducto.setText("");
    txtValorCliente.setText("");
    txtNumeroIdReloj.setText("");
    txtNombreCliente.setText("");
    txtNumeroContacto.setText("");
    txtDireccionEnvio.setText("");
    cmbMarcaReloj.setValue(null);
    cmbCalidadProducto.setValue(null);
    cmbHora.setValue(null);
    cmbAlarma.setValue(null);
    cmbIluminacion.setValue(null);
    cmbCalendario.setValue(null);
    cmbCronometro.setValue(null);
    cmbSumergible.setValue(null);
     
    }

    

    
}

    
