/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author martinsanabria
 */
public class Producto {
private int idproducto;	
private int idcategoria;	
private int idproveedor;	
private String nombre_producto;		
private double precio_normal;	
private int ofertado;	
private double precio_oferta;	

    public Producto(int idproducto, int idcategoria, int idproveedor, String nombre_producto, double precio_normal, int ofertado, double precio_oferta, int existencias, String descripcion, String imagen) {
        this.idproducto = idproducto;
        this.idcategoria = idcategoria;
        this.idproveedor = idproveedor;
        this.nombre_producto = nombre_producto;
        this.precio_normal = precio_normal;
        this.ofertado = ofertado;
        this.precio_oferta = precio_oferta;
        this.existencias = existencias;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Producto(int idcategoria, int idproveedor, String nombre_producto, double precio_normal, int ofertado, double precio_oferta, int existencias, String descripcion, String imagen) {
        this.idcategoria = idcategoria;
        this.idproveedor = idproveedor;
        this.nombre_producto = nombre_producto;
        this.precio_normal = precio_normal;
        this.ofertado = ofertado;
        this.precio_oferta = precio_oferta;
        this.existencias = existencias;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Producto() {
    }
private int existencias;	
private String descripcion;	
private String imagen;		

    /**
     * @return the idproducto
     */
    public int getIdproducto() {
        return idproducto;
    }

    /**
     * @param idproducto the idproducto to set
     */
    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    /**
     * @return the idcategoria
     */
    public int getIdcategoria() {
        return idcategoria;
    }

    /**
     * @param idcategoria the idcategoria to set
     */
    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    /**
     * @return the idproveedor
     */
    public int getIdproveedor() {
        return idproveedor;
    }

    /**
     * @param idproveedor the idproveedor to set
     */
    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    /**
     * @return the nombre_producto
     */
    public String getNombre_producto() {
        return nombre_producto;
    }

    /**
     * @param nombre_producto the nombre_producto to set
     */
    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    /**
     * @return the precio_normal
     */
    public Double getPrecio_normal() {
        return precio_normal;
    }

    /**
     * @param precio_normal the precio_normal to set
     */
    public void setPrecio_normal(Double precio_normal) {
        this.precio_normal = precio_normal;
    }

    /**
     * @return the ofertado
     */
    public int getOfertado() {
        return ofertado;
    }

    /**
     * @param ofertado the ofertado to set
     */
    public void setOfertado(int ofertado) {
        this.ofertado = ofertado;
    }

    /**
     * @return the precio_oferta
     */
    public Double getPrecio_oferta() {
        return precio_oferta;
    }

    /**
     * @param precio_oferta the precio_oferta to set
     */
    public void setPrecio_oferta(Double precio_oferta) {
        this.precio_oferta = precio_oferta;
    }

    /**
     * @return the existencias
     */
    public int getExistencias() {
        return existencias;
    }

    /**
     * @param existencias the existencias to set
     */
    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
