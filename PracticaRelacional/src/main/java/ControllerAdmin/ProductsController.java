/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllerAdmin;

import DAOModelsAdmin.DaoClientsAdmin;
import DAOModelsAdmin.DaoProductosAdmin;
import DAOModelsAdmin.DaoProveedorAdmin;
import Models.Cliente;
import Models.Producto;
import Models.Proveedor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author martinsanabria
 */
@WebServlet(name = "ProductsController", urlPatterns = {"/ProductsController"})
public class ProductsController extends HttpServlet {

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
            out.println("<title>Servlet ProductsController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductsController at " + request.getContextPath() + "</h1>");
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
        try {
           if(request.getParameter("action")!=null){
                if(request.getParameter("action").equals("edit")){
                int idpersona=Integer.parseInt(request.getParameter("id"));
               DaoProductosAdmin products = new DaoProductosAdmin();
                 //obtener los datos
                Producto producto = (Producto) products.buscarPorID(idpersona);
                request.setAttribute("productEdit", producto);
                 //falta traer la persona de la base de datos
                 //y pasarlo como atributo
                RequestDispatcher dispatcher2=request.getRequestDispatcher("/AdminProducts/edit.jsp");
                dispatcher2.forward(request,response);
                }
           }
        } catch (Exception e) {
            e.printStackTrace(); //
        }
        
            DaoProductosAdmin products = new DaoProductosAdmin();
            //ir al modelo para acceder a los datos
            //obtener los datos
            List<Producto> productos = products.consultaGeneral();
            
            //pasarlos a la vista
            request.setAttribute("productos", productos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminProducts/AdminProductos.jsp");

            // Envía la solicitud al dispatcher.
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
        if (request.getParameter("action") != null){
            
            if(request.getParameter("action").equals("create")){
                
                 DaoProveedorAdmin proveedor=new DaoProveedorAdmin();
                 Proveedor prov=new Proveedor(request.getParameter("nombre"),request.getParameter("telefono"),request.getParameter("email"));
                 proveedor.agregar(prov);
                 
            } else if(request.getParameter("action").equals("update")){
                
                int idpersona=Integer.parseInt(request.getParameter("id"));
                DaoProveedorAdmin personaModel3= new DaoProveedorAdmin();
                //obtener los datos
                Proveedor person = (Proveedor) personaModel3.buscarPorID(idpersona);
                person.setNombre_proveedor(request.getParameter("nombre"));
                person.setContacto( request.getParameter("email"));
                person.setTelefono(request.getParameter("telefono"));
                personaModel3.actualizar(person);

                //ir al modelo para acceder a los datos
                //obtener los datos


           }else if(request.getParameter("action").equals("delete")){
            int idpersona=Integer.parseInt(request.getParameter("id"));
            DaoProveedorAdmin personaModel4= new DaoProveedorAdmin();
            //obtener los datos
            personaModel4.eliminar(idpersona);
           
            
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
