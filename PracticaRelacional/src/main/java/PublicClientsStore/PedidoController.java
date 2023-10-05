/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package PublicClientsStore;

import DAOModelsAdmin.DaoDetallePedido;
import DAOModelsAdmin.DaoPedidos;
import DAOModelsAdmin.DaoProductosAdmin;
import Models.Carrito;
import Models.Cliente;
import Models.DetallePedido;
import Models.Pedido;
import Models.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author martinsanabria
 */
@WebServlet(name = "PedidoController", urlPatterns = {"/PedidoController"})
public class PedidoController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PedidoController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PedidoController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession(false);
        Cliente cliente = (Cliente) session.getAttribute("cliente"); 
            if(request.getParameter("action").equals("pedido")){
                        List<Carrito> carritoProductos = (List<Carrito>) session.getAttribute("carrito");
                        if (session != null || cliente != null) {
                            // Actualiza la lista de carrito en la sesión
                        if (cliente != null && carritoProductos != null && !carritoProductos.isEmpty()){
                            
                            Pedido pedido = new Pedido();
                            pedido.setIdcliente(cliente.getIdcliente());
                              // Obtener la fecha actual
                            java.util.Date fechaActual = new java.util.Date();
                            java.sql.Date fechaSQL = new java.sql.Date(fechaActual.getTime());
                            pedido.setFecha(fechaSQL); // Establecer la fecha actual
                            pedido.setEstado("Pendiente"); // Establecer el estado inicial del pedido
                            double totalPedido = 0.0; // Inicializa el total del pedido como 0.0
                            
                            for (Carrito producto : carritoProductos) {
                                if( producto.getOfertadoc() == 1){
                                    double subtotalProducto = producto.getPrecio_ofertadoC()* producto.getCantidad();
                                    totalPedido += subtotalProducto;
                                } else if(producto.getOfertadoc() == 0){
                                    double subtotalProducto = producto.getPrecio_normalc()* producto.getCantidad();
                                    totalPedido += subtotalProducto;
                                }
                                
                            }
                            
                            pedido.setTotal(totalPedido);
                            DaoPedidos pedidoDAO = new DaoPedidos();
                            
                            int pedidoId = pedidoDAO.agregarPedido(pedido);

                            // Crear objetos DetallePedido para cada producto en el carrito y guardar en la base de datos
                             for (Carrito producto : carritoProductos) {
                                DetallePedido detallePedido = new DetallePedido();
                                detallePedido.setIdpedido(pedidoId);
                                detallePedido.setIdproducto(producto.getIdproductoC());
                                detallePedido.setCantidad(producto.getCantidad());
                                if( producto.getOfertadoc() == 1){
                                    detallePedido.setSubtotal(producto.getPrecio_ofertadoC()* producto.getCantidad());
                                } else if(producto.getOfertadoc() == 0){
                                    detallePedido.setSubtotal(producto.getPrecio_normalc()* producto.getCantidad());
                                }
                               
                                
                                DaoDetallePedido detallePedidoDAO = new DaoDetallePedido();

                                // Llama a tu método para crear un detalle de pedido en la base de datos
                                detallePedidoDAO.agregar(detallePedido);
                                session.removeAttribute("carrito");
                                session.setAttribute("detalleFactura", carritoProductos);
                                RequestDispatcher dispatcher2=request.getRequestDispatcher("/Carrito/Factura.jsp");
                                dispatcher2.forward(request,response);
                            }

                        }
                            

                    } 
                } else if (request.getParameter("action").equals("MiPedido")){
                        DaoPedidos pedidosdao = new DaoPedidos();
                        List<Pedido> pedidoList = pedidosdao.ListarPedidos(cliente.getIdcliente());
                        
                        
                        request.setAttribute("pedidos", pedidoList);
                        RequestDispatcher dispatcher2=request.getRequestDispatcher("/Cliente/Pedidos.jsp");
                        dispatcher2.forward(request,response);
                        
                }else if (request.getParameter("action").equals("detallepedido")) {
                        DaoPedidos pedidosdao = new DaoPedidos();
                        int idPedidoD = Integer.parseInt(request.getParameter("idPedido"));
                        List<DetallePedido> detalleList = pedidosdao.ListarDetallePedidos(idPedidoD);

                        DaoProductosAdmin products = new DaoProductosAdmin();

                        Map<Integer, Producto> productosData = new HashMap<>();

                        // Utilizar una lista para almacenar los productos procesados
                        List<Integer> productosProcesados = new ArrayList<>();

                        for (DetallePedido ped : detalleList) {
                            int idProducto = ped.getIdproducto();

                            // Verificar si el producto ya se procesó para evitar duplicados
                            if (!productosProcesados.contains(idProducto)) {
                                Producto produc = products.buscarPorID(idProducto);
                                int existencias = produc.getExistencias() - ped.getCantidad();
                                products.actualizarExistencias(existencias, idProducto);
                                productosData.put(produc.getIdproducto(), produc);

                                // Agregar el producto a la lista de productos procesados
                                productosProcesados.add(idProducto);
                            }
                        }

                        // Agrega la información al objeto request
                        request.setAttribute("detalleProductos", detalleList);
                        request.setAttribute("productosL", productosData);
                        request.setAttribute("idPedido", idPedidoD);

                        // Redirige a la página de detalle
                        RequestDispatcher dispatcher2 = request.getRequestDispatcher("/Cliente/detalle.jsp");
                        dispatcher2.forward(request, response);
                    }
                                         else if (request.getParameter("action").equals("consultarFactura")) {
                        int idPedido = Integer.parseInt(request.getParameter("idPedido"));
                        // Recupera la información del pedido y sus detalles
                        DaoPedidos pedidosdao = new DaoPedidos();

                        // Obtén el pedido específico por su ID
                        Pedido pedido = pedidosdao.buscarPorID(idPedido);

                        // Verifica si el pedido es nulo, puedes manejarlo según tus requerimientos
                        if (pedido != null) {
                            List<DetallePedido> detalleList = pedidosdao.ListarDetallePedidos(idPedido);

                            // Agrega la información al objeto request
                            request.setAttribute("pedido", pedido);
                            request.setAttribute("detalleProductos", detalleList);
                            

                        DaoProductosAdmin products = new DaoProductosAdmin();

                        Map<Integer, Producto> productosData = new HashMap<>();
                        for (DetallePedido ped : detalleList) {
                            Producto produc = products.buscarPorID(ped.getIdproducto());
                            int existencias = produc.getExistencias() - ped.getCantidad();
                            products.actualizarExistencias(existencias, produc.getIdproducto());
                            productosData.put(produc.getIdproducto(), produc);
                        }
                        


                        // Agrega la información al objeto request
                        request.setAttribute("detalleProductos", detalleList);
                        request.setAttribute("productosL", productosData);
                        request.setAttribute("idPedido", idPedido);
                        
                         RequestDispatcher dispatcher = request.getRequestDispatcher("/Cliente/factura.jsp");
                        dispatcher.forward(request, response);
                            
                        } 
                    }

            
            
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
