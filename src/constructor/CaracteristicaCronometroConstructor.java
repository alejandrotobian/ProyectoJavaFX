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
public class CaracteristicaCronometroConstructor {
    private String Cronometro;
    
    public CaracteristicaCronometroConstructor(){
        
    }

    public CaracteristicaCronometroConstructor(String Cronometro) {
        this.Cronometro = Cronometro;
    }

    public String getCronometro() {
        return Cronometro;
    }

    public void setCronometro(String Cronometro) {
        this.Cronometro = Cronometro;
    }

    @Override
    public String toString() {
        return Cronometro;
    }    
    
    ConexionMysql conexion = new ConexionMysql();
    Connection con = conexion.getConnection();
    
    public static void CmbCronometro(Connection connection,
            ObservableList<CaracteristicaCronometroConstructor>listaCronometro){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT  * FROM tbl_caracteristicareloj");
            while(resultSet.next()){
                listaCronometro.add(new CaracteristicaCronometroConstructor(resultSet.getString("cronometro")));
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
