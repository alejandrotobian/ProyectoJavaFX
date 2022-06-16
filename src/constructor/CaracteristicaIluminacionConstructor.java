package constructor;

import conexion.ConexionMysql;
import java.sql.Connection;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Tobian
 */
public class CaracteristicaIluminacionConstructor {
   
    
    ConexionMysql conexion = new ConexionMysql();
    Connection con = conexion.getConnection();
    
    private String iluminacion;

    public CaracteristicaIluminacionConstructor(String iluminacion) {
        this.iluminacion = iluminacion;
    }

    public String getIluminacion() {
        return iluminacion;
    }

    public void setIluminacion(String iluminacion) {
        this.iluminacion = iluminacion;
    }

    @Override
    public String toString() {
        return iluminacion ;
    }
    
    public CaracteristicaIluminacionConstructor(){
        
    }
    
    public static void CmbIluminacion(Connection connection, 
            ObservableList<CaracteristicaIluminacionConstructor>listaIluminacion){
        
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT  * FROM tbl_caracteristicareloj");
            while(resultSet.next()){
            listaIluminacion.add( new CaracteristicaIluminacionConstructor(resultSet.getString("iluminacion")));
        }
        } catch (Exception e) {
        }
    }
}
