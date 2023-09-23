<%-- 
    Document   : create
    Created on : 14 sep. 2023, 09:28:27
    Author     : martinsanabria
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../layouts/header.jsp"/>
<div class="container mt-3">
        <form method="post" action="/PracticaRelacional/ProvidersController?action=create">
            <div class="mb-3 row">
                <label for="inputName" class="col-4 col-form-label">Ingrese el nombre</label>
                <div class="col-8">
                    <input type="text" class="form-control" name="nombre" id="inputName" placeholder="Nombre" required>
                </div>
            </div> 
            <div class="mb-3 row">
                <label for="inputName" class="col-4 col-form-label">Ingrese el telefono</label>
                <div class="col-8">
                    <input type="text" class="form-control" name="telefono" id="inputName" placeholder="54672456" required>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputName" class="col-4 col-form-label">Ingrese el correo electronico</label>
                <div class="col-8">
                    <input type="email" class="form-control" name="email" id="inputName" placeholder="example@gmail.com" required>
                </div>
            </div>
            <div class="mb-3 row">
                <div class="offset-sm-4 col-sm-8">
                    <button type="submit" class="btn btn-primary">Agregar</button>
                    <a href="/PracticaRelacional/ProvidersController" class="btn btn-secondary "> Regresar</a>
                </div>
            </div>
        </form>
    </div>
<jsp:include page="../layouts/footer.jsp"/>