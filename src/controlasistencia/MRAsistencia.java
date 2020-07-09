/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlasistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Scanner;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author BB_TLACUACHE
 */
public class MRAsistencia {
    
    public static int ENHORARIO     = 0;
    public static int RETARDO_MENOR = 1;
    public static int RETARDO_MAYOR = 2;
    public static int INASISTENCIA  = 3;
    
    public static int minRMENOR  = 11;
    public static int minRMAYOR  = 21;
    public static int minRFALTA  = 31;
    
    public static int ENTRADA=1;
    public static int SALIDA=2;
    

    public static File archivo = null;
    Asistencia asistencias[] = null;
    
    int inOut = 0;
    String empleado;
    String str_fecha;
    String str_hora;
    int dia;
    int lector = 0;
    int incidencia = 0;
    Horario hr;
    public void setAsistencias() throws FileNotFoundException, ParseException, SQLException{
        String registro;
        Scanner sc = new Scanner(archivo);
        while(sc.hasNextLine()){
            registro = sc.nextLine();
            fragmentar(registro);
            parseFecha();
            parseHora();
            setHorario();
            setAsistencia();
            
        }
    }
    
    public void setAsistencia() throws SQLException{
        QuerysBD consulta = new QuerysBD();
        
        if(inOut == 1){
            comparar();
            Asistencia a = new Asistencia(empleado,str_fecha,str_hora,inOut,incidencia,lector);
            consulta.registrarAsistencia(a);
        }else{
            Asistencia a = new Asistencia(empleado,str_fecha,str_hora,inOut,0,lector);
            consulta.registrarAsistencia(a);
        }
    }
    
    public void comparar(){
        int item = hr.diccionario.get(String.valueOf(dia));
        String _entrada = hr.dias[item].entrada;
        DateTimeFormatter  ft1 = DateTimeFormat.forPattern("HH:mm");
        DateTimeFormatter  ft2 = DateTimeFormat.forPattern("HH:mm:ss");
        
        LocalTime menor = ft1.parseLocalTime(_entrada).plusMinutes(minRMENOR);
        LocalTime mayor = ft1.parseLocalTime(_entrada).plusMinutes(minRMAYOR);
        LocalTime falta = ft1.parseLocalTime(_entrada).plusMinutes(minRFALTA);
        LocalTime horaReg = ft2.parseLocalTime(str_hora);
        
        if(horaReg.isBefore(menor)){
            incidencia = ENHORARIO;
        }else{
            if(horaReg.isBefore(mayor)){
                incidencia = RETARDO_MENOR;
            }else{
                if(horaReg.isBefore(falta)){
                    incidencia = RETARDO_MAYOR;
                }else{
                    incidencia = INASISTENCIA;
                }//falta
            }//r mayor
        }//r menor
        //System.out.println(incidencia);
    }
    
    public void setHorario() throws SQLException{
        HorariosController.createListaHorarios();
        QuerysBD consulta = new QuerysBD();
        String horario = consulta.consultaEmpleado(empleado).idHorario;
        int item = HorariosController.diccionario.get(horario);
        hr = HorariosController.listaHorarios[item];
        //System.out.println(hr.idHorario);
    }
    public void fragmentar(String registro){
        inOut = Integer.valueOf(registro.substring(0, 1));
        empleado = registro.substring(1, 10);
        str_fecha = registro.substring(10, 18);
        str_hora = registro.substring(18,24);
        lector = Integer.valueOf(registro.substring(24));
    }
    public void parseFecha() throws ParseException{
        SimpleDateFormat juntoFecha = new SimpleDateFormat("ddMMyyyy");
        Date fecha = juntoFecha.parse(str_fecha);

        SimpleDateFormat isoF = new SimpleDateFormat("yyyy-MM-dd");
        str_fecha = isoF.format(fecha);
        
        SimpleDateFormat diaF = new SimpleDateFormat("u");
        dia = Integer.valueOf(diaF.format(fecha));
        
    }
    public void parseHora() throws ParseException{
        
        SimpleDateFormat horaOg = new SimpleDateFormat("HHmmss");
        Date hora = horaOg.parse(str_hora);
        
        SimpleDateFormat ca = new SimpleDateFormat("HH:mm:ss");
        str_hora = ca.format(hora);
        
    }
}
