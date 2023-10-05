/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOModelsAdmin;

import DBConection.Conexion;
import Models.DetallePedido;
import Models.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martinsanabria
 */
public class DaoDetallePedido {
    private Conexion CN;
    private Connection con;
    private PreparedStatement  ps;
    private ResultSet rs;
    
    public DaoDetallePedido() {
    try {
         CN=new Conexion();
        } catch (Exception e) {
        }
    
    }
    
    
       
       public boolean agregar(DetallePedido detallepedido){
        String sql="insert into detallepedido (idpedido, idproducto, cantidad, subtotal) "
                + " values(?,?,?,?)";
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setInt(1,detallepedido.getIdpedido());
            ps.setInt(2,detallepedido.getIdproducto());
            ps.setInt(3,detallepedido.getCantidad());
            ps.setDouble(4,detallepedido.getSubtotal());
            int filasAfectadas=ps.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
       
        public boolean eliminar(int id){
        String sql="delete from detallepedido where idpedido=?";
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setInt(1,id);
            int filasAfectadas=ps.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
        
    
}
