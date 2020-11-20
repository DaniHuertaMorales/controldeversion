/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.mysql.jdbc.CommunicationsException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class Maestro_DAO_Imp implements Maestro_DAO {

    @Override
    public boolean create(MaestroVO maestro) throws Exception{
        boolean created = false;
        Statement stm = null;
        Connection con = null;
        String sql = "INSERT INTO maestro values ('" + maestro.getMatricula()+ "', '" + maestro.getNombre() + "', '" + maestro.getContra()+"')";
        ConexionDB cc = new ConexionDB();
        try {
            con = cc.conectarMySQL();
            stm = con.createStatement();
            stm.execute(sql);
            created = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            throw new Exception ("Error en create SQLException " + e.getMessage());
        } catch (NullPointerException e){
            throw new Exception ("Error en create objeto null " + e.getMessage());
        }catch (Exception e){
            throw new Exception ("Error en create " + e.getMessage());
        }
        return created;
    }
    
    @Override
    public MaestroVO read(int clave) throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select * from maestro where matricula = " + clave;

        MaestroVO maestro = new MaestroVO();

        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                maestro.setMatricula(rs.getString(1));
                maestro.setNombre(rs.getString(2));
                maestro.setContra(rs.getString(3));
            }
            stm.close();
            rs.close();
            con.close();
        } catch (CommunicationsException e) {
            throw new Exception ("Error en read CommunicationsException" + e.getCause().toString());
        } catch (SQLException e){
            throw new Exception ("Error en read SQLException" + e.getCause().toString());
        } catch (Exception e){
            throw new Exception ("Error en read " + e.getCause().toString());
        }
        return maestro;
    }
    
    @Override
    public MaestroVO read(String nombre) throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select * from producto where nombre = '" + nombre+ "'";

        MaestroVO maestro = new MaestroVO();

        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                maestro.setMatricula(rs.getString(1));
                maestro.setNombre(rs.getString(2));
                maestro.setContra(rs.getString(3));
            }
            stm.close();
            rs.close();
            con.close();
        } catch (CommunicationsException e) {
            throw new Exception ("Error en read CommunicationsException" + e.getCause().toString());
        } catch (SQLException e){
            throw new Exception ("Error en read SQLException" + e.getCause().toString());
        } catch (Exception e){
            throw new Exception ("Error en read " + e.getCause().toString());
        }
        return maestro;
    }

    public List<MaestroVO> readAll() throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select * from maestro order by matricula";

        List<MaestroVO> listaMaestros = new ArrayList<MaestroVO>();

        try {
            con = new ConexionDB().conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                MaestroVO c = new MaestroVO(rs.getString(1),
                        rs.getString(2), rs.getString(3));
                listaMaestros.add(c);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            throw new Exception ("Error en readAll SQLException" + e.getCause().toString());
        } catch (Exception e){
            throw new Exception ("Error en readAll" + e.getCause().toString());
        }

        return listaMaestros;
    }

   
    public boolean update(MaestroVO maestro, String nuevo) throws Exception{
        Connection connect = null;
        Statement stm = null;
        boolean actualizar = false;
        String sql = "";
        
        if(nuevo == "Matricula"){
            sql = "update maestro set " +nuevo+" = '" + maestro.getMatricula()+ "' where matricula = '" + maestro.getMatricula()+"'";
        }
        if(nuevo == "Nombre"){
            sql = "update maestro set " +nuevo+" = '" + maestro.getNombre() + "' where matricula = '" + maestro.getMatricula()+"'";
        }
        if(nuevo == "Contra"){
            sql = "update maestro set " +nuevo+" = '" + maestro.getContra()+ "' where matricula = '" + maestro.getMatricula()+"'";
        }
        
        try {
            connect = new ConexionDB().conectarMySQL();
            stm = connect.createStatement();
            actualizar = stm.execute(sql);    
        } catch (SQLException e) {
           throw new Exception ("Error en update SQLException" + e.getCause().toString());
        }
        return actualizar;
    }

   
    public boolean delete(MaestroVO maestro) throws Exception {
        Connection connect = null;
        Statement stm = null;

        boolean eliminar = false;

        String sql = "DELETE FROM maestro WHERE matricula = " + maestro.getMatricula();
        try {
            connect = new ConexionDB().conectarMySQL();
            stm = connect.createStatement();
            eliminar = stm.execute(sql);
        } catch (SQLException e) {
            throw new Exception ("Error en delete SQLException" + e.getCause().toString());
        }
        return eliminar;
    }

}
