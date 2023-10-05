/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllerAdmin;

import DAOModelsAdmin.DaoCategoriaAdmin;
import DAOModelsAdmin.DaoProductosAdmin;
import DAOModelsAdmin.DaoProveedorAdmin;
import Models.Categoria;
import Models.Producto;
import Models.Proveedor;
import Models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "CategoriesController", urlPatterns = {"/CategoriesController"})
public class CategoriesController extends HttpServlet {

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
            out.println("<title>Servlet CategoriesController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoriesController at " + request.getContextPath() + "</h1>");
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
    }else {
             try {
           if(request.getParameter("action")!=null){
                if(request.getParameter("action").equals("edit")){
                int idcategoria=Integer.parseInt(request.getParameter("id"));
               DaoCategoriaAdmin categoria = new DaoCategoriaAdmin();
                 //obtener los datos
                Categoria category = (Categoria) categoria.buscarPorID(idcategoria);
                request.setAttribute("categoriaEdit", category);
                 //falta traer la persona de la base de datos
                 //y pasarlo como atributo
                RequestDispatcher dispatcher2=request.getRequestDispatcher("/AdminCategories/edit.jsp");
                dispatcher2.forward(request,response);
                }
           }
        } catch (Exception e) {
            e.printStackTrace(); //
        }
        
            DaoCategoriaAdmin categoria = new DaoCategoriaAdmin();
            //ir al modelo para acceder a los datos
            //obtener los datos
            List<Categoria> category = categoria.consultaGeneral();
            
            //pasarlos a la vista
            request.setAttribute("categorias", category);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminCategories/AdminCategorias.jsp");

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
        if (request.getParameter("action") != null){
            
            if(request.getParameter("action").equals("create")){
                
                 DaoCategoriaAdmin category=new DaoCategoriaAdmin();
                 Categoria cat=new Categoria(request.getParameter("nombre"));
                
                 category.agregar(cat);
                 String successMessage = "Categoria agregada satisfactoriamente";

                request.setAttribute("successMessage", successMessage);
                 
            } else if(request.getParameter("action").equals("update")){
                
                int idcategoria=Integer.parseInt(request.getParameter("id"));
               DaoCategoriaAdmin category=new DaoCategoriaAdmin();
                //obtener los datos
                Categoria catego = (Categoria) category.buscarPorID(idcategoria);
                catego.setNombre_categoria(request.getParameter("nombre"));
                category.actualizar(catego);
                 String successMessage = "Categoria actualizada satisfactoriamente";

                request.setAttribute("successMessage", successMessage);
                //ir al modelo para acceder a los datos
                //obtener los datos


           }else if(request.getParameter("action").equals("delete")){
                int idcategoria = Integer.parseInt(request.getParameter("id"));
                DaoCategoriaAdmin category = new DaoCategoriaAdmin();
                DaoProductosAdmin product = new DaoProductosAdmin();

                // Consultar productos por categoría
                List<Producto> productos = product.consultarPorCategoria(idcategoria);

                if (productos.isEmpty()) {
                    // No hay productos asociados, se puede eliminar la categoría
                    category.eliminar(idcategoria);
                    String successMessage = "Categoria eliminada satisfactoriamente";
                    request.setAttribute("successMessage", successMessage);
                } else {
                    // Hay productos asociados, no se puede eliminar la categoría
                    String errorMessage = "Accion denegada, existen productos en esta categoria.";
                    request.setAttribute("errorMessage", errorMessage);
                }
            }
        }
            
       
           doGet(request,response);
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
