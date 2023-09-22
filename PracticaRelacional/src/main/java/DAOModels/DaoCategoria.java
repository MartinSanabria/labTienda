/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOModels;

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
public class DaoCategoria {
    private Conexion con;
    private Connection DBCon;
    private PreparedStatement  prepared;
    private ResultSet resultset;
    
    public DaoCategoria() {
    try {
         con=new Conexion();
        } catch (Exception e) {
        }
    
    }
    
     public List consultaGeneral(){

        ArrayList<Categoria> lista=new ArrayList<>();
        String sql="select * from categorias";
        try {
            DBCon=con.getConection();
            prepared=DBCon.prepareStatement(sql);
            resultset=prepared.executeQuery();
            while(resultset.next()){
                Categoria categoria=new Categoria();
                categoria.setIdcategoria(resultset.getInt("idproveedor"));
                categoria.setNombre_categoria(resultset.getString("nombre_categoria"));
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
            prepared=con.getConection().prepareStatement(sql);
            prepared.setInt(1,id);
            resultset=prepared.executeQuery();
            while (resultset.next()){
                categoria.setIdcategoria(resultset.getInt("idcategoria"));
                categoria.setNombre_categoria(resultset.getString("nombre_categoria"));
            
            }

        }catch (Exception e){

        }
        return categoria;
    }
       
       public boolean agregar(Categoria categoria){
        String sql="insert into categorias(nombre_categoria) values(?)";
        try {
            prepared=con.getConection().prepareStatement(sql);
            prepared.setString(1,categoria.getNombre_categoria());
            int filasAfectadas=prepared.executeUpdate();
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
        
        public boolean actualizar(Categoria categoria){
        String sql="update categorias set nombre_categoria=? where idcategoria=?";
        try {
            prepared=con.getConection().prepareStatement(sql);
            prepared.setString(1,categoria.getNombre_categoria());
            prepared.setInt(2,categoria.getIdcategoria());
            int filasAfectadas=prepared.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
}
