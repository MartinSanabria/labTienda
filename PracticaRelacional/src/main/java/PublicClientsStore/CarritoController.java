/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package PublicClientsStore;

import DAOModelsAdmin.DaoProductosAdmin;
import Models.Carrito;
import Models.Cliente;
import Models.Producto;
import Models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "CarritoController", urlPatterns = {"/CarritoController"})
public class CarritoController extends HttpServlet {

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
            out.println("<title>Servlet CarritoController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CarritoController at " + request.getContextPath() + "</h1>");
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
          // Obtén el ID del producto que se está agregando al carrito
           
            try {
                if(request.getParameter("action")!=null){
                    if(request.getParameter("action").equals("add")){
                    int idProducto = Integer.parseInt(request.getParameter("idProducto"));
                    DaoProductosAdmin products = new DaoProductosAdmin();
                   // Obtén la información completa del producto (puedes tener un método en tu DAO o servicio)
                   Producto producto = products.buscarPorID(idProducto);

                   // Obtén la lista de productos en el carrito desde la sesión
                   HttpSession session = request.getSession(true);
                   List<Carrito> carrito = (List<Carrito>) session.getAttribute("carrito");
                   
                   // Si la lista de carrito aún no existe, créala
                   if (carrito == null) {
                       carrito = new ArrayList<>();
                   }
                   boolean productoExistente = false;
                    for (Carrito item : carrito) {
                        if (item.getIdproductoC() == (producto.getIdproducto())) {
                            // El producto ya existe en el carrito, actualiza la cantidad
                            if (producto.getExistencias() > item.getCantidad()){
                                item.setCantidad(item.getCantidad() + 1);
                                productoExistente = true;
                                break;
                            }
                            else {
                                productoExistente = true;
                                 String errorMessage = "Existencias insuficientes.";
                                 request.setAttribute("errorMessage", errorMessage);
                                break;
                               
                            }
                            
                        }
                    }
                    
                     if (productoExistente==false) {
                        // Paso 5: El producto no existe en el carrito, crea un nuevo objeto CarritoItem y agrégalo
                        Carrito carrito1 = new Carrito(producto.getIdproducto(),producto.getNombre_producto(),producto.getPrecio_normal(),
                           producto.getOfertado(),producto.getPrecio_oferta(),1,producto.getImagen());
                        if(producto.getExistencias() >= carrito1.getCantidad()){
                            carrito.add(carrito1);
                            String successMessage = "Producto Agregado al carrito.";
                            request.setAttribute("successMessage", successMessage);
                        }
                        else { 
                            String errorMessage = "Existencias insuficientes.";
                            request.setAttribute("errorMessage", errorMessage);
                        }
                        
                    }
                   
                session.setAttribute("carrito", carrito);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ClientsStore");

                // Envía la solicitud al dispatcher.
                dispatcher.forward(request, response);
                } else if(request.getParameter("action").equals("view")) {
                    HttpSession session = request.getSession(false);
                    Cliente cliente = (Cliente) session.getAttribute("cliente"); 
                    if (session == null || cliente == null) {
                        List<Carrito> carrito = (List<Carrito>) session.getAttribute("carrito");
                        // Actualiza la lista de carrito en la sesión
                        
                         session.setAttribute("carrito", carrito);
                         RequestDispatcher dispatcher2=request.getRequestDispatcher("Carrito/carrito.jsp");
                         dispatcher2.forward(request,response);

                    
                    } else{
                        List<Carrito> carrito = (List<Carrito>) session.getAttribute("carrito");
                         session.setAttribute("carrito", carrito);
                         RequestDispatcher dispatcher2=request.getRequestDispatcher("Carrito/carritoAuth.jsp");
                         dispatcher2.forward(request,response);
                    }

               
                } else if (request.getParameter("action").equals("login")){
                    HttpSession session = request.getSession(false);

                        List<Carrito> carrito = (List<Carrito>) session.getAttribute("carrito");
                         session.setAttribute("carrito", carrito);
                         RequestDispatcher dispatcher2=request.getRequestDispatcher("login.jsp");
                         dispatcher2.forward(request,response);
                         
                } 
                
                
            }
           
            } catch (Exception e) {
                e.printStackTrace();
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
        String action = request.getParameter("action");

            if ("remove".equals(action)) {
                
                HttpSession session = request.getSession(true);
                List<Carrito> carrito = (List<Carrito>) session.getAttribute("carrito");

                if (carrito != null) {
                    int index = Integer.parseInt(request.getParameter("index"));

                    if (carrito != null && index >= 0 && index < carrito.size()) {
                        carrito.remove(index);
                        session.setAttribute("carrito", carrito);
                        // Después de eliminar el elemento
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/CarritoController?action=view");
                        dispatcher.forward(request, response);


                    }
                }

                
            } else if ("actualizarCarrito".equals(action)){
                this.actualizarCarrito(request, response);
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

    
   private void actualizarCarrito(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
       
       try {
            // Obtener los datos del producto desde la solicitud HTTP
            int productoId = Integer.parseInt(request.getParameter("productoId"));
            String cantidadStr = request.getParameter("cantidad");
            DaoProductosAdmin products = new DaoProductosAdmin();
           // Obtén la información completa del producto (puedes tener un método en tu DAO o servicio)
           Producto producto = products.buscarPorID(productoId);

            // Convertir la cantidad a un entero (debes validar que sea un número válido)
            int nuevaCantidad = Integer.parseInt(cantidadStr);

            // Obtener la sesión actual o crear una nueva si no existe
            HttpSession session = request.getSession(true);

            // Obtener la lista de productos del carrito desde la sesión
            List<Carrito> carrito = (List<Carrito>) session.getAttribute("carrito");


            // Si la lista de productos del carrito aún no existe en la sesión, no hay nada que actualizar
            if (carrito == null) {
                return;
            }

            // Buscar el producto en el carrito
            for (Carrito item : carrito) {

                if (item.getIdproductoC() == productoId) {
                    if(producto.getExistencias() < nuevaCantidad){
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        response.setContentType("application/json");
                        response.getWriter().write("{\"message\":\"Existencias insuficientes.\", \"insufficientStock\": true}");


                        break;
                    }else if (producto.getExistencias() >= nuevaCantidad) {
                        item.setCantidad(nuevaCantidad);
                        // Actualizar la sesión con la lista de productos actualizada
                        session.setAttribute("carrito", carrito);
                        response.setStatus(HttpServletResponse.SC_OK);
                        // Enviar una respuesta al cliente (puedes enviar JSON u otra respuesta según tus necesidades)
                        response.setContentType("application/json");
                        response.getWriter().write("{\"message\":\"Cantidad del producto actualizada con éxito\"}");
                        break;
                    }


                }
            }
           
       } catch (Exception e) {
            e.printStackTrace();
        }

       

        
    }
}
