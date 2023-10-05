<%@page import="Models.Pedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../layouts/header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<c:if test="${empty usuario}">
    <%-- La sesión no está activa, redirige al inicio de sesión --%>
    <jsp:forward page="index.jsp" />
</c:if>
<div class="container mt-3">
    <h1>Editar Pedido PPOO${pedidoEdit.idpedido}</h1>
    <form action="AdminPedidosController?action=update" method="post">
        <input type="hidden" name="id" value="${pedidoEdit.idpedido}">
        <div class="mb-3">
            <label for="estado" class="form-label">Estado</label>
            <select class="form-select" id="estado" name="estado">
                <option value="pendiente" ${pedidoEdit.estado eq 'pendiente' ? 'selected' : ''}>Pendiente</option>
                <option value="en progreso" ${pedidoEdit.estado eq 'en progreso' ? 'selected' : ''}>En Progreso</option>
                <option value="finalizado" ${pedidoEdit.estado eq 'finalizado' ? 'selected' : ''}>Finalizado</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Actualizar Pedido</button>
        <a href="/PracticaRelacional/AdminPedidosController" class="btn btn-secondary "> Regresar</a>
    </form>
</div>
<jsp:include page="../layouts/footer.jsp"/>