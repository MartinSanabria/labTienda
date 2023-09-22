/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author martinsanabria
 */
public class Categoria {
    private int idcategoria;
    private String nombre_categoria;

    public Categoria() {
    }

    public Categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    public Categoria(int idcategoria, String nombre_categoria) {
        this.idcategoria = idcategoria;
        this.nombre_categoria = nombre_categoria;
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
     * @return the nombre_categoria
     */
    public String getNombre_categoria() {
        return nombre_categoria;
    }

    /**
     * @param nombre_categoria the nombre_categoria to set
     */
    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }
    
}
