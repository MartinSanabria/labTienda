<%-- 
    Document   : index
    Created on : 21 sep. 2023, 17:22:13
    Author     : martinsanabria
--%>

<%@page import="Models.Usuario"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="./layouts/header.jsp" />
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    if (session == null || usuario == null) {
        // Si no hay sesión o el usuario no está autenticado, redirige a la página de inicio de sesión
        response.sendRedirect("index.jsp");
    }
%>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<div class="container mt-3">
    <h1>Pagina inicial</h1>
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
<jsp:include page="./layouts/footer.jsp" />