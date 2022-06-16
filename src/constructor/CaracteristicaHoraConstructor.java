package constructor;

import conexion.ConexionMysql;
import java.sql.Connection;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Tobian
 */
public class CaracteristicaHoraConstructor {
    
    private String hora;   
    
    public CaracteristicaHoraConstructor(){
        
    }
    
    @Override
    public String toString() {
        return hora;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    public CaracteristicaHoraConstructor(String hora) {
        this.hora = hora;
    }   
    
    ConexionMysql conexion = new ConexionMysql();
    Connection con = conexion.getConnection();

    public static void cmbCaracteristicaHora(Connection connection,
            ObservableList<CaracteristicaHoraConstructor>listaCmbHora){
     
        try {
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl_caracteristicareloj");
           while(resultSet.next()){
           listaCmbHora.add(new CaracteristicaHoraConstructor(resultSet.getString("hora")));
        } 
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        
        
    }
    
    
}
