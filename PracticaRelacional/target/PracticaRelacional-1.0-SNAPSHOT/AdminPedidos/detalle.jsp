<%@page import="Models.Usuario"%>
<%@page import="Models.DetallePedido"%>
<%@page import="Models.Cliente"%>
<%@page import="java.util.List"%>
<%@page import="Models.Producto"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="../layouts/header.jsp" />
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<%
    // Verifica la existencia del cliente
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("index.jsp");
    }

    // Obtén los detalles y el mapa de productos del objeto request
    List<DetallePedido> detalleList = (List<DetallePedido>) request.getAttribute("detalleProductos");
    Map<Integer, Map<String, Producto>> productosL = (Map<Integer, Map<String, Producto>>) request.getAttribute("productosL");
%>

<div class="container mt-3">
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Producto</th>
                    <th>Imagen</th>
                    <th>Cantidad</th>
                    <th>Subtotal</th>
                </tr>
            </thead>
            <tbody>
               <c:forEach var="detalle" items="${detalleProductos}">
                    <tr>
                        <td>
                            <p>${productosL[detalle.idproducto].nombre_producto}</p>
                        </td>
                        <td>
                            <img src="${productosL[detalle.idproducto].imagen}" alt="Imagen del Producto" width="100">
                        </td>
                        <td><fmt:formatNumber value="${detalle.cantidad}" /></td>
                        <td><fmt:formatNumber value="${detalle.subtotal}" /></td>

                    </tr>
                </c:forEach>


            </tbody>
        </table>
    </div>
    
</div>

<c:if test="${not empty successMessage}">
    <script>
        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 2000,
            timerProgressBar: true,
            didOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        })

        Toast.fire({
            icon: 'success',
            title: '${successMessage}'
        })
    </script>
</c:if>
<jsp:include page="../layouts/public_footer.jsp" />
