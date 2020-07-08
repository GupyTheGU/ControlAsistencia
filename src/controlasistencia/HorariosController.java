/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlasistencia;

import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author BB_TLACUACHE
 */
public class HorariosController {
    public static String listaNombres[]={"sou","mou"};
    public static Horario listaHorarios[]=null;
    public static HashMap<String,Integer> diccionario = new HashMap<String, Integer>();
    public static int activeHorario = 0;

    public static void createListaHorarios() throws SQLException{
        diccionario.clear();
        QuerysBD consulta = new QuerysBD();
        int LARGO = consulta.listaHorarioLength();
        listaNombres = consulta.getListaHorarios(LARGO);
        listaHorarios = new Horario[LARGO];
        
        for(int i = 0; i<LARGO;i++){
            listaHorarios[i] = consulta.getHorario(listaNombres[i]);
            diccionario.put(listaNombres[i], i);
        }
        
    }
   
}
