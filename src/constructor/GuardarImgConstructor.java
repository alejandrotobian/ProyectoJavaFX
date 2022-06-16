package constructor;


import conexion.ConexionMysql;
import controller.VentanaSecundariaController;
import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 *
 * @author Tobian
 */
public class GuardarImgConstructor {
    
    ConexionMysql conexion = new ConexionMysql();
    Connection con = conexion.getConnection();
    
    private int id_tblRelojes;
    private String cmbMarcaReloj;
    private String txtNombreReloj;
    private String txtValorProducto;
    private String txtValorCliente;
    private byte[] imgReloj;
    
    public GuardarImgConstructor(){
        
    }

    public GuardarImgConstructor(int id_tblRelojes, String cmbMarcaReloj, String txtNombreReloj, String txtValorProducto, String txtValorCliente, byte[] imgReloj) {
        this.id_tblRelojes = id_tblRelojes;
        this.cmbMarcaReloj = cmbMarcaReloj;
        this.txtNombreReloj = txtNombreReloj;
        this.txtValorProducto = txtValorProducto;
        this.txtValorCliente = txtValorCliente;
        this.imgReloj = imgReloj;
    }

    public ConexionMysql getConexion() {
        return conexion;
    }

    public void setConexion(ConexionMysql conexion) {
        this.conexion = conexion;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public int getId_tblRelojes() {
        return id_tblRelojes;
    }

    public void setId_tblRelojes(int id_tblRelojes) {
        this.id_tblRelojes = id_tblRelojes;
    }

    public String getCmbMarcaReloj() {
        return cmbMarcaReloj;
    }

    public void setCmbMarcaReloj(String cmbMarcaReloj) {
        this.cmbMarcaReloj = cmbMarcaReloj;
    }

    public String getTxtNombreReloj() {
        return txtNombreReloj;
    }

    public void setTxtNombreReloj(String txtNombreReloj) {
        this.txtNombreReloj = txtNombreReloj;
    }

    public String getTxtValorProducto() {
        return txtValorProducto;
    }

    public void setTxtValorProducto(String txtValorProducto) {
        this.txtValorProducto = txtValorProducto;
    }

    public String getTxtValorCliente() {
        return txtValorCliente;
    }

    public void setTxtValorCliente(String txtValorCliente) {
        this.txtValorCliente = txtValorCliente;
    }

    public byte[] getImgReloj() {
        return imgReloj;
    }

    public void setImgReloj(byte[] imgReloj) {
        this.imgReloj = imgReloj;
    }
    
   
}
