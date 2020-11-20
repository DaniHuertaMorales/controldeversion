/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author fredycastaneda
 */
public class Maestro_DAO_Test {
        
    Maestro_DAO_Imp eDAOImp;
    MaestroVO maestro;
    MaestroVO maestro2;
    
    @Before
    public void before(){
        eDAOImp = new Maestro_DAO_Imp();
        maestro = new MaestroVO();
        maestro2 = new MaestroVO();
    }

    @Test 
    public void testTrueCreate(){
       boolean resultado = false;
        try {
            resultado = eDAOImp.create(maestro);
        } catch (Exception ex) {
            Logger.getLogger(Maestro_DAO_Test.class.getName()).log(Level.SEVERE, null, ex);
        }
       assertTrue(resultado);
    }
    
}
