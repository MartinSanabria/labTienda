/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllerAdmin;

import DAOModelsAdmin.DaoClientsAdmin;
import DAOModelsAdmin.DaoUserAdmin;
import MainController.DaoMain;
import Models.Cliente;
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

/**
 *
 * @author martinsanabria
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

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
            out.println("<title>Servlet UserController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserController at " + request.getContextPath() + "</h1>");
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
               DaoUserAdmin personaModel = new DaoUserAdmin();
                 //obtener los datos
                Usuario usuario = (Usuario) personaModel.buscarPorID(idpersona);
                request.setAttribute("userEdit", usuario);
                 //falta traer la persona de la base de datos
                 //y pasarlo como atributo
                RequestDispatcher dispatcher2=request.getRequestDispatcher("/Usuario/edit.jsp");
                dispatcher2.forward(request,response);
                }
           }
        } catch (Exception e) {
            e.printStackTrace(); //
        }
        
            DaoUserAdmin personaModel = new DaoUserAdmin();
            //ir al modelo para acceder a los datos
            //obtener los datos
            List<Usuario> usuario = personaModel.consultaGeneral();
            
            //pasarlos a la vista
            request.setAttribute("usuarios", usuario);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Usuario/userAdmin.jsp");

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
                
                 DaoUserAdmin user=new DaoUserAdmin();
                 // Recupera el valor seleccionado del elemento select
            if(request.getParameter("password").equals(request.getParameter("confirm-password"))){
                
                Usuario userNew=new Usuario(request.getParameter("nombre"),request.getParameter("apellido"),request.getParameter("telefono"),request.getParameter("email"),request.getParameter("password"));
                
                 DaoMain main = new DaoMain();
                 Usuario userFound = main.buscarPorCorreoUser(request.getParameter("email"));
                  if(userNew.getEmail().equals(userFound.getEmail())){
                    String errorMessage = "correo existente, ingrese uno nuevo.";
                    request.setAttribute("errorMessage", errorMessage); 
                }else {
                      
                   if (userNew.getTelefono().equals(userFound.getTelefono())){
                        String errorMessage = "El numero de telefono ya se encuentra registrado..";
                    request.setAttribute("errorMessage", errorMessage); 
                   } else {
                        user.agregar(userNew);
                        Usuario userRol = main.buscarPorCorreoUser(userNew.getEmail());
                        main.agregarRolUsuario(userRol);

                        String successMessage = "Empleado agregado satisfactoriamente";

                        request.setAttribute("successMessage", successMessage);
                        }
                    
                }
                
            } else {
                 String errorMessage = "Las contraseñas no coinciden";
                 
                 request.setAttribute("errorMessage", errorMessage);
            }
 
                 
            } else if(request.getParameter("action").equals("update")){
                
                int idpersona=Integer.parseInt(request.getParameter("id"));
                 DaoUserAdmin user=new DaoUserAdmin();
                //obtener los datos
                Usuario userUpdate = (Usuario) user.buscarPorID(idpersona);
                userUpdate.setNombre(request.getParameter("nombre"));
                userUpdate.setApellido(request.getParameter("apellido"));              
                userUpdate.setTelefono(request.getParameter("telefono"));
                userUpdate.setEmail(request.getParameter("email"));
                if(request.getParameter("password").equals(request.getParameter("confirm-password"))){
                userUpdate.setPassword(request.getParameter("password"));
             
                user.actualizar(userUpdate);
                
                String successMessage = "Empleado Actualizado satisfactoriamente";
                 
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
            DaoUserAdmin personaModel4= new DaoUserAdmin();
            main.borrarRolUsuario(idpersona);
            //obtener los datos
            personaModel4.eliminar(idpersona);
            
            String successMessage = "Empleado Eliminado satisfactoriamente";
                 
            request.setAttribute("successMessage", successMessage);
           
            
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
