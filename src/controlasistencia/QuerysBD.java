/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlasistencia;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author BB_TLACUACHE
 */
public class QuerysBD {
    BD conn;
    String stm;
    ResultSet rs;
    
    public QuerysBD(){
        conn = new BD();
    }
    
    public String registrarEmpleado(String rE_claveE,String rE_nombre,String rE_primerA,String rE_segundoA,
                                 String rE_curp,String rE_fecha,String rE_calle,String rE_colonia,
                                 int rE_estado,int rE_municipio,String rE_nExterior,String rE_nInterior,
                                 String rE_cPostal,char rE_status) throws SQLException{
        String respuesta="";
        conn.conectar();
        stm = "CALL sp_registraEmpleado("+ rE_claveE +",'"+rE_nombre+"','"+rE_primerA+"','"+rE_segundoA+"','"+ rE_curp+"','"+rE_fecha+"','"+rE_calle+"','"+rE_colonia+"',"+ rE_estado+","+rE_municipio+",'"+rE_nExterior+"','"+rE_nInterior+"','"+ rE_cPostal+"','"+rE_status+"');";
        rs = conn.consulta(stm);
        if(rs.next()){
            respuesta = rs.getString(1);
        }
        return respuesta;    
    }
    
    public ResultSet consultaEmpleado(String claveE) throws SQLException{
        String respuesta="";
        conn.conectar();
        stm = "CALL sp_consultaEmpleado("+claveE+");";
        rs = conn.consulta(stm);
        return rs;  
    }
    
    public String registrarHorario(String idHorario) throws SQLException{
        String respuesta = "";
        conn.conectar();
        stm = "CALL sp_registraHorario('"+idHorario+"');";
        rs = conn.consulta(stm);
        if(rs.next()){
            respuesta = rs.getString(1);
        }
        return respuesta;
    }
    
    public void modificarHorario(Horario hr) throws SQLException{
        conn.conectar();
        for(Dia d : hr.dias){
            stm = "CALL sp_modificaHorario('"+hr.idHorario+"',"+d.dia+",'"+d.entrada+"','"+d.salida+"');";
            rs = conn.consulta(stm);
            if(rs.next()){
            }
        }
    }
}
