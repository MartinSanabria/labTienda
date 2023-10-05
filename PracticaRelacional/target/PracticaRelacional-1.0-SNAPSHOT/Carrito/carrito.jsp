<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.Cliente"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="../layouts/header_base.jsp" />
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<div class="container mt-3">
    <c:if test="${not empty carrito}">    
        <div class="row">
            <div class="col-md-8">
                <div class="table-responsive">
                    <table class="table table-striped
                    table-hover	
                    table-borderless
                    align-middle text-center">
                        <thead class="table-dark">
                            <tr>
                                <th>Producto</th>
                                <th>Imagen</th>
                                <th>Precio</th>
                                <th>Cantidad</th>
                                <th>Sub total</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody class="table-group-divider">
                            <c:set var="total" value="0" />
                            <c:forEach var="ca" items="${carrito}" varStatus="loop">
                                <tr>
                                    <td>${ca.getNombre_productoC()}</td>
                                    <td><img src="${ca.getImagenC()}" width="80px" height="80px" /></td>
                                    <td>
                                        <c:choose> 
                                            <c:when test="${ca.getOfertadoc() eq 1}">
                                                $ <fmt:formatNumber value="${ca.getPrecio_ofertadoC() }" type="number" pattern="#,##0.00" />
                                                <c:set var="ofertN" value="${ca.getOfertadoc()}" />
                                                <c:set var="ofertado" value="${ca.getPrecio_ofertadoC()}" />
                                                <c:set var="total" value="${total + ca.getPrecio_ofertadoC()}" />
                                            </c:when>
                                            <c:otherwise>                        
                                                $ <fmt:formatNumber value="${ca.getPrecio_normalc()}" type="number" pattern="#,##0.00" />
                                                <c:set var="ofertN" value="${ca.getOfertadoc()}" />
                                                <c:set var="precio" value="${ca.getPrecio_normalc()}" />
                                                <c:set var="total" value="${total + ca.getPrecio_normalc()}" />
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <div class="col-12">
                                            <input type="number" class="form-control" name="cantidad" id="cantidad${ca.getIdproductoC()}" 
                                                min="1" step="1" value="${ca.getCantidad()}" 
                                                data-id="${ca.getIdproductoC()}"
                                                data-name="${ca.getNombre_productoC()}"
                                                data-precio="${ofertN eq 1 ? ofertado : precio}" 
                                                data-cantidad="${ca.getCantidad()}" 
                                                onchange="actualizarTotales(this)" >
                                        </div>
                                    </td>
                                    <td>
                                        <c:choose> 
                                            <c:when test="${ca.getOfertadoc() eq 1}">
                                                <p id="subTotal${ca.getIdproductoC()}">$ <fmt:formatNumber  value="${ca.getCantidad() * ca.getPrecio_ofertadoC()}" type="number" pattern="#,##0.00" /></p>
                                            </c:when>
                                            <c:otherwise>  
                                                <p id="subTotal${ca.getIdproductoC()}">$ <fmt:formatNumber  value="${ca.getCantidad() * ca.getPrecio_normalc()}" type="number" pattern="#,##0.00" /></p>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <!-- Agregar el botón para eliminar -->
                                        <form id="deleteForm${ca.getIdproductoC()}" action="CarritoController" method="post">
                                            <input type="hidden" name="action" value="remove" />
                                            <input type="hidden" name="index" value="${loop.index}" />
                                            <button type="button" class="btn btn-danger" onclick="confirmDelete(${ca.getIdproductoC()});">Eliminar</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="col-md-4">
                <!-- Resumen del monto total -->
                <div class="card mt-3" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">Monto Total a Pagar</h5>
                        <p class="card-text" id="total">$ <fmt:formatNumber value="${total}" type="number" pattern="#,##0.00" /></p>
                        <hr/>
                        <c:set var="iva" value="${total * 0.13}" />
                        <p id="iva">Iva (13%) $ <fmt:formatNumber value="${iva}" type="number" pattern="#,##0.00" /></p>
                        <hr/>
                        <p id="totalPagar">Total a pagar $ <fmt:formatNumber value="${total + iva}" type="number" pattern="#,##0.00" /></p>
                        <c:if test="${empty cliente}">
                            <a href="CarritoController?action=login" class="btn btn-success"">Iniciar sesión</a>
                        </c:if>
                        <c:if test="${not empty cliente}">
                            <a href="login.jsp" class="btn btn-success">Generar pedido</a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </c:if>  
    <c:if test="${empty carrito}"> 
        <h1 class="text-muted text-center">No hay productos seleccionados</h1>
    </c:if> 
</div>

