<%-- 
    Document   : create
    Created on : 14 sep. 2023, 09:28:27
    Author     : martinsanabria
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../layouts/header.jsp"/>
<div class="container mt-3">
        <form method="post" action="/PracticaRelacional/ProductsController?action=create">
            <div class="mb-3 row">
                <label for="inputName" class="col-4 col-form-label">Ingrese el nombre del producto</label>
                <div class="col-8">
                    <input type="text" class="form-control" name="nombre" id="inputName" placeholder="Nombre" required>
                </div>
            </div> 
         
            <div class="mb-3 row">
                <div class="offset-sm-4 col-sm-8">
                    <button type="submit" class="btn btn-primary">Agregar</button>
                    <a href="/PracticaRelacional/ProductsController" class="btn btn-secondary "> Regresar</a>
                </div>
            </div>
        </form>
    </div>
<jsp:include page="../layouts/footer.jsp"/>