/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOModels;

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
public class DaoProveedor {
    private Conexion con;
    private Connection DBCon;
    private PreparedStatement  prepared;
    private ResultSet resultset;
    
    public DaoProveedor() {
    try {
         con=new Conexion();
        } catch (Exception e) {
        }
    
    }
    
    
    public List consultaGeneral(){

        ArrayList<Proveedor> lista=new ArrayList<>();
        String sql="select * from proveedores";
        try {
            DBCon=con.getConection();
            prepared=DBCon.prepareStatement(sql);
            resultset=prepared.executeQuery();
            while(resultset.next()){
                Proveedor proveedor=new Proveedor();
                proveedor.setIdproveedor(resultset.getInt("idproveedor"));
                proveedor.setNombre_proveedor(resultset.getString("nombre_proveedor"));
                proveedor.setTelefono(resultset.getString("telefono"));
                proveedor.setContacto(resultset.getString("contacto"));
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
            prepared=con.getConection().prepareStatement(sql);
            prepared.setInt(1,id);
            resultset=prepared.executeQuery();
            while (resultset.next()){
                proveedor.setIdproveedor(resultset.getInt("idproveedor"));
                proveedor.setNombre_proveedor(resultset.getString("nombre_proveedor"));
                proveedor.setTelefono(resultset.getString("telefono"));
                proveedor.setContacto(resultset.getString("contacto"));
            
            }

        }catch (Exception e){

        }
        return proveedor;
    }
       
       public boolean agregar(Proveedor proveedor){
        String sql="insert into proveedores(nombre_proveedor, telefono, contacto) values(?,?,?)";
        try {
            prepared=con.getConection().prepareStatement(sql);
            prepared.setString(1,proveedor.getNombre_proveedor());
            prepared.setString(2,proveedor.getTelefono());
            prepared.setString(3,proveedor.getContacto());
            int filasAfectadas=prepared.executeUpdate();
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
            prepared=con.getConection().prepareStatement(sql);
            prepared.setInt(1,id);
            int filasAfectadas=prepared.executeUpdate();
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
            prepared=con.getConection().prepareStatement(sql);
            prepared.setString(1,proveedor.getNombre_proveedor());
            prepared.setString(2,proveedor.getTelefono());
            prepared.setString(3,proveedor.getContacto());
            prepared.setInt(4,proveedor.getIdproveedor());
            int filasAfectadas=prepared.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
}
