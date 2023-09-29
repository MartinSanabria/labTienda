<%-- 
    Document   : create
    Created on : 14 sep. 2023, 09:28:27
    Author     : martinsanabria
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<style>
    /* Estilo para el ícono del ojo */
.toggle-password {
    float: right;
    margin-top: -25px;
    margin-right: 10px;
    cursor: pointer;
}

/* Estilo para cambiar el cursor al pasar el mouse sobre el ícono */
.toggle-password:hover {
    color: #007BFF; /* Puedes cambiar el color a tu preferencia */
}

</style>
<jsp:include page="../layouts/header.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<div class="container mt-3">
        <form method="post" action="/PracticaRelacional/UserController?action=create">
            <div class="mb-3 row">
                <label for="nombre" class="col-3 col-form-label">Ingrese el nombre</label>
                <div class="col-8">
                    <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre" required>
                </div>
            </div> 
            <div class="mb-3 row">
                <label for="apellido" class="col-3 col-form-label">Ingrese el apellido</label>
                <div class="col-8">
                    <input type="text" class="form-control" name="apellido" id="apellido" placeholder="apellido" required>
                </div>
            </div> 
           
            <div class="mb-3 row">
                <label for="telefono" class="col-3 col-form-label">Ingrese el telefono</label>
                <div class="col-8">
                    <input type="text" class="form-control" name="telefono" id="telefono" placeholder="54672456" required>
                </div>
            </div>    
            <div class="mb-3 row">
                <label for="email" class="col-3 col-form-label">Ingrese su correo</label>
                <div class="col-8">
                    <input type="email" class="form-control" name="email" id="email" placeholder="example@gmail.com" required>
                </div>
            </div> 
            <div class="mb-3 row">
                <label for="password" class="col-3 col-form-label">Ingrese su contraseña</label>
                <div class="col-8">
                    <input type="password" class="form-control" name="password" id="password" required>
                    <span toggle="#password" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                </div>
            </div>

            <div class="mb-3 row">
                <label for="confirm-password" class="col-3 col-form-label">Confirmar contraseña</label>
                <div class="col-8">
                    <input type="password" class="form-control" name="confirm-password" id="confirm-password" required>
                    <span toggle="#confirm-password" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                </div>
            </div>


            <div class="mb-3 row mt-3">
                <div class="offset-sm-5 col-sm-8">
                    <button type="submit" class="btn btn-primary">Agregar</button>
                    <a href="/PracticaRelacional/ClientsController" class="btn btn-secondary "> Regresar</a>
                </div>
            </div>
        </form>
    </div>

<script>
// JavaScript para alternar la visibilidad de la contraseña
document.querySelectorAll(".toggle-password").forEach(function (icon) {
    icon.addEventListener("click", function () {
        const passwordInput = document.querySelector(this.getAttribute("toggle"));
        if (passwordInput.type === "password") {
            passwordInput.type = "text";
        } else {
            passwordInput.type = "password";
        }
    });
});

</script>
<jsp:include page="../layouts/footer.jsp"/>