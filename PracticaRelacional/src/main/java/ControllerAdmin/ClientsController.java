/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllerAdmin;

import DAOModelsAdmin.DaoClientsAdmin;
import DAOModelsAdmin.DaoProveedorAdmin;
import MainController.DaoMain;
import Models.Cliente;
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
@WebServlet(name = "ClientsController", urlPatterns = {"/ClientsController"})
public class ClientsController extends HttpServlet {

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
            out.println("<title>Servlet ClientsController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClientsController at " + request.getContextPath() + "</h1>");
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
        if(request.getParameter("action").equals("create")){
             String successMessage = "Se registro satisfactoriamente";
            request.setAttribute("successMessage", successMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
       
        response.sendRedirect("index.jsp");
    } else {
         try {
           if(request.getParameter("action")!=null){
                if(request.getParameter("action").equals("edit")){
                int idpersona=Integer.parseInt(request.getParameter("id"));
               DaoClientsAdmin personaModel = new DaoClientsAdmin();
                 //obtener los datos
                Cliente client = (Cliente) personaModel.buscarPorID(idpersona);
                request.setAttribute("clientEdit", client);
                 //falta traer la persona de la base de datos
                 //y pasarlo como atributo
                RequestDispatcher dispatcher2=request.getRequestDispatcher("/AdminClients/edit.jsp");
                dispatcher2.forward(request,response);
                }
           }
        } catch (Exception e) {
            e.printStackTrace(); //
        }
        
            DaoClientsAdmin personaModel = new DaoClientsAdmin();
            //ir al modelo para acceder a los datos
            //obtener los datos
            List<Cliente> clientes = personaModel.consultaGeneral();
            
            //pasarlos a la vista
            request.setAttribute("clientes", clientes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminClients/AdminClients.jsp");

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
        try {
            
         if (request.getParameter("action") != null){
            
            if(request.getParameter("action").equals("create")){
                
                 DaoClientsAdmin client=new DaoClientsAdmin();
                 // Recupera el valor seleccionado del elemento select
            if(request.getParameter("password").equals(request.getParameter("confirm-password"))){
                
                Cliente client2=new Cliente(request.getParameter("nombre"),request.getParameter("apellido"),request.getParameter("sexo"),request.getParameter("direccion"),request.getParameter("telefono"),
                                            request.getParameter("pais"),request.getParameter("password"),request.getParameter("email"));
                
                 DaoMain main = new DaoMain();
                 Cliente clientFound = main.buscarPorCorreo(request.getParameter("email"));
                  if(client2.getCorreo().equals(clientFound.getCorreo())){
                    String errorMessage = "correo existente, ingrese uno nuevo.";
                    request.setAttribute("errorMessage", errorMessage); 
                }else {
                     
                    if (client2.getTelefono().equals(clientFound.getTelefono())){
                        String errorMessage = "El numero de telefono ya se encuentra registrado..";
                        request.setAttribute("errorMessage", errorMessage); 
                    } else {
                            client.agregar(client2);
                            Cliente clientrol = main.buscarPorCorreo(client2.getCorreo());
                            main.agregarRolCliente(clientrol);

                            String successMessage = "Cliente agregado satisfactoriamente";

                            request.setAttribute("successMessage", successMessage);
                        }
                    
                    }
                
            } else {
                 String errorMessage = "Las contraseñas no coinciden";
                 
                 request.setAttribute("errorMessage", errorMessage);
            }

                 
                 
                 
            } else if(request.getParameter("action").equals("update")){
                
                int idpersona=Integer.parseInt(request.getParameter("id"));
                 DaoClientsAdmin client=new DaoClientsAdmin();
                //obtener los datos
                Cliente person = (Cliente) client.buscarPorID(idpersona);
                person.setNombres(request.getParameter("nombre"));
                person.setApellidos(request.getParameter("apellido"));
                person.setSexo(request.getParameter("sexo"));
                person.setTelefono(request.getParameter("telefono"));
                person.setDireccion(request.getParameter("direccion"));
                person.setPais(request.getParameter("pais"));
                person.setCorreo(request.getParameter("email"));
                if(request.getParameter("password").equals(request.getParameter("confirm-password"))){
                person.setClave(request.getParameter("password"));
             
                client.actualizar(person);
                String successMessage = "Cliente Actualizado satisfactoriamente";
                 
                request.setAttribute("successMessage", successMessage);
                } else {
                     String errorMessage = "Las contraseñas no coinciden";

                     request.setAttribute("errorMessage", errorMessage);
                }
                

                //ir al modelo para acceder a los datos
                //obtener los datos


           }else if(request.getParameter("action").equals("delete")){
            int idpersona=Integer.parseInt(request.getParameter("id"));
            DaoMain main = new DaoMain();
            DaoClientsAdmin personaModel4= new DaoClientsAdmin();
            main.borrarRolCliente(idpersona);
            //obtener los datos
            personaModel4.eliminar(idpersona);
             String successMessage = "Cliente Eliminado satisfactoriamente";
                 
            request.setAttribute("successMessage", successMessage);
           
            
            }
        }
            
       
           doGet(request,response);
        } catch (Exception e) {
            
            e.printStackTrace();
        }
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
