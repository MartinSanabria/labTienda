/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOModelsAdmin;

import DBConection.Conexion;
import Models.Cliente;
import Models.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martinsanabria
 */
public class DaoProveedorAdmin {
    private Conexion CN;
    private Connection con;
    private PreparedStatement  ps;
    private ResultSet rs;
    
    public DaoProveedorAdmin() {
    try {
         CN=new Conexion();
        } catch (Exception e) {
        }
    
    }
    
    public List consultaGeneral(){

        ArrayList<Proveedor> lista=new ArrayList<>();
        String sql="select * from proveedores";
        try {
            con=CN.getConection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Proveedor proveedor=new Proveedor();
                proveedor.setIdproveedor(rs.getInt("idproveedor"));
                proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setContacto(rs.getString("contacto"));
                lista.add(proveedor);
            }
        }catch (Exception e){

        }
        return lista;
    }
      
       public Proveedor buscarPorID(int id){
        String sql="select * from proveedores where idproveedor=?";
        Proveedor proveedor=new Proveedor();
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while (rs.next()){
                proveedor.setIdproveedor(rs.getInt("idproveedor"));
                proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setContacto(rs.getString("contacto"));
            
            }

        }catch (Exception e){

        }
        return proveedor;
    }
       
       public boolean agregar(Proveedor proveedor){
        String sql="insert into proveedores(nombre_proveedor, telefono, contacto) values(?,?,?)";
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setString(1,proveedor.getNombre_proveedor());
            ps.setString(2,proveedor.getTelefono());
            ps.setString(3,proveedor.getContacto());
            int filasAfectadas=ps.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
       
        public boolean eliminar(int id){
        String sql="delete from proveedores where idproveedor=?";
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
        
        public boolean actualizar(Proveedor proveedor){
        String sql="update proveedores set nombre_proveedor=?,telefono=?,contacto=? where idproveedor=?";
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setString(1,proveedor.getNombre_proveedor());
            ps.setString(2,proveedor.getTelefono());
            ps.setString(3,proveedor.getContacto());
            ps.setInt(4,proveedor.getIdproveedor());
            int filasAfectadas=ps.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
}
