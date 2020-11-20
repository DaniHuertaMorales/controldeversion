/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author fredycastaneda
 */
public class MaestroVO {

    String matricula;
    String nombre;
    String contra;
    /* -------- */
    public MaestroVO() {
        this.matricula = "";
        this.nombre = "";
        this.contra = "";
    }

    public MaestroVO(String nombre, String contra){
        this.matricula = matricula;
        this.nombre = nombre;
        this.contra = contra;
    }
    
     public MaestroVO(String matricula, String nombre, String contra){
        this.matricula = matricula;
        this.nombre = nombre;
        this.contra = contra;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
   

    @Override
    public String toString() {
        return "ProductoVO{" + "matricula = " + matricula + ", nombre = " + nombre + ", contra = "+'}';
    }
}
