/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author martinsanabria
 */
public class Carrito {
    
 private int idproductoC;
 private String nombre_productoC;
 private double precio_normalc;
 private int ofertadoc;
 private double precio_ofertadoC;
 private int cantidad; 
 private String imagenC;
 private boolean procesado; 


    public Carrito() {
    }

    public Carrito(int idproductoC, String nombre_productoC, double precio_normalc, int ofertadoc, double precio_ofertadoC, int cantidad,String imagen) {
        this.idproductoC = idproductoC;
        this.nombre_productoC = nombre_productoC;
        this.precio_normalc = precio_normalc;
        this.ofertadoc = ofertadoc;
        this.precio_ofertadoC = precio_ofertadoC;
        this.cantidad = cantidad;
        this.imagenC = imagen;
    }

    /**
     * @return the idproductoC
     */
    public int getIdproductoC() {
        return idproductoC;
    }

    /**
     * @param idproductoC the idproductoC to set
     */
    public void setIdproductoC(int idproductoC) {
        this.idproductoC = idproductoC;
    }

    /**
     * @return the nombre_productoC
     */
    public String getNombre_productoC() {
        return nombre_productoC;
    }

    /**
     * @param nombre_productoC the nombre_productoC to set
     */
    public void setNombre_productoC(String nombre_productoC) {
        this.nombre_productoC = nombre_productoC;
    }

    /**
     * @return the precio_normalc
     */
    public double getPrecio_normalc() {
        return precio_normalc;
    }

    /**
     * @param precio_normalc the precio_normalc to set
     */
    public void setPrecio_normalc(double precio_normalc) {
        this.precio_normalc = precio_normalc;
    }

    /**
     * @return the ofertadoc
     */
    public int getOfertadoc() {
        return ofertadoc;
    }

    /**
     * @param ofertadoc the ofertadoc to set
     */
    public void setOfertadoc(int ofertadoc) {
        this.ofertadoc = ofertadoc;
    }

    /**
     * @return the precio_ofertadoC
     */
    public double getPrecio_ofertadoC() {
        return precio_ofertadoC;
    }

    /**
     * @param precio_ofertadoC the precio_ofertadoC to set
     */
    public void setPrecio_ofertadoC(double precio_ofertadoC) {
        this.precio_ofertadoC = precio_ofertadoC;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the imagenC
     */
    public String getImagenC() {
        return imagenC;
    }

    /**
     * @param imagenC the imagenC to set
     */
    public void setImagenC(String imagenC) {
        this.imagenC = imagenC;
    }
    
        
        public boolean isProcesado() {
        return procesado;
    }

    public void setProcesado(boolean procesado) {
        this.procesado = procesado;
    }

    
    
}
