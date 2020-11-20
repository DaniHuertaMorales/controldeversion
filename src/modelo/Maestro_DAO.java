/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

/**
 *
 * @author fredycastaneda
 */
public interface Maestro_DAO {
    
    public MaestroVO read(String nombre) throws Exception;

    public boolean create(MaestroVO maestro) throws Exception;

    public List<MaestroVO> readAll()throws Exception;
    
    public MaestroVO read(int clave)throws Exception;
    
    public boolean update(MaestroVO maestro, String nuevo)throws Exception;
    
    public boolean delete(MaestroVO maestro)throws Exception; 
}
