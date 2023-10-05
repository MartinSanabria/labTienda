/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllerAdmin;

import DAOModelsAdmin.DaoCategoriaAdmin;
import DAOModelsAdmin.DaoClientsAdmin;
import DAOModelsAdmin.DaoDetallePedido;
import DAOModelsAdmin.DaoPedidos;
import DAOModelsAdmin.DaoProductosAdmin;
import Models.Categoria;
import Models.Cliente;
import Models.DetallePedido;
import Models.Pedido;
import Models.Producto;
import Models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AdminPedidosController", urlPatterns = {"/AdminPedidosController"})
public class AdminPedidosController extends HttpServlet {

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
            out.println("<title>Servlet AdminPedidosController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminPedidosController at " + request.getContextPath() + "</h1>");
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
             Usuario usuario = (Usuario) session.getAttribute("usuario");

         if (session == null || usuario == null) {
             // Si no hay sesión o el usuario no está autenticado, redirige a la página de inicio de sesión
             response.sendRedirect("index.jsp");
         } else {
             try {
                  if(request.getParameter("action")!=null){
                        if(request.getParameter("action").equals("edit")){
                            int idpedido=Integer.parseInt(request.getParameter("id"));
                           DaoPedidos pedidoAd = new DaoPedidos();
                             //obtener los datos
                            Pedido pedidoUp = (Pedido) pedidoAd.buscarPorID(idpedido);
                            request.setAttribute("pedidoEdit", pedidoUp);
                             //falta traer la persona de la base de datos
                             //y pasarlo como atributo
                            RequestDispatcher dispatcher2=request.getRequestDispatcher("/AdminPedidos/edit.jsp");
                            dispatcher2.forward(request,response);
                            
                        } else if (request.getParameter("action").equals("detallepedido")) {
                        DaoPedidos pedidosdao = new DaoPedidos();
                        int idPedidoD = Integer.parseInt(request.getParameter("idPedido"));
                        List<DetallePedido> detalleList = pedidosdao.ListarDetallePedidos(idPedidoD);

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
                        request.setAttribute("idPedido", idPedidoD);


                        // Redirige a la página de detalle
                        RequestDispatcher dispatcher2 = request.getRequestDispatcher("/AdminPedidos/detalle.jsp");
                        dispatcher2.forward(request, response);
                    }
                   }
                 
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
         
               DaoPedidos pedidosA = new DaoPedidos();
                List<Pedido> pedidosL = pedidosA.consultaGeneral();

                // Obtener la lista de clientes
                DaoClientsAdmin clientsAdmin = new DaoClientsAdmin();
                List<Cliente> clientes = clientsAdmin.consultaGeneral();

                // Crear un mapa para almacenar la información del cliente por ID
                Map<Integer, Cliente> clientesMap = new HashMap<>();
                for (Cliente cliente : clientes) {
                    clientesMap.put(cliente.getIdcliente(), cliente);
                }

                // Agregar la lista de clientes al atributo de solicitud
                request.setAttribute("clientes", clientes);

                // Agregar la lista de pedidos al atributo de solicitud
                request.setAttribute("pedidos", pedidosL);

                // Agregar el mapa de clientes al atributo de solicitud
                request.setAttribute("clientesMap", clientesMap);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminPedidos/pedidosAdmin.jsp");
                dispatcher.forward(request, response);
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
        String action = request.getParameter("action");

            if (action != null && action.equals("update")) {
                int idPedido = Integer.parseInt(request.getParameter("id"));
                String nuevoEstado = request.getParameter("estado");

                try {
                    // Aquí deberías llamar a tu DAO para actualizar el estado del pedido en la base de datos
                    DaoPedidos pedidoAd = new DaoPedidos();
                    Pedido pedido = pedidoAd.buscarPorID(idPedido);

                    // Verifica si se encontró el pedido
                    if (pedido != null) {
                        // Actualiza el estado del pedido
                        pedido.setEstado(nuevoEstado);

                        // Luego, guarda los cambios en la base de datos
                        pedidoAd.actualizar(pedido);

                        // Puedes configurar mensajes de éxito y redirigir a la página de pedidos
                        request.setAttribute("successMessage", "Estado del pedido actualizado exitosamente");
                       
                    } else {
                        // Maneja el caso en el que no se encuentra el pedido
                        request.setAttribute("errorMessage", "No se encontró el pedido con ID: " + idPedido);
                        
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                   
                }
            } else if (action.equals("delete")){
                try {
                    int idPedido = Integer.parseInt(request.getParameter("id"));
                    DaoDetallePedido detalle = new DaoDetallePedido();
                    DaoPedidos pedido = new DaoPedidos();
                    detalle.eliminar(idPedido);
                    pedido.eliminar(idPedido);
                    String successMessage = "Pedido Eliminado satisfactoriamente";
                    request.setAttribute("successMessage", successMessage);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
            
            
             doGet(request, response);
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
