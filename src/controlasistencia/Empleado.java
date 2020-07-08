/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlasistencia;

/**
 *
 * @author BB_TLACUACHE
 */
public class Empleado {
    String clave;
    String nombre;
    String primerA;
    String segundoA;
    String curp;
    String fecha;
    String calle;
    String colonia;
    String nExterior;
    String nInterior;
    char estatus;
    int estado;
    int municipio;
    int cPostal;
    String idHorario;
    boolean horarioAsignado = true;
    Horario hr;
    
    public Empleado( String _clave, String _nombre,String _primerA,String _segundoA,String _curp,
                     String _fecha,String _calle,String _colonia,String _nExterior,String _nInterior,
                     char _estatus,int _estado,int _municipio, int _cPostal,String _idHorario){
        
        this.clave = _clave;
        this.nombre = _nombre;
        this.primerA = _primerA;
        this.segundoA = _segundoA;
        this.curp = _curp;
        this.fecha = _fecha;
        this.calle = _calle;
        this.colonia = _colonia;
        this.nExterior = _nExterior;
        this.nInterior = _nInterior;
        this.estatus = _estatus;
        this.estado = _estado;
        this.municipio = _municipio;
        this.cPostal = _cPostal;
        this.idHorario = _idHorario;
        
        if(_idHorario == null || _idHorario.equals("-10")){
            this.horarioAsignado=false;
        }
        
    }

    Empleado(String string) {
        this.clave = string;
    }
    public void cambiarDatos(String _nombre,String _primerA,String _segundoA,String _curp,
                     String _fecha,String _calle,String _colonia,String _nExterior,String _nInterior,
                     char _estatus,int _estado,int _municipio, int _cPostal){
        this.nombre = _nombre;
        this.primerA = _primerA;
        this.segundoA = _segundoA;
        this.curp = _curp;
        this.fecha = _fecha;
        this.calle = _calle;
        this.colonia = _colonia;
        this.nExterior = _nExterior;
        this.nInterior = _nInterior;
        this.estatus = _estatus;
        this.estado = _estado;
        this.municipio = _municipio;
        this.cPostal = _cPostal;
    }
    public void setHorario(Horario dias){
        this.hr = dias;
    }
    
}
