/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainController;

import DBConection.Conexion;
import Models.Cliente;
import Models.Proveedor;
import Models.Usuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martinsanabria
 */
public class DaoMain {
    
     private Conexion CN;
    private Connection con;
    private PreparedStatement  ps;
    private ResultSet rs;
    
    public DaoMain() {
    try {
         CN=new Conexion();
        } catch (Exception e) {
        }
    
    }
    
    public Usuario ConsultaUsuario(String email, String password){

        Usuario user=new Usuario();
        String sql="SELECT us.usuario_id,us.nombre,us.apellido,us.telefono,us.email,us.password from usuario us \n" +
                    "inner join detalle_rol drol ON drol.usuario_id = us.usuario_id \n" +
                    "where us.email = ? AND us.password = ? AND rol_id=2";
        try {
          ps=CN.getConection().prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2,password);
            rs=ps.executeQuery();
            while(rs.next()){
                user.setUsuario_id(rs.getInt("usuario_id"));
                user.setNombre(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido"));
                user.setTelefono(rs.getString("telefono"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                
            }
        }catch (Exception e){

        }
        return user;
    }
    
    public Cliente consultaCliente(String email, String password){

        Cliente cliente=new Cliente();
        String sql="SELECT cli.idcliente,clientes.nombres,cli.apellidos,cli.sexo,cli.direccion,cli.telefono,cli.pais,cli.correo,cli.clave from clientes cli \n" +
                    "inner join detalle_rol drol ON drol.cliente_id = cli.idcliente \n" +
                    "where cli.correo = ? AND cli.clave = ? AND rol_id=1";
        try {
          ps=CN.getConection().prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2,password);
            rs=ps.executeQuery();
            while(rs.next()){
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
    
    
   
    
    
    public boolean agregarRolCliente(Cliente cliente){
        String sql="insert into detalle_rol(rol_id,cliente_id) values(?,?)";
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setInt(2,cliente.getIdcliente());
            int filasAfectadas=ps.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
     
    
    public boolean agregarRolUsuario(Usuario usuario){
        String sql="insert into detalle_rol(rol_id,usuario_id) values(?,?)";
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setInt(1, 2);
            ps.setInt(2,usuario.getUsuario_id());
            int filasAfectadas=ps.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
     
     
     
     public boolean borrarRolCliente(int id){
        String sql="delete from detalle_rol where cliente_id=?";
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
     
     public boolean borrarRolUsuario(int id){
        String sql="delete from detalle_rol where usuario_id=?";
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
     
     public Cliente buscarPorCorreo(String correo){
        String sql="select * from clientes where correo=?";
        Cliente cliente=new Cliente();
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setString(1,correo);
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
     
     public Usuario buscarPorCorreoUser(String correo){
        String sql="select * from usuario where email=?";
        Usuario user=new Usuario();
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setString(1,correo);
            rs=ps.executeQuery();
            while (rs.next()){
                user.setUsuario_id(rs.getInt("usuario_id"));
                user.setNombre(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido"));
                user.setTelefono(rs.getString("telefono"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

            
            }

        }catch (Exception e){

        }
        return user;
    }
}
