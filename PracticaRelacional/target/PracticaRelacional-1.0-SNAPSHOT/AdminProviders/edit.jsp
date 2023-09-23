<%-- 
    Document   : edit
    Created on : 14 sep. 2023, 08:15:56
    Author     : martinsanabria
--%>

<%@page import="Models.Proveedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../layouts/header.jsp"/>

<div class="container mt-3">
        <form method="post" action="ProvidersController?action=update&id=${providerEdit.getIdproveedor()}">          
            <div class="mb-3 row">
                <label for="nombre" class="col-4 col-form-label">Ingrese el nombre</label>
                <div class="col-8">
                    <input type="text" value="${providerEdit.getNombre_proveedor()}" class="form-control" name="nombre" id="nombre" required>
                </div>
            </div>  
             <div class="mb-3 row">
                <label for="email" class="col-4 col-form-label">Ingrese el correo electronico</label>
                <div class="col-8">
                    <input type="email" value="${providerEdit.getContacto()}" class="form-control" name="email" id="email" required>
                </div>
            </div>
             <div class="mb-3 row">
                <label for="telefono" class="col-4 col-form-label">Ingrese el telefono</label>
                <div class="col-8">
                    <input type="text" value="${providerEdit.getTelefono()}" class="form-control" name="telefono" id="telefono" required>
                </div>
            </div>
            <div class="mb-3 row">
                <div class="offset-sm-4 col-sm-8">
                    <button type="submit" class="btn btn-primary">Actualizar</button>
                     <a href="/PracticaRelacional/ProvidersController" class="btn btn-secondary "> Regresar</a>
                </div>
            </div>
        </form>
    </div>
<jsp:include page="../layouts/footer.jsp"/>