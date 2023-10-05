<%@page import="Models.Usuario"%>
<%@page import="Models.Categoria"%>
<%@page import="Models.Proveedor"%>
<%@page import="java.util.List"%>
<%@page import="DAOModelsAdmin.DaoCategoriaAdmin"%>
<%@page import="DAOModelsAdmin.DaoProveedorAdmin"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../layouts/header.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<c:if test="${empty usuario}">
    <%-- La sesión no está activa, redirige al inicio de sesión --%>
    <jsp:forward page="index.jsp" />
</c:if>
<div class="container mt-3">
    <h1>Control de Pedidos</h1>
        <div class="table-responsive mt-3">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Pedido</th>
                        <th>Cliente</th>
                        <th>Fecha</th>
                        <th>total</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="pedido" items="${pedidos}">
                    <tr>
                        <td>${pedido.getIdpedido()}</td>
                        <td>${clientesMap[pedido.idcliente].nombres} ${clientesMap[pedido.idcliente].apellidos}</td>
                        <td>${pedido.getFecha()}</td>
                        <td>${pedido.getTotal()}</td>
                        <td>${pedido.getEstado()}</td>
                        <td>
                            <div class="d-flex">
                                <a href="AdminPedidosController?action=edit&id=${pedido.idpedido}" class="btn btn-dark me-1"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                    <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                  </svg>
                                </a>
                                <a href="AdminPedidosController?action=detallepedido&idPedido=${pedido.idpedido}" class="btn btn-secondary me-1"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye-fill" viewBox="0 0 16 16">
                                    <path d="M10.5 8a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0z"/>
                                    <path d="M0 8s3-5.5 8-5.5S16 8 16 8s-3 5.5-8 5.5S0 8 0 8zm8 3.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7z"/>
                                  </svg>
                                </a>
                                <form id="deleteForm" action="AdminPedidosController?action=delete&id=${pedido.idpedido}" method="post">
                                    <button type="button" class="btn btn-danger" onclick="confirmDelete();">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
                                            <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"/>
                                        </svg>
                                    </button>
                                </form>
                            </div>  
                        </td>
                    </tr>  
                    </c:forEach>    
                </tbody>
            </table>
        </div>
</div>

<c:if test="${not empty errorMessage}">
    <script>
       Swal.fire({
        position: 'top-end',
        icon: 'error',
        title: '${errorMessage}',
        showConfirmButton: false,
        timer: 1500
      })
    </script>
</c:if>
<c:if test="${not empty successMessage}">
    <script>
        Swal.fire({
           position: 'top-end',
           icon: 'success',
           title: '${successMessage}',
           showConfirmButton: false,
           timer: 1500
         });
    </script>
</c:if>
        <script>
    function confirmDelete() {
        Swal.fire({
            title: '¿Estás seguro?',
            text: "¡No podrás revertir esto!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sí, eliminarlo'
        }).then((result) => {
            if (result.isConfirmed) {
                // Si se confirma, envía el formulario
                document.getElementById("deleteForm").submit();
            }
        });
    }
</script>
<jsp:include page="../layouts/footer.jsp"/>