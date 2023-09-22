/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOModels;

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
public class DaoClients {
    private Conexion con;
    private Connection DBCon;
    private PreparedStatement  prepared;
    private ResultSet resultset;
    
    public DaoClients() {
    try {
         con=new Conexion();
        } catch (Exception e) {
        }
    
    }
    
      public List consultaGeneral(){

        ArrayList<Cliente> lista=new ArrayList<>();
        String sql="select * from clientes";
        try {
            DBCon=con.getConection();
            prepared=DBCon.prepareStatement(sql);
            resultset=prepared.executeQuery();
            while(resultset.next()){
                Cliente cliente=new Cliente();
                cliente.setIdcliente(resultset.getInt("idcliente"));
                cliente.setApellidos(resultset.getString("apellidos"));
                cliente.setNombres(resultset.getString("nombres"));
                cliente.setCorreo(resultset.getString("correo"));
                cliente.setTelefono(resultset.getString("telefono"));
                cliente.setSexo(resultset.getString("sexo"));
                cliente.setDireccion(resultset.getString("direccion"));
                cliente.setPais(resultset.getString("pais"));
                cliente.setClave(resultset.getString("clave"));
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
            prepared=con.getConection().prepareStatement(sql);
            prepared.setInt(1,id);
            resultset=prepared.executeQuery();
            while (resultset.next()){
                cliente.setIdcliente(resultset.getInt("idcliente"));
                cliente.setApellidos(resultset.getString("apellidos"));
                cliente.setNombres(resultset.getString("nombres"));
                cliente.setCorreo(resultset.getString("correo"));
                cliente.setTelefono(resultset.getString("telefono"));
                cliente.setSexo(resultset.getString("sexo"));
                cliente.setDireccion(resultset.getString("direccion"));
                cliente.setPais(resultset.getString("pais"));
                cliente.setClave(resultset.getString("clave"));
            
            }

        }catch (Exception e){

        }
        return cliente;
    }
       
       public boolean agregar(Cliente cliente){
        String sql="insert into clientes(nombres, apellidos, sexo, direccion, telefono, pais, clave, correo) values(?,?,?,?.?,?,?,?)";
        try {
            prepared=con.getConection().prepareStatement(sql);
            prepared.setString(1,cliente.getNombres());
            prepared.setString(2,cliente.getApellidos());
            prepared.setString(3,cliente.getSexo());
            prepared.setString(4,cliente.getDireccion());
            prepared.setString(5,cliente.getTelefono());
            prepared.setString(6,cliente.getPais());
            prepared.setString(7,cliente.getClave());
            prepared.setString(8,cliente.getCorreo());
            int filasAfectadas=prepared.executeUpdate();
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
        
        public boolean actualizar(Cliente cliente){
        String sql="update clientes set nombres=?,apellidos=?,sexo=?,direccion=?,telefono=?,pais=?,clave=?,correo=? where idcliente=?";
        try {
            prepared=con.getConection().prepareStatement(sql);
            prepared.setString(1,cliente.getNombres());
            prepared.setString(2,cliente.getApellidos());
            prepared.setString(3,cliente.getSexo());
            prepared.setString(4,cliente.getDireccion());
            prepared.setString(5,cliente.getTelefono());
            prepared.setString(6,cliente.getPais());
            prepared.setString(7,cliente.getClave());
            prepared.setString(8,cliente.getCorreo());
            prepared.setInt(9,cliente.getIdcliente());
            int filasAfectadas=prepared.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
}
