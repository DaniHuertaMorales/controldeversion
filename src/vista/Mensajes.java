/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.Iterator;
import java.util.List;
import modelo.MaestroVO;

/**
 *
 * @author fredycastaneda
 */
public class Mensajes {
    Teclado teclado;

    public Mensajes() {
        this.teclado = new Teclado();
    }
    
    public int opcionEntero(){
        int opcion = 0;
        System.out.println("Introduce un dato de tipo entero");
        opcion = teclado.leerEntero();
        return opcion;
    }
    
    public void menu(){
        System.out.println("Elige una opcion");
        System.out.println("1) Agregar");
        System.out.println("2) Mostrar");
        System.out.println("3) Salir");
    }
    
    public void agregarMensaje(){
        System.out.println("Ingresando un maestro :");
    }
    
    public String  leerNombre(){
        String nombre;
        System.out.println("Introduce un nombre");
        nombre = teclado.leerCadena();
        return nombre;
    }
    
    public String leerContra(){
        String contra;
        System.out.println("Introduce una contrasena");
        contra = teclado.leerCadena();
        return contra;
    }
    
    public String leerMatricula(){
        String matricula = "";
        System.out.println("Introduce el valor de la matricula");
        matricula = teclado.leerCadena();
        return matricula;
    
    }
    
    public void mostrarMaestros(List<MaestroVO> lista){
        System.out.println("\t Los maestros son: ");
        Iterator<MaestroVO> iteradorE = lista.iterator();

        while (iteradorE.hasNext()) {
            System.out.println("\t \t " +iteradorE.next());
        }
    }
    
    public void resultadoOperacion(boolean resultado, String operacion){
        if(resultado){
            System.out.println(" \n       Resultado Exitoso al "+ operacion +"\n");
        }else{
            System.out.println(" \n       Resultado Fallido al "+ operacion +"\n");
        }
    }
}
