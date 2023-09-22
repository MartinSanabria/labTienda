/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author martinsanabria
 */
public class DetallePedido {
    private int iddetalle;
    private int idpedido;
    private int idproducto;
    private int cantidad;
    private double subtotal;

    public DetallePedido(int idpedido, int idproducto, int cantidad, double subtotal) {
        this.idpedido = idpedido;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public DetallePedido(int iddetalle, int idpedido, int idproducto, int cantidad, double subtotal) {
        this.iddetalle = iddetalle;
        this.idpedido = idpedido;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public DetallePedido() {
    }

    /**
     * @return the iddetalle
     */
    public int getIddetalle() {
        return iddetalle;
    }

    /**
     * @param iddetalle the iddetalle to set
     */
    public void setIddetalle(int iddetalle) {
        this.iddetalle = iddetalle;
    }

    /**
     * @return the idpedido
     */
    public int getIdpedido() {
        return idpedido;
    }

    /**
     * @param idpedido the idpedido to set
     */
    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

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
     * @return the subtotal
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
