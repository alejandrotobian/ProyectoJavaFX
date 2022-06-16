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
public class MarcaRelojConstructor {
    
    private String marcaReloj;
    
    ConexionMysql conexion = new ConexionMysql();
    Connection con = conexion.getConnection();

    public MarcaRelojConstructor(String marcaReloj) {
        this.marcaReloj = marcaReloj;
    }

    public String getMarcaReloj() {
        return marcaReloj;
    }

    public void setMarcaReloj(String marcaReloj) {
        this.marcaReloj = marcaReloj;
    }
    
    public MarcaRelojConstructor() {
        
    }
    
    @Override
    public String toString() {
        return marcaReloj;
    }
    
    
    public static void cmbMarcaRelojes(Connection connection, 
            ObservableList<MarcaRelojConstructor>ListaCmbMarcaRelojes){
        
        try {
            Statement statement = connection.createStatement();
            ResultSet resulset = statement.executeQuery(" SELECT * FROM tbl_marcaReloj  ");
            while(resulset.next()){
                ListaCmbMarcaRelojes.add(new MarcaRelojConstructor(resulset.getString("marcaReloj")));
            }
        } catch (Exception e) {
            System.out.print(e.getLocalizedMessage());
        }
        
    }
}
