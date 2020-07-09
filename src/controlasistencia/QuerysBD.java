/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlasistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public String registrarEmpleado(Empleado emp) throws SQLException{
        String respuesta="";
        conn.conectar();
        stm = "CALL sp_registraEmpleado("+ emp.clave +",'"+emp.nombre+"','"+emp.primerA+"','"+emp.segundoA+"','"+ emp.curp+"','"+emp.fecha+"','"+emp.calle+"','"+emp.colonia+"',"+ emp.estado+","+emp.municipio+",'"+emp.nExterior+"','"+emp.nInterior+"',"+ emp.cPostal+",'"+emp.estatus+"');";
        rs = conn.consulta(stm);
        if(rs.next()){
            respuesta = rs.getString(1);
        }
        return respuesta;    
    }
    
    public Empleado consultaEmpleado(String claveE) throws SQLException{
        String respuesta="";
        Empleado emp = null ;
        conn.conectar();
        stm = "CALL sp_consultaEmpleado("+claveE+");";
        rs = conn.consulta(stm);
        if(rs.next()){
            if(rs.getString(1).equals("0")){
                emp = new Empleado("0");
            }else{
                emp = new Empleado( rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
                                rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(12),rs.getString(13),
                                rs.getString(15).charAt(0),Integer.valueOf(rs.getString(10)),Integer.valueOf(rs.getString(11)),Integer.valueOf(rs.getString(14)),rs.getString(1));
            }
        }
        return emp;  
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
    
    public int listaHorarioLength() throws SQLException{
        conn.conectar();
        int LARGO = 0;
        stm = "SELECT * FROM getNumeroHorarios";
        rs = conn.consulta(stm);
        if(rs.next()){
            LARGO = Integer.valueOf(rs.getString(1));
        }
        
        return LARGO;
    }
    
    public String[] getListaHorarios(int LARGO) throws SQLException{
        conn.conectar();
        String lista[] = new String[LARGO];
        int i = 0;
        stm = "SELECT * FROM horario;";
        rs = conn.consulta(stm);
        while(rs.next()){
            lista[i] = rs.getString(1);
            i++;
        }
        return lista;
    }

    Horario getHorario(String idHorario) throws SQLException {
        conn.conectar();
        Horario hr = new Horario();
        Dia d[] = new Dia[7];
        Dia _dias[];
        int i = 0;
        stm = "CALL sp_getHorario('"+idHorario+"');";
        rs = conn.consulta(stm);
        while(rs.next()){
            d[i] = new Dia(Integer.valueOf(rs.getString(1)), rs.getString(2), rs.getString(3));
            i++;
        }
        
        hr.idHorario = idHorario;
        hr.setDias(d,i);
        return hr;
    }
    
    
    String modificarEmpleado(Empleado emp) throws SQLException {
        String fin = "";
        conn.conectar();
        stm = "CALL sp_modificarEmpleado("+ emp.clave +",'"+emp.nombre+"','"+emp.primerA+"','"+emp.segundoA+"','"+ emp.curp+"','"+emp.fecha+"','"+emp.calle+"','"+emp.colonia+"',"+ emp.estado+","+emp.municipio+",'"+emp.nExterior+"','"+emp.nInterior+"',"+ emp.cPostal+",'"+emp.estatus+"');";
        rs = conn.consulta(stm);
        if(rs.next()){
            fin = rs.getString(1);
        }
        return fin;
    }

    void asignarHorario(Empleado emp) throws SQLException {
        conn.conectar();
        stm = "CALL sp_asignarHorario("+emp.clave+",'"+emp.idHorario+"');";
        rs = conn.consulta(stm);      
    }

    void removerHorarioEmpleado(String clave) throws SQLException {
        conn.conectar();
        stm = "CALL sp_removerHorario("+clave+");";
        rs = conn.consulta(stm);
    }

    void cleanHorario(Horario original, Horario cambios) throws SQLException {
        conn.conectar();
        for(Dia d: original.dias){
            if(!cambios.diccionario.containsKey(d.dia)){
                stm = "CALL sp_removerJornada('"+original.idHorario+"',"+d.dia+");";
                rs = conn.consulta(stm);
            }
        }
    }

    void registrarAsistencia(Asistencia a) throws SQLException {
        conn.conectar();
        stm = "CALL sp_altaAsistencia("+a.idEmpleado+",'"+a.str_fecha+"','"+a.str_hora+"',"+a.tipo+","+a.incidencia+","+a.lector+");";
        rs = conn.consulta(stm);
    }
    
    modeloTabla consultarAsisFecha(String clave,String fecha) throws SQLException{
        modeloTabla mt = new modeloTabla();
        conn.conectar();
        stm = "CALL sp_conAsistenciasFecha("+clave+",'"+fecha+"');";
        rs = conn.consulta(stm);
        while(rs.next()){
            mt.add(new Asistencia(clave,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
        }
        return mt;
    }
    
    void eliminarHorario(String idHorario) throws SQLException {
        conn.conectar();
        stm = "CALL sp_eliminarHorario('"+idHorario+"');";
        rs = conn.consulta(stm);
    }
    
    void eliminarEmpleado(String clave) throws SQLException {
        conn.conectar();
        stm = "CALL sp_eliminarEmpleado("+clave+");";
        rs = conn.consulta(stm);
    }

    modeloTabla consultarIncidencias(String clave) throws SQLException {
        modeloTabla mt = new modeloTabla();
        conn.conectar();
        stm = "CALL sp_conIncidencias("+clave+");";
        rs = conn.consulta(stm);
        while(rs.next()){
            mt.add(new Asistencia(clave,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
        }
        return mt;
    }
    
}
