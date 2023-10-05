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
