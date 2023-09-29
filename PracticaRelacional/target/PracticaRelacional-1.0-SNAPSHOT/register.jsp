<%-- 
    Document   : register
    Created on : 26 sep. 2023, 09:44:25
    Author     : martinsanabria
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Registro</title>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
</head>
<body>
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
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<div class="container mt-3">
    <div class="container mt-4 text-center mb-4">
        <h3>Ingrese sus datos para registrarse.</h3>
    </div>
        <form method="post" action="">
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
                <label for="sexo" class="col-3 col-form-label">Ingrese su sexo</label>
                <div class="col-8">
                    <input type="text" class="form-control" name="sexo" id="sexo"  required>
                </div>
            </div> 
           
            <div class="mb-3 row">
                <label for="telefono" class="col-3 col-form-label">Ingrese el telefono</label>
                <div class="col-8">
                    <input type="text" class="form-control" name="telefono" id="telefono" placeholder="54672456" required>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="direccion" class="col-3 col-form-label">Ingrese su dirección</label>
                <div class="col-8">
                    <input type="text" class="form-control" name="direccion" id="direccion"  required>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="pais" class="col-3 col-form-label">Ingrese el pais</label>
                <div class="col-8">
                    <input type="text" class="form-control" name="pais" id="pais" required>
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
                    <button type="submit" class="btn btn-success">Registrarse</button>
                    <a href="index.jsp" class="btn btn-secondary "> Regresar</a>
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
    <!-- Agrega el enlace al archivo JavaScript de Bootstrap (jQuery y Popper.js son necesarios) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.min.js"></script>
</body>
</html>

