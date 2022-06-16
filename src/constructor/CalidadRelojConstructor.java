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
public class CalidadRelojConstructor {
    
    private String calidadReloj;

    public CalidadRelojConstructor() {
    }

    public String getCalidadReloj() {
        return calidadReloj;
    }

    public void setCalidadReloj(String calidadReloj) {
        this.calidadReloj = calidadReloj;
    }

    public CalidadRelojConstructor(String calidadReloj) {
        this.calidadReloj = calidadReloj;
    }
    
    
    ConexionMysql conexion = new ConexionMysql();
    Connection con = conexion.getConnection();
    
    @Override 
    public String toString(){
        return calidadReloj;
    }
    
    public static void cmbCalidadReloj(Connection connection,
            ObservableList<CalidadRelojConstructor>listaCalidadReloj){
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM tbl_calidadreloj");
            while(result.next()){
            listaCalidadReloj.add(new CalidadRelojConstructor(result.getString("calidadProducto")));
            }
        } catch (Exception e) {
            System.out.print(e.getLocalizedMessage());
        }
        
    }
}
