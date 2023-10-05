/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MainController;

import Models.Cliente;
import Models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

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
            out.println("<title>Servlet MainController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MainController at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String email = request.getParameter("email");
        String password = hashContrasena(request.getParameter("password"));
        try {
            //Validar lo que el action sea login
             if(request.getParameter("action").equals("login")){
                 
                 //validar lo que es el usuario Empleado
                DaoMain userAdmin = new DaoMain();
                Usuario user = (Usuario) userAdmin.ConsultaUsuario(email, password);
                if(user.getEmail() != null && user.getPassword() != null){
                    if (user.getEmail().equals(request.getParameter("email")) && user.getPassword().equals(password)) {
                    // El usuario se encontró en la tabla de usuarios
                        HttpSession session = request.getSession(true);
                        session.setAttribute("usuario", user);
                    String successMessage = "Inicio de sesion satisfactorio.";

                    request.setAttribute("successMessage", successMessage);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("homeAdmin.jsp");
                    dispatcher.forward(request, response);
                    } 
                }
                //Validar lo que es el usuario Cliente
                // Si el usuario no se encontró en la tabla de usuarios, intenta buscarlo en la tabla de clientes
                    DaoMain daoClientes = new DaoMain(); // Supongamos que tienes una clase DaoClientes
                    Cliente cliente = daoClientes.consultaCliente(email, password);
                    
                    if(cliente.getCorreo()!= null && cliente.getClave()!= null){
                        if (cliente.getCorreo().equals(request.getParameter("email")) && cliente.getClave().equals(password)) {
                        // El usuario se encontró en la tabla de clientes
                        HttpSession session = request.getSession(true);
                        session.setAttribute("cliente", cliente);
                        String successMessage = "Inicio de sesion satisfactorio.";

                        request.setAttribute("successMessage", successMessage);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/Cliente/clientView.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        String errorMessage = "Credenciales incorrectas.";

                        request.setAttribute("errorMessage", errorMessage);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                        dispatcher.forward(request, response);
                        }
                    } else { 
                        String errorMessage = "Credenciales incorrectas.";

                        request.setAttribute("errorMessage", errorMessage);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                        dispatcher.forward(request, response);
                    }
                    
                
            }
             
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        
       
       
    }
    
    
    private String hashContrasena(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(contrasena.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%04x", b));
            }
            return sb.toString().substring(0, 20);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
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
