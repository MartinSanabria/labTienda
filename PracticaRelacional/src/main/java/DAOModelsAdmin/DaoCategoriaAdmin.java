/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOModelsAdmin;

import DBConection.Conexion;
import Models.Categoria;
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
public class DaoCategoriaAdmin {
    private Conexion CN;
    private Connection con;
    private PreparedStatement  ps;
    private ResultSet rs;
    
    public DaoCategoriaAdmin() {
    try {
         CN=new Conexion();
        } catch (Exception e) {
        }
    
    }
    
     public List consultaGeneral(){

        ArrayList<Categoria> lista=new ArrayList<>();
        String sql="select * from categorias";
        try {
            con=CN.getConection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Categoria categoria=new Categoria();
                categoria.setIdcategoria(rs.getInt("idcategoria"));
                categoria.setNombre_categoria(rs.getString("nombre_categoria"));
                lista.add(categoria);
            }
        }catch (Exception e){

        }
        return lista;
    }
      
       public Categoria buscarPorID(int id){
        String sql="select * from categorias where idcategoria=?";
        Categoria categoria=new Categoria();
        try {
             ps=CN.getConection().prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while (rs.next()){
                categoria.setIdcategoria(rs.getInt("idcategoria"));
                categoria.setNombre_categoria(rs.getString("nombre_categoria"));
            
            }

        }catch (Exception e){

        }
        return categoria;
    }
       
       public boolean agregar(Categoria categoria){
        String sql="insert into categorias(nombre_categoria) values(?)";
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setString(1,categoria.getNombre_categoria());
            int filasAfectadas=ps.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
       
        public boolean eliminar(int id){
        String sql="delete from categorias where idcategoria=?";
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
        
        public boolean actualizar(Categoria categoria){
        String sql="update categorias set nombre_categoria=? where idcategoria=?";
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setString(1,categoria.getNombre_categoria());
            ps.setInt(2,categoria.getIdcategoria());
            int filasAfectadas=ps.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
}
