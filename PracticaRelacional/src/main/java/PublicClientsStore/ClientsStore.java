/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package PublicClientsStore;

import DAOModelsAdmin.DaoCategoriaAdmin;
import DAOModelsAdmin.DaoProductosAdmin;
import DAOModelsAdmin.DaoProveedorAdmin;
import Models.Categoria;
import Models.Cliente;
import Models.Producto;
import Models.Proveedor;
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
@WebServlet(name = "ClientsStore", urlPatterns = {"/ClientsStore"})
public class ClientsStore extends HttpServlet {

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
            out.println("<title>Servlet ClientsStore</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClientsStore at " + request.getContextPath() + "</h1>");
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
            Cliente usuario = (Cliente) session.getAttribute("cliente");

    if (session == null || usuario == null) {
            DaoProductosAdmin products = new DaoProductosAdmin();
            DaoProveedorAdmin providers = new DaoProveedorAdmin();
            DaoCategoriaAdmin categoriaFound = new DaoCategoriaAdmin();
            //ir al modelo para acceder a los datos
            //obtener los datos
            List<Producto> productos = products.consultaGeneral();

         Map<Integer, Map<String, String>> productosData = new HashMap<>();

        for (Producto producto : productos) {
            // Obtener nombre de proveedor
            if (producto.getExistencias() > 0){
                Proveedor proveedor = providers.buscarPorID(producto.getIdproveedor());
                String nombreProveedor = proveedor.getNombre_proveedor();

                // Obtener nombre de categoría
                Categoria categoria = categoriaFound.buscarPorID(producto.getIdcategoria());
                String nombreCategoria = categoria.getNombre_categoria();

                Map<String, String> productoData = new HashMap<>();
                productoData.put("nombreProveedor", nombreProveedor);
                productoData.put("nombreCategoria", nombreCategoria);

                productosData.put(producto.getIdproducto(), productoData);
            }
            
        }

            request.setAttribute("productosData", productosData);
            request.setAttribute("productos", productos);


            RequestDispatcher dispatcher = request.getRequestDispatcher("/PublicCliente/productos.jsp");

            // Envía la solicitud al dispatcher.
            dispatcher.forward(request, response);
    } else{
            DaoProductosAdmin products = new DaoProductosAdmin();
            DaoProveedorAdmin providers = new DaoProveedorAdmin();
            DaoCategoriaAdmin categoriaFound = new DaoCategoriaAdmin();
            //ir al modelo para acceder a los datos
            //obtener los datos
            List<Producto> productos = products.consultaGeneral();

         Map<Integer, Map<String, String>> productosData = new HashMap<>();

        for (Producto producto : productos) {
            // Obtener nombre de proveedor
            Proveedor proveedor = providers.buscarPorID(producto.getIdproveedor());
            String nombreProveedor = proveedor.getNombre_proveedor();

            // Obtener nombre de categoría
            Categoria categoria = categoriaFound.buscarPorID(producto.getIdcategoria());
            String nombreCategoria = categoria.getNombre_categoria();

            Map<String, String> productoData = new HashMap<>();
            productoData.put("nombreProveedor", nombreProveedor);
            productoData.put("nombreCategoria", nombreCategoria);

            productosData.put(producto.getIdproducto(), productoData);
        }

            request.setAttribute("productosData", productosData);
            request.setAttribute("productos", productos);


            RequestDispatcher dispatcher = request.getRequestDispatcher("/Cliente/productos.jsp");

            // Envía la solicitud al dispatcher.
            dispatcher.forward(request, response);
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
