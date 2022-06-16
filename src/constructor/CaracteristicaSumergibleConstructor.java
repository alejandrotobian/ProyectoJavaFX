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
public class CaracteristicaSumergibleConstructor {
    
    ConexionMysql conexion = new ConexionMysql();
    Connection con = conexion.getConnection();
    
    private String sumergible;
    
    public CaracteristicaSumergibleConstructor(){
        
    }

    public CaracteristicaSumergibleConstructor(String sumergible) {
        this.sumergible = sumergible;
    }

    public String getSumergible() {
        return sumergible;
    }

    public void setSumergible(String sumergible) {
        this.sumergible = sumergible;
    }

    @Override
    public String toString() {
        return sumergible;
    }
    
    public static void CmbSumergible(Connection connection,
            ObservableList<CaracteristicaSumergibleConstructor>listaCmbSumergible){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl_caracteristicareloj");
            while(resultSet.next()){
                listaCmbSumergible.add(new CaracteristicaSumergibleConstructor(resultSet.getString("sumergible")));
            }                    
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
}
