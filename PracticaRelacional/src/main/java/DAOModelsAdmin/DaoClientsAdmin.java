/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOModelsAdmin;

import DBConection.Conexion;
import Models.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martinsanabria
 */
public class DaoClientsAdmin {
    private Conexion CN;
    private Connection con;
    private PreparedStatement  ps;
    private ResultSet rs;
    
    public DaoClientsAdmin() {
    try {
         CN=new Conexion();
        } catch (Exception e) {
        }
    
    }
    
      public List consultaGeneral(){

        ArrayList<Cliente> lista=new ArrayList<>();
        String sql="select * from clientes";
        try {
            con=CN.getConection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Cliente cliente=new Cliente();
                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setNombres(rs.getString("nombres"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setPais(rs.getString("pais"));
                cliente.setClave(rs.getString("clave"));
                lista.add(cliente);
            }
        }catch (Exception e){

        }
        return lista;
    }
      
       public Cliente buscarPorID(int id){
        String sql="select * from clientes where idcliente=?";
        Cliente cliente=new Cliente();
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while (rs.next()){
                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setNombres(rs.getString("nombres"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setPais(rs.getString("pais"));
                cliente.setClave(rs.getString("clave"));
            
            }

        }catch (Exception e){

        }
        return cliente;
    }
       
       public boolean agregar(Cliente cliente){
        String sql="insert into clientes(nombres, apellidos, sexo, direccion, telefono, pais, clave, correo) values(?,?,?,?,?,?,?,?)";
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setString(1,cliente.getNombres());
            ps.setString(2,cliente.getApellidos());
            ps.setString(3,cliente.getSexo());
            ps.setString(4,cliente.getDireccion());
            ps.setString(5,cliente.getTelefono());
            ps.setString(6,cliente.getPais());
            ps.setString(7,cliente.getClave());
            ps.setString(8,cliente.getCorreo());
            int filasAfectadas=ps.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
       
        public boolean eliminar(int id){
        String sql="delete from clientes where idcliente=?";
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
        
        public boolean actualizar(Cliente cliente){
        String sql="update clientes set nombres=?,apellidos=?,sexo=?,direccion=?,telefono=?,pais=?,clave=?,correo=? where idcliente=?";
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setString(1,cliente.getNombres());
            ps.setString(2,cliente.getApellidos());
            ps.setString(3,cliente.getSexo());
            ps.setString(4,cliente.getDireccion());
            ps.setString(5,cliente.getTelefono());
            ps.setString(6,cliente.getPais());
            ps.setString(7,cliente.getClave());
            ps.setString(8,cliente.getCorreo());
            ps.setInt(9,cliente.getIdcliente());
            int filasAfectadas=ps.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
}
