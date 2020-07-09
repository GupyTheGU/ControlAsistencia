/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlasistencia;

import java.util.Date;

/**
 *
 * @author BB_TLACUACHE
 */
public class Asistencia {

    String idEmpleado;
    
    String str_fecha;
    String str_hora;
    
    int    tipo;
    int    lector;
    int    incidencia;
    
    String str_tipo;
    String str_incidencia;
    String str_biometrico;
    
    Asistencia(String empleado, String str_fecha, String str_hora, int inOut, int object, int lector) {
        this.idEmpleado = empleado;
        this.str_fecha = str_fecha;
        this.str_hora = str_hora;
        this.tipo = inOut;
        this.incidencia = object;
        this.lector = lector;
    }
    Asistencia(String empleado, String str_fecha, String str_hora, String inOut, String object, String lector) {
        this.idEmpleado = empleado;
        this.str_fecha = str_fecha;
        this.str_hora = str_hora;
        this.str_tipo = inOut;
        this.str_incidencia = object;
        this.str_biometrico = lector;
    }
    
    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getStr_fecha() {
        return str_fecha;
    }

    public void setStr_fecha(String str_fecha) {
        this.str_fecha = str_fecha;
    }

    public String getStr_hora() {
        return str_hora;
    }

    public void setStr_hora(String str_hora) {
        this.str_hora = str_hora;
    }

    public int getLector() {
        return lector;
    }

    public void setLector(int lector) {
        this.lector = lector;
    }

    public String getStr_tipo() {
        return str_tipo;
    }

    public void setStr_tipo(String str_tipo) {
        this.str_tipo = str_tipo;
    }

    public String getStr_incidencia() {
        return str_incidencia;
    }

    public void setStr_incidencia(String str_incidencia) {
        this.str_incidencia = str_incidencia;
    }

    public String getStr_biometrico() {
        return str_biometrico;
    }

    public void setStr_biometrico(String str_biometrico) {
        this.str_biometrico = str_biometrico;
    }
    
}
