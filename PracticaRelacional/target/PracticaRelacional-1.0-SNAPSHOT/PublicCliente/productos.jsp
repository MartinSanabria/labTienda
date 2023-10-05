<%@page import="Models.Cliente"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="../layouts/header_base.jsp" />
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<style>
 .card-img-top {
  object-fit: contain;
  max-width: 100%;
  max-height: 200px; 
}

</style>

<div class="container mt-3">
    <div class="row mt-3">
        <c:forEach var="producto" items="${productos}">
            <div class="col-md-3">
                <div class="card text-center" style="width: 20rem;">
                    <!-- Muestra el precio de oferta como un span en la parte superior si es igual a 1 -->
                    <c:if test="${producto.ofertado eq 1}">
                        <span class="badge bg-danger" style="position: absolute; top: 0; left: 0; margin: 5px;">
                             Oferta: $  <fmt:formatNumber value="${producto.precio_oferta}" type="number" pattern="#,##0.00" />
                        </span>
                    </c:if>
                    <img src="${producto.imagen}" class="card-img-top mt-2 rounded" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">${producto.nombre_producto}</h5>
                        <p class="card-text">${producto.descripcion}</p>
                        <a href="/PracticaRelacional/CarritoController?action=add&idProducto=${producto.idproducto}" class="btn btn-primary">Adquirir</a>
                    </div>
                    <div class="card-footer">
                        <small class="text-muted">Precio: $<fmt:formatNumber value="${producto.precio_normal}" type="number" pattern="#,##0.00" /> </small>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>


<c:if test="${not empty successMessage}">
    <script>
        const Toast = Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 1200,
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
    
<c:if test="${not empty errorMessage}">
    <script>
        const Toast = Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 1200,
          timerProgressBar: true,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
        })

        Toast.fire({
          icon: 'error',
          title: '${errorMessage}'
        })
    </script>
</c:if>

<jsp:include page="../layouts/public_footer.jsp" />
