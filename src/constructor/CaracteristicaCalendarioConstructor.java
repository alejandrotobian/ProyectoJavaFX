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
public class CaracteristicaCalendarioConstructor {
    
    private String calendario;

    public CaracteristicaCalendarioConstructor(String calendario) {
        this.calendario = calendario;
    }

    public String getCalendario() {
        return calendario;
    }

    public void setCalendario(String calendario) {
        this.calendario = calendario;
    }

    @Override
    public String toString() {
        return  calendario;
    }
    
    public CaracteristicaCalendarioConstructor(){
        
    }
    
    public static void CmbCalendario(Connection connection,
            ObservableList<CaracteristicaCalendarioConstructor>listaCalendario){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT  * FROM tbl_caracteristicareloj");
            while(resultSet.next()){
                listaCalendario.add(new CaracteristicaCalendarioConstructor(resultSet.getString("calendario")));
            }
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
    
}
