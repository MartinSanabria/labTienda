<%@page import="Models.Cliente"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../layouts/public_header.jsp" />
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<%
    Cliente cliente = (Cliente) session.getAttribute("cliente");

    if (session == null || cliente == null) {
        // Si no hay sesión o el usuario no está autenticado, redirige a la página de inicio de sesión
        response.sendRedirect("index.jsp");
    }
%>
<div class="container mt-3">
    <h1>Bienvenido <%= cliente.getNombres() %> <%=cliente.getApellidos() %></h1>
    
          <div class="table-responsive">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Pedido</th>
                        <th>Fecha</th>
                        <th>Total</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="pedido" items="${pedidos}">
                    <tr>
                        <td>PP00${pedido.getIdpedido()}</td>
                        <td>${pedido.getFecha()}</td>
                        <td>${pedido.getTotal()}</td>
                        <td>${pedido.getEstado()}</td>
                            
                        <td>
                            <div class="d-flex">
                                <a href="PedidoController?action=detallepedido&idPedido=${pedido.getIdpedido()}" class="btn btn-dark me-1"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye-fill" viewBox="0 0 16 16">
                                    <path d="M10.5 8a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0z"/>
                                    <path d="M0 8s3-5.5 8-5.5S16 8 16 8s-3 5.5-8 5.5S0 8 0 8zm8 3.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7z"/>
                                  </svg>
                                </a>
                            </div>  
                        </td>
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
