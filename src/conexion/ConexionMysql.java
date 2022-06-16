package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import com.mysql.jdbc.Driver;

/**
 *
 * @author Tobian
 */
public class ConexionMysql {
    private static Connection con;
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String user = "root";
    private static String password = "1234";
    private static String url = "jdbc:mysql://localhost:3306/basedato_relojeria";
    
    public ConexionMysql(){
    con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
            if(con!=null){
                System.out.println("Conexion Exitosa");
            }
        } catch (Exception e) {
            System.out.println("Error en conexion");
            System.out.println(e.getMessage());
        }
}
    
    public Connection getConnection(){
        return con;
    }    
    
    int ejecutarInstruccion(String SQL) {
        return 0;        
    }
}
