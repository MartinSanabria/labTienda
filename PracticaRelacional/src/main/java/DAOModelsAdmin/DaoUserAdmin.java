/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOModelsAdmin;

import DBConection.Conexion;
import Models.Cliente;
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
public class DaoUserAdmin {
    private Conexion CN;
    private Connection con;
    private PreparedStatement  ps;
    private ResultSet rs;
    
    public DaoUserAdmin() {
    try {
         CN=new Conexion();
        } catch (Exception e) {
        }
    
    }
    
     public List consultaGeneral(){

        ArrayList<Usuario> lista=new ArrayList<>();
        String sql="select * from usuario";
        try {
            con=CN.getConection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Usuario usuario=new Usuario();
                
                usuario.setUsuario_id(rs.getInt("usuario_id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setPassword(rs.getString("password"));
                lista.add(usuario);
            }
        }catch (Exception e){

        }
        return lista;
    }
      
       public Usuario buscarPorID(int id){
        String sql="select * from usuario where usuario_id=?";
        Usuario usuario=new Usuario();
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while (rs.next()){
               usuario.setUsuario_id(rs.getInt("usuario_id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setPassword(rs.getString("password"));
            
            }

        }catch (Exception e){

        }
        return usuario;
    }
       
       public boolean agregar(Usuario usuario){
        String sql="insert into usuario(nombre, apellido, telefono, email, password) values(?,?,?,?,?)";
        
        String hashPassword = this.hashContrasena(usuario.getPassword());
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setString(1,usuario.getNombre());
            ps.setString(2,usuario.getApellido());
            ps.setString(3,usuario.getTelefono());
            ps.setString(4,usuario.getEmail());
            ps.setString(5,hashPassword);
            int filasAfectadas=ps.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
       
        public boolean eliminar(int id){
        String sql="delete from usuario where usuario_id=?";
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
        
        public boolean actualizar(Usuario usuario){
        String sql="update usuario set nombre=?,apellido=?,telefono=?,email=?,password=? where usuario_id=?";
        String hashPassword = this.hashContrasena(usuario.getPassword());
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setString(1,usuario.getNombre());
            ps.setString(2,usuario.getApellido());
            ps.setString(3,usuario.getTelefono());
            ps.setString(4,usuario.getEmail());
            ps.setString(5,hashPassword);
            ps.setInt(6,usuario.getUsuario_id());
            int filasAfectadas=ps.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
        
        private String hashContrasena(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(contrasena.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%04x", b));
            }
            return sb.toString().substring(0, 20);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        
    }
}
