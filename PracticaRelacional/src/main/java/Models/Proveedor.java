/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author martinsanabria
 */
public class Proveedor {

private int idproveedor;	
private String nombre_proveedor;	
private String telefono;	
private String contacto;	

    public Proveedor() {
    }

    public Proveedor(String nombre_proveedor, String telefono, String contacto) {
        this.nombre_proveedor = nombre_proveedor;
        this.telefono = telefono;
        this.contacto = contacto;
    }

    public Proveedor(int idproveedor, String nombre_proveedor, String telefono, String contacto) {
        this.idproveedor = idproveedor;
        this.nombre_proveedor = nombre_proveedor;
        this.telefono = telefono;
        this.contacto = contacto;
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
     * @return the nombre_proveedor
     */
    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    /**
     * @param nombre_proveedor the nombre_proveedor to set
     */
    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the contacto
     */
    public String getContacto() {
        return contacto;
    }

    /**
     * @param contacto the contacto to set
     */
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

}
