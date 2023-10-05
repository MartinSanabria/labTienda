<%@page import="Models.Pedido"%>
<%@page import="Models.DetallePedido"%>
<%@page import="Models.Cliente"%>
<%@page import="Models.Producto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="../layouts/public_header.jsp" />
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<%
    // Verifica la existencia del cliente
    Cliente cliente = (Cliente) session.getAttribute("cliente");
    if (cliente == null) {
        response.sendRedirect("index.jsp");
    }

    // Obtén los detalles, el pedido y el mapa de productos del objeto request
    Pedido pedido = (Pedido) request.getAttribute("pedido");
    List<DetallePedido> detalleList = (List<DetallePedido>) request.getAttribute("detalleProductos");
    Map<Integer, Map<String, Producto>> productosL = (Map<Integer, Map<String, Producto>>) request.getAttribute("productosL");
%>


<div class="container mt-3">
    <h1>Bienvenido <%= cliente.getNombres() %> <%=cliente.getApellidos() %></h1>
    <div class="row">
        <div class="col-md-8">
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
                                        <img src="${productosL[detalle.idproducto].imagen}" alt="imagen" width="100">
                                    </td>
                                    <td><fmt:formatNumber value="${detalle.cantidad}" /></td>
                                    <td><fmt:formatNumber value="${detalle.subtotal}" /></td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
        </div>
        
        <div class="col-md-4">
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">Información del Pedido</h5>
                        <p class="card-text">ID del Pedido: ${pedido.getIdpedido()}</p>
                        <p class="card-text">Fecha: <fmt:formatDate value="${pedido.getFecha()}" pattern="dd-MM-yyyy" /></p>
                        <p class="card-text">Estado: ${pedido.getEstado()}</p>
                        <hr/>
                        <p class="card-text">Monto Total: $<fmt:formatNumber value="${pedido.getTotal()}" type="number" pattern="#,##0.00" /></p>
                        <c:if test="${not empty cliente}">
                             <button class="btn btn-success" onclick="window.print()">Imprimir</button>
                        </c:if>
                    </div>
                </div>
        </div>
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
