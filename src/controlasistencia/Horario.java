/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlasistencia;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

/**
 *
 * @author BB_TLACUACHE
 */
public class Horario {
    public String idHorario;
    public Dia[] dias;
    
    public Dia[] getHorario(){
        return this.dias;
    }
    public void setHorario(JPanel panel){
        boolean esLaboral=false;
        int hora=0,dia=0,i=0;
        String horas[] = new String[2];
        Dia[] _dias = new Dia[7];
        
        for(Component comp : panel.getComponents()){
            hora=0;
            esLaboral = false;
            if(comp.getClass().getName().equals("javax.swing.JPanel")){
                dia = Integer.valueOf(((JPanel)comp).getName());
                for(Component c : ((JPanel)comp).getComponents()){
                    
                    if(c.getClass().getName().equals("javax.swing.JRadioButton")){
                        if(  (  (JRadioButton)  c).isSelected()  ){
                            esLaboral = true;
                        }
                    }

                    if(c.getClass().getName().equals("javax.swing.JSpinner")){
                        if(esLaboral){
                            horas[hora] = Extras.getHora(c);
                            hora++;
                        }
                    }

                }//ITERACION PARA UN DIA
                if(hora == 2){
                    _dias[i] = new Dia(dia,horas[0],horas[1]);
                    i++;
                }
            } 
        }
        
        this.dias = new Dia[i];
        for(int k = 0;k < i ; k++){
            dias[k]= _dias[k];
        }
    }
    
    
}


