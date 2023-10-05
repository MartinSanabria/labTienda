<%-- 
    Document   : public_header
    Created on : 30 sep. 2023, 16:39:25
    Author     : martinsanabria
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
  <title>Tienda online</title>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS v5.2.1 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">

</head>

<body>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <!-- Container wrapper -->
    <div class="container-fluid">
  
      <!-- Collapsible wrapper -->
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <!-- Left links -->
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link" href="ClientsStore">Productos</a>
          </li>
           <li class="nav-item">
            <a class="nav-link" href="PedidoController?action=MiPedido">Mis pedidos</a>
          </li>
        </ul>
        <!-- Left links -->
      </div>
      <!-- Collapsible wrapper -->
  
      <!-- Right elements -->
      <div class="d-flex align-items-center">
        <!-- Icon -->
        <a class="link-secondary me-3" href="/PracticaRelacional/CarritoController?action=view">
          <i class="fas fa-shopping-cart"></i>
          <span class="badge rounded-pill badge-notification bg-danger"></span>
        </a>
        <!-- Avatar -->
        <!-- Avatar dropdown -->
            <div class="dropdown">
                <a
                class="btn btn-secondary dropdown-toggle"
                href="#"
                id="navbarDropdownMenuAvatar"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                >
                    Perfil
                </a>
                <ul
                class="dropdown-menu dropdown-menu-end"
                aria-labelledby="navbarDropdownMenuAvatar"
                >
                <li>
                    <a class="dropdown-item" href="/PracticaRelacional/LogoutController">Logout</a>
                </li>
                </ul>
            </div>
            <!-- Avatar dropdown -->
            
      </div>
      <!-- Right elements -->
    </div>
    <!-- Container wrapper -->
  </nav>
  <!-- Navbar -->