<script>
    function confirmDelete(idProducto) {
        Swal.fire({
            title: '¿Estás seguro?',
            text: "¡No podrás revertir esto!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sí, eliminarlo'
        }).then((result) => {
            if (result.isConfirmed) {
                // Si se confirma, envía el formulario
                document.getElementById("deleteForm" + idProducto).submit();
            }
        });
    }
    
     window.onload = function() {
        // Obtén todos los elementos de cantidad y ejecuta la función actualizarTotales para cada uno
        var cantidadInputs = document.querySelectorAll('[id^="cantidad"]');
        cantidadInputs.forEach(function(cantidadInput) {
            actualizarTotales(cantidadInput);
        });
    };

    function actualizarTotales(input) {
        var idProducto = input.dataset.id;
        var precio = parseFloat(input.dataset.precio);
        var cantidad = parseInt(input.value);
        var subTotal = cantidad * precio;

        // Actualiza el subtotal en la interfaz
        var subTotalElement = document.getElementById('subTotal' + idProducto);
        if (subTotalElement) {
            subTotalElement.textContent = "$ " + subTotal.toFixed(2);
        }

        // Calcula los totales
        var total = 0;
        var cantidadInputs = document.querySelectorAll('[id^="cantidad"]');
        cantidadInputs.forEach(function (cantidadInput) {
            var precioUnitario = parseFloat(cantidadInput.dataset.precio);
            var cantidad = parseInt(cantidadInput.value);
            total += cantidad * precioUnitario;
            
            
                input.addEventListener("keypress", function (event) {
                if (event.key === "Enter") {
                    // Evitar que la tecla Enter envíe el formulario
                    event.preventDefault();

                    // Llamar a la función para actualizar el carrito pasando el elemento input
                    actualizarCarrito(input);
                }
            });
        });

        // Actualiza los totales en la interfaz
        var totalElement = document.getElementById('total');
        var ivaElement = document.getElementById('iva');
        var totalPagarElement = document.getElementById('totalPagar');
        if (totalElement && ivaElement && totalPagarElement) {
            var iva = total * 0.13;
            var totalPagar = total + iva;
            totalElement.textContent = "$ " + total.toFixed(2);
            ivaElement.textContent = "Iva (13%) $ " + iva.toFixed(2);
            totalPagarElement.textContent = "Total a pagar $ " + totalPagar.toFixed(2);
        }
    }
    
    
     function actualizarCarrito(input) {
    // Obtener los atributos de datos del campo de cantidad
    var productId = input.getAttribute('data-id');
    var productName = input.getAttribute('data-name');
    var productPrice = input.getAttribute('data-precio');
    var productQuantityInputId = input.getAttribute('data-cantidad');

    // Obtener la cantidad ingresada por el usuario
    var cantidad = input.value;

    // Comprobar si la cantidad es válida (por ejemplo, mayor que cero)
    if (cantidad <= 0) {
        Swal.fire({
            title: 'Error',
            text: 'La cantidad ingresada no es válida',
            icon: 'error',
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 3000
        });
        return;
    }

    // Crear un objeto para enviar la solicitud AJAX
    var xhr = new XMLHttpRequest();

    // Configurar la URL correcta
    var url = '/PracticaRelacional/CarritoController?action=actualizarCarrito';

    // Configurar la solicitud POST con el endpoint adecuado en tu controlador
    xhr.open('POST', url, true);

    // Configurar el encabezado de la solicitud si es necesario
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    // Configurar la función de devolución de llamada cuando la solicitud se complete
    xhr.onload = function() {
        console.log('Status:', xhr.status);
        console.log('Response:', xhr.responseText);
        if (xhr.status === 200){
            Swal.fire({
                title: 'Éxito',
                text: 'Carrito actualizado con éxito',
                icon: 'success',
                toast: true,
                position: 'top-end',
                showConfirmButton: false,
                timer: 3000
            });
        }
        if (xhr.status === 200 && input.value == 1) {
            Swal.fire({
                title: 'Éxito',
                text: 'Carrito estandar ejecutado.',
                icon: 'success',
                toast: true,
                position: 'top-end',
                showConfirmButton: false,
                timer: 3000
            });
        }  else if (xhr.status === 400) {
            console.error('Existencias insuficientes');
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Tenemos problemas con la cantidad que deseas. \n Existencias insuficientes para el pedido.',
              }).then((result) => {
                // Después de cerrar la alerta, ejecutar actualizarTotales o actualizarCarrito
                input.value = 1;
                actualizarTotales(input);
                actualizarCarrito(input);
            });
        } 
    };

    // Preparar los datos a enviar en la solicitud AJAX
    var dataToSend = 'productoId=' + productId +
                     '&nombreProducto=' + encodeURIComponent(productName) +
                     '&precioNormal=' + productPrice +
                     '&cantidad=' + cantidad;

    // Enviar la solicitud AJAX con los datos del producto
    xhr.send(dataToSend);
}

</script>

<jsp:include page="../layouts/public_footer.jsp" />
