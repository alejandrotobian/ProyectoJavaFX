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
public class CaracteristicaAlarmaConstructor {
    private String alarma;

    public CaracteristicaAlarmaConstructor(String alarma) {
        this.alarma = alarma;
    }

    public String getAlarma() {
        return alarma;
    }

    public void setAlarma(String alarma) {
        this.alarma = alarma;
    }

    @Override
    public String toString() {
        return alarma;
    }
    
    public CaracteristicaAlarmaConstructor() {
        
    }
    
    ConexionMysql conexion = new ConexionMysql();
    Connection con = conexion.getConnection();
    
    public static void CmbCaracteristicaHora(Connection connection,
            ObservableList<CaracteristicaAlarmaConstructor>listaCmbAlarma){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl_caracteristicareloj");
            while (resultSet.next()) {
               listaCmbAlarma.add(new CaracteristicaAlarmaConstructor(resultSet.getString("alarma")));                
            }
        } catch (Exception e) {
        }
    }
        
}
