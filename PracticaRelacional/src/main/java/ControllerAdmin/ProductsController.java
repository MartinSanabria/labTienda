/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllerAdmin;

import DAOModelsAdmin.DaoCategoriaAdmin;
import DAOModelsAdmin.DaoClientsAdmin;
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
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuario");

    if (session == null || usuario == null) {
        // Si no hay sesión o el usuario no está autenticado, redirige a la página de inicio de sesión
        response.sendRedirect("index.jsp");
    }else {
        try {
           if(request.getParameter("action")!=null){
                if(request.getParameter("action").equals("edit")){
                int idproducto=Integer.parseInt(request.getParameter("id"));
               DaoProductosAdmin products = new DaoProductosAdmin();
                 //obtener los datos
                Producto producto = (Producto) products.buscarPorID(idproducto);
                request.setAttribute("productEdit", producto);
                
                DaoProveedorAdmin providers = new DaoProveedorAdmin();
                DaoCategoriaAdmin categoria = new DaoCategoriaAdmin();
                    //ir al modelo para acceder a los datos
                    //obtener los datos

                List<Proveedor> proveedores = providers.consultaGeneral();
                List<Categoria> categorias = categoria.consultaGeneral();


                request.setAttribute("proveedores", proveedores);
                request.setAttribute("categorias", categorias);
                 //falta traer la persona de la base de datos
                 //y pasarlo como atributo
                RequestDispatcher dispatcher2=request.getRequestDispatcher("/AdminProducts/edit.jsp");
                dispatcher2.forward(request,response);
                } else if (request.getParameter("action").equals("new")){
                    
                    DaoProveedorAdmin providers = new DaoProveedorAdmin();
                    DaoCategoriaAdmin categoria = new DaoCategoriaAdmin();
                    //ir al modelo para acceder a los datos
                    //obtener los datos

                    List<Proveedor> proveedores = providers.consultaGeneral();
                    List<Categoria> categorias = categoria.consultaGeneral();


                    request.setAttribute("proveedores", proveedores);
                    request.setAttribute("categorias", categorias);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminProducts/create.jsp");

            // Envía la solicitud al dispatcher.
            dispatcher.forward(request, response);
                }
           }
        } catch (Exception e) {
            e.printStackTrace(); //
        }
        
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


            RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminProducts/AdminProductos.jsp");

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
                DaoProductosAdmin productNew = new DaoProductosAdmin();
                DaoCategoriaAdmin categoriaFound = new DaoCategoriaAdmin();
                DaoProveedorAdmin proveedorFound = new DaoProveedorAdmin();
                if(!request.getParameter("categoria").isEmpty()){
                    
                        if(!request.getParameter("proveedor").isEmpty()){

                            if(request.getParameter("precio_oferta") == null  || request.getParameter("precio_oferta").isEmpty()){
                                double precio_oferta = 0.00;
                                
                                Categoria catego = categoriaFound.buscarPorID(Integer.parseInt(request.getParameter("categoria")));
                                Proveedor prov = proveedorFound.buscarPorID(Integer.parseInt(request.getParameter("proveedor")));
                                
                                Producto 
                                productoNuevo = new Producto(catego.getIdcategoria(), prov.getIdproveedor(), request.getParameter("nombre"),
                                        Double.valueOf(request.getParameter("precio_normal")), Integer.valueOf(request.getParameter("ofertado")), precio_oferta,
                                        Integer.parseInt(request.getParameter("existencias")), request.getParameter("descripcion"), request.getParameter("imagen"));
                            productNew.agregar(productoNuevo);

                            String successMessage = "Producto agregado satisfactoriamente";

                            request.setAttribute("successMessage", successMessage);
                            } else {
                                Categoria catego = categoriaFound.buscarPorID(Integer.parseInt(request.getParameter("categoria")));
                                Proveedor prov = proveedorFound.buscarPorID(Integer.parseInt(request.getParameter("proveedor")));
                                
                                Producto 
                                productoNuevo = new Producto(catego.getIdcategoria(), prov.getIdproveedor(), request.getParameter("nombre"),
                                        Double.valueOf(request.getParameter("precio_normal")), Integer.valueOf(request.getParameter("ofertado")), Double.parseDouble(request.getParameter("precio_oferta")),
                                        Integer.parseInt(request.getParameter("existencias")), request.getParameter("descripcion"), request.getParameter("imagen"));
                            productNew.agregar(productoNuevo);

                            String successMessage = "Producto agregado satisfactoriamente";

                            request.setAttribute("successMessage", successMessage);
                            }

                    } else {
                        String errorMessage = "Error de seleccion de proveedores";

                        request.setAttribute("errorMessage", errorMessage);
                    }
                     
                } else {
                    String errorMessage = "Error de seleccion de categorias";

                    request.setAttribute("errorMessage", errorMessage);
                }
               
                
            } else if(request.getParameter("action").equals("update")){
                
                int idproductos=Integer.parseInt(request.getParameter("id"));
                DaoProductosAdmin productUpdate = new DaoProductosAdmin();
                Producto dataProduct = productUpdate.buscarPorID(idproductos);
                dataProduct.setNombre_producto(request.getParameter("nombre"));
                dataProduct.setDescripcion(request.getParameter("descripcion"));
                DaoCategoriaAdmin categoriaFound = new DaoCategoriaAdmin();
                DaoProveedorAdmin proveedorFound = new DaoProveedorAdmin();
                if(!request.getParameter("categoria").isEmpty()){
                    
                        if(!request.getParameter("proveedor").isEmpty()){
                            

                            if(request.getParameter("precio_oferta") == null  || request.getParameter("precio_oferta").isEmpty()){
                                double precio_oferta = 0.00;
                                
                                Categoria catego = categoriaFound.buscarPorID(Integer.parseInt(request.getParameter("categoria")));
                                Proveedor prov = proveedorFound.buscarPorID(Integer.parseInt(request.getParameter("proveedor")));
        
                                dataProduct.setIdcategoria(catego.getIdcategoria());
                                dataProduct.setIdproveedor(prov.getIdproveedor());
                                dataProduct.setPrecio_normal(Double.parseDouble(request.getParameter("precio_normal")));
                                dataProduct.setOfertado(Integer.parseInt(request.getParameter("ofertado")));
                                dataProduct.setPrecio_oferta(precio_oferta);
                                dataProduct.setExistencias(Integer.parseInt(request.getParameter("existencias")));
                                dataProduct.setImagen(request.getParameter("imagen"));

                                productUpdate.actualizar(dataProduct);
                            String successMessage = "Producto actualizado satisfactoriamente";

                            request.setAttribute("successMessage", successMessage);
                            } else {
                                Categoria catego = categoriaFound.buscarPorID(Integer.parseInt(request.getParameter("categoria")));
                                Proveedor prov = proveedorFound.buscarPorID(Integer.parseInt(request.getParameter("proveedor")));
        
                                dataProduct.setIdcategoria(catego.getIdcategoria());
                                dataProduct.setIdproveedor(prov.getIdproveedor());
                                dataProduct.setPrecio_normal(Double.parseDouble(request.getParameter("precio_normal")));
                                dataProduct.setOfertado(Integer.parseInt(request.getParameter("ofertado")));
                                dataProduct.setPrecio_oferta(Double.parseDouble(request.getParameter("precio_oferta")));
                                dataProduct.setExistencias(Integer.parseInt(request.getParameter("existencias")));
                                dataProduct.setImagen(request.getParameter("imagen"));

                                productUpdate.actualizar(dataProduct);
                            String successMessage = "Producto actualizado satisfactoriamente";

                            request.setAttribute("successMessage", successMessage);
                            }

                    } else {
                        String errorMessage = "Error de seleccion de proveedores";

                        request.setAttribute("errorMessage", errorMessage);
                    }
                     
                } else {
                    String errorMessage = "Error de seleccion de categorias";

                    request.setAttribute("errorMessage", errorMessage);
                }

           }else if(request.getParameter("action").equals("delete")){
            int idproducto=Integer.parseInt(request.getParameter("id"));
           
            DaoProductosAdmin delete = new DaoProductosAdmin();
            
            delete.eliminar(idproducto);
            String successMessage = "Producto Eliminado satisfactoriamente";
                 
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
