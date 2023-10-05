<%@page import="DAOModelsAdmin.DaoProveedorAdmin"%>
<%@page import="Models.Categoria"%>
<%@page import="Models.Proveedor"%>
<%@page import="java.util.List"%>
<%@page import="DAOModelsAdmin.DaoCategoriaAdmin"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../layouts/header.jsp"/>

<div class="container mt-3">
    <form action="/PracticaRelacional/ProductsController?action=create" method="post">
        <div class="mb-3">
            <label for="nombre">Nombre:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required>
        </div>
        <div class="mb-3">
            <label for="descripcion">Descripción:</label>
           <input type="text" class="form-control" id="descripcion" name="descripcion" required>
        </div>
        <div class="mb-3">
            <label for="categoria">Categoría: </label>
            <select class="form-control" id="categoria" name="categoria" required>
                 <option value="" selected>Selecciona una opción</option>
                <c:forEach var="categoria" items="${categorias}">
                    <option value="${categoria.getIdcategoria()}">${categoria.getNombre_categoria()}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="proveedor">Proveedor: </label>
            <select class="form-control" id="proveedor" name="proveedor" required>
                 <option value="" selected>Selecciona una opción</option>
                <c:forEach var="proveedor" items="${proveedores}">
                    <option value="${proveedor.getIdproveedor()}">${proveedor.getNombre_proveedor()}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="precio_normal">Precio Normal:</label>
            <input type="number" class="form-control" id="precio_normal" min="0.00" step="0.01" name="precio_normal" required>
        </div>
        <div class="mb-3">
                <label for="ofertado">Ofertado:</label>
                <select class="form-control" id="ofertado" name="ofertado" required onchange="togglePrecioOferta()">
                    <option value="" selected>Selecciona una opción</option>
                    <option value="1">Si</option>
                    <option value="0">No</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="precio_oferta">Precio Oferta:</label>
                <input type="number" class="form-control" value="0.00" id="precio_oferta" min="0.00" step="0.01" name="precio_oferta" disabled>
            </div>
        <div class="mb-3">
            <label for="existencias">Existencias:</label>
            <input type="number" class="form-control" id="existencias" name="existencias" min="0" step="1" required>
        </div>
        
       <div class="mb-3">
            <label for="imagen">Direccion de la imagen: </label>
            <input type="text" class="form-control" id="imagen" name="imagen" required>
        </div>

        <div class="mb-3 row mt-3">
                <div class="offset-sm-5 col-sm-8">
                    <button type="submit" class="btn btn-primary">Agregar</button>
                    <a href="/PracticaRelacional/ProductsController" class="btn btn-secondary "> Regresar</a>
                </div>
            </div>
    </form>
</div>
 <script>
        function togglePrecioOferta() {
            var ofertadoDropdown = document.getElementById("ofertado");
            var precioOfertaInput = document.getElementById("precio_oferta");

            // Habilitar o deshabilitar el campo de precio de oferta según la opción seleccionada
            precioOfertaInput.disabled = ofertadoDropdown.value !== "1";
        }
        
        
    </script>
<jsp:include page="../layouts/footer.jsp"/>
