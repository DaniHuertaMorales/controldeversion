/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.MaestroVO;
import modelo.Maestro_DAO_Imp;
import vista.Mensajes;

/**
 *
 * @author fredycastaneda
 */
public class Operaciones {

    Mensajes ms;
    Maestro_DAO_Imp maestroDAOImp;

    public Operaciones() {
        this.ms =new Mensajes();
        this.maestroDAOImp = new Maestro_DAO_Imp();
    }

        
    

    public void guardar(String nombre, String contra)  {
        MaestroVO ep = new MaestroVO(nombre, contra);
        Maestro_DAO_Imp ei = new Maestro_DAO_Imp();
        try {
            ei.create(ep);
        } catch (Exception ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrar() {
        List<MaestroVO> lista = null;
        try {
            lista = maestroDAOImp.readAll();
        } catch (Exception ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        ms.mostrarMaestros(lista);
    }

    public void ejecutarAplicacion() {
        int opcion = 0;
        do {
            ms.menu();
            opcion = ms.opcionEntero();
            switch (opcion) {
                case 1:
                    ms.agregarMensaje();
                    String matricula = ms.leerMatricula();
                    String nombre = ms.leerNombre();
                    String contra = ms.leerContra();
                    MaestroVO maestro = new MaestroVO(matricula,nombre, contra);
                    boolean creado = false;
                try {
                    creado = this.maestroDAOImp.create(maestro);
                } catch (Exception ex) {
                    Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
                    ms.resultadoOperacion(creado, " crear ");
                    break;

                case 2:
                    List<MaestroVO> lista = null;
                try {
                    lista = this.maestroDAOImp.readAll();
                } catch (Exception ex) {
                    Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
                    ms.mostrarMaestros(lista);
                    break;
                    
                default:
                    System.out.println("Saliendo del sistema ...");
            }
        } while (opcion != 3);
    }
}
