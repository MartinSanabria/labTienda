/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *
 * @author martinsanabria
 */
public class Pedido {
    private int idpedido; 
    private int idcliente;
    private Date fecha;
    private double total;
    private String estado;

    public Pedido(int idcliente, Date fecha, double total, String estado) {
        this.idcliente = idcliente;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }

    public Pedido(int idpedido, int idcliente, Date fecha, double total, String estado) {
        this.idpedido = idpedido;
        this.idcliente = idcliente;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }

    public Pedido() {
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
     * @return the idcliente
     */
    public int getIdcliente() {
        return idcliente;
    }

    /**
     * @param idcliente the idcliente to set
     */
    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
