/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOModelsAdmin;

import DBConection.Conexion;
import Models.Cliente;
import Models.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martinsanabria
 */
public class DaoProductosAdmin {
    private Conexion CN;
    private Connection con;
    private PreparedStatement  ps;
    private ResultSet rs;
    
    public DaoProductosAdmin() {
    try {
         CN=new Conexion();
        } catch (Exception e) {
        }
    
    }
      public List consultaGeneral(){

        ArrayList<Producto> lista=new ArrayList<>();
        String sql="select * from productos";
        try {
            con=CN.getConection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Producto producto=new Producto();
                producto.setIdproducto(rs.getInt("idproducto"));
                producto.setIdcategoria(rs.getInt("idcategoria"));
                producto.setIdproveedor(rs.getInt("idproveedor"));
                producto.setNombre_producto(rs.getString("nombre_producto"));
                producto.setPrecio_normal(rs.getDouble("precio_normal"));
                producto.setOfertado(rs.getInt("ofertado"));
                 producto.setPrecio_oferta(rs.getDouble("precio_oferta"));
                producto.setExistencias(rs.getInt("existencias"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setImagen(rs.getString("imagen"));
                lista.add(producto);
            }
        }catch (Exception e){

        }
        return lista;
    }
      
       public Producto buscarPorID(int id){
        String sql="select * from productos where idproducto=?";
        Producto producto=new Producto();
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while (rs.next()){
                producto.setIdproducto(rs.getInt("idproducto"));
                producto.setIdcategoria(rs.getInt("idcategoria"));
                producto.setIdproveedor(rs.getInt("idproveedor"));
                producto.setNombre_producto(rs.getString("nombre_producto"));
                producto.setPrecio_normal(rs.getDouble("precio_normal"));
                producto.setOfertado(rs.getInt("ofertado"));
                 producto.setPrecio_oferta(rs.getDouble("precio_oferta"));
                producto.setExistencias(rs.getInt("existencias"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setImagen(rs.getString("imagen"));
            
            }

        }catch (Exception e){

        }
        return producto;
    }
       
       public boolean agregar(Producto producto){
        String sql="insert into productos(idcategoria, idproveedor, nombre_producto, precio_normal, ofertado, precio_oferta, existencias, descripcion, imagen) "
                + " values(?,?,?,?,?,?,?,?,?)";
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setInt(1,producto.getIdcategoria());
            ps.setInt(2,producto.getIdproveedor());
            ps.setString(3,producto.getNombre_producto());
            ps.setDouble(4,producto.getPrecio_normal());
            ps.setInt(5,producto.getOfertado());
            ps.setDouble(6,producto.getPrecio_oferta());
            ps.setInt(7,producto.getExistencias());
            ps.setString(8,producto.getDescripcion());
            ps.setString(9, producto.getImagen());
            int filasAfectadas=ps.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
       
        public boolean eliminar(int id){
        String sql="delete from productos where idproducto=?";
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
        
        public boolean actualizar(Producto producto){
        String sql="update productos set idcategoria=?, idproveedor=?, nombre_producto=?, precio_normal=?, ofertado=?, "
                + " precio_oferta=?, existencias=?, descripcion=?, imagen=? where idproducto=?";
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setInt(1,producto.getIdcategoria());
            ps.setInt(2,producto.getIdproveedor());
            ps.setString(3,producto.getNombre_producto());
            ps.setDouble(4,producto.getPrecio_normal());
            ps.setInt(5,producto.getOfertado());
            ps.setDouble(6,producto.getPrecio_oferta());
            ps.setInt(7,producto.getExistencias());
            ps.setString(8,producto.getDescripcion());
            ps.setString(9, producto.getImagen());
            ps.setInt(10,producto.getIdproducto());
            int filasAfectadas=ps.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
    
}
