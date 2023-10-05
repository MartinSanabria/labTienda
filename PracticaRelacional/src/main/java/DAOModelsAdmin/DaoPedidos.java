/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOModelsAdmin;

import DBConection.Conexion;
import Models.DetallePedido;
import Models.Pedido;
import Models.Producto;
import Models.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martinsanabria
 */
public class DaoPedidos {
    private Conexion CN;
    private Connection con;
    private PreparedStatement  ps;
    private ResultSet rs;
    
    public DaoPedidos() {
    try {
         CN=new Conexion();
        } catch (Exception e) {
        }
    
    }
    
     public List consultaGeneral(){

        ArrayList<Pedido> lista=new ArrayList<>();
        String sql="select * from pedidos";
        try {
            con=CN.getConection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Pedido pedido=new Pedido();
                pedido.setIdpedido(rs.getInt("idpedido"));
                pedido.setIdcliente(rs.getInt("idcliente"));
                pedido.setFecha(rs.getDate("fecha"));
                pedido.setTotal(rs.getDouble("total"));
                pedido.setEstado(rs.getString("estado"));
                lista.add(pedido);
            }
        }catch (Exception e){ 

        }
        return lista;
    }
    
     public List ListarPedidos(int id){

        ArrayList<Pedido> lista=new ArrayList<>();
        String sql="select * from pedidos where idcliente=?";
        
        try {
           ps=CN.getConection().prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                Pedido pedido=new Pedido();
                pedido.setIdpedido(rs.getInt("idpedido"));
                pedido.setIdcliente(rs.getInt("idcliente"));
                pedido.setFecha(rs.getDate("fecha"));
                pedido.setTotal(rs.getDouble("total"));
                pedido.setEstado(rs.getString("estado"));
                lista.add(pedido);
            }
        }catch (Exception e){

        }
        return lista;
    }
     
    public Pedido buscarPorID(int id){
        String sql="select * from pedidos where idpedido=?";
        Pedido pedido=new Pedido();
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while (rs.next()){
                
                pedido.setIdpedido(rs.getInt("idpedido"));
                pedido.setIdcliente(rs.getInt("idcliente"));
                pedido.setFecha(rs.getDate("fecha"));
                pedido.setTotal(rs.getDouble("total"));
                pedido.setEstado(rs.getString("estado"));
            
            }

        }catch (Exception e){

        }
        return pedido;
    }
     
    public List ListarDetallePedidos(int id){

        ArrayList<DetallePedido> lista=new ArrayList<>();
        String sql="select * from detallepedido where idpedido=?";
        
        try {
           ps=CN.getConection().prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                DetallePedido detalle=new DetallePedido();
                detalle.setIdpedido(rs.getInt("idpedido"));
                detalle.setIddetalle(rs.getInt("iddetalle"));
                detalle.setIdproducto(rs.getInt("idproducto"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setSubtotal(rs.getDouble("subtotal"));
                lista.add(detalle);
            }
        }catch (Exception e){

        }
        return lista;
    }
     
       
        public int agregarPedido(Pedido pedido) {
        String sql = "INSERT INTO pedidos (idCliente, fecha, total,estado) VALUES (?, ?, ?,?)";

        try {
            // Indica que deseas obtener el ID generado automáticamente
            ps=CN.getConection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, pedido.getIdcliente());
            ps.setDate(2, new java.sql.Date(pedido.getFecha().getTime()));
            ps.setDouble(3, pedido.getTotal());
            ps.setString(4, pedido.getEstado());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                // Obtén las claves generadas automáticamente
                ResultSet generatedKeys = ps.getGeneratedKeys();

                if (generatedKeys.next()) {
                    // Retorna el ID generado automáticamente
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al agregar pedido: " + e.getMessage()); // Imprime el mensaje de error en la consola

        }

        // Si no se pudo insertar o recuperar el ID, retorna un valor negativo o lanza una excepción según tu manejo de errores.
        return -1; // Por ejemplo, puedes retornar -1 si hay un error.
    }
       
        public boolean eliminar(int id){
        String sql="delete from pedidos where idpedido=?";
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
        
        public boolean actualizar(Pedido pedido){
        String sql="update pedidos set estado=? where idpedido=?";
        try {
            ps=CN.getConection().prepareStatement(sql);
            ps.setString(1,pedido.getEstado());
            ps.setInt(2,pedido.getIdpedido());
            int filasAfectadas=ps.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
}
