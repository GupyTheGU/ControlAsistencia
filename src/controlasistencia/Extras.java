/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlasistencia;

import java.awt.Component;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JTextField;

/**
 *
 * @author BB_TLACUACHE
 */
public class Extras {
    
    public static boolean validarCURP(String curp){ 
        if(isNull(curp)){
            return false;
        }
        String regex =
                        "[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}" +
                        "(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])" +
                        "[HM]{1}" +
                        "(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)" +
                        "[B-DF-HJ-NP-TV-Z]{3}" +
                        "[0-9A-Z]{1}[0-9]{1}$";
        return matchPattern(regex, curp);
    }
    
    public static boolean validarNumeros(String clave,int num){
        if(isNull(clave)){
            return false;
        }
        String regex = "[0-9]{"+num+"}$";
        return matchPattern(regex, clave);
    }
    
    public static boolean isNull(String cadena){
        String regex = "^\\s+$";
        if(cadena == null || cadena.equals("") || matchPattern(regex, cadena)){

            return true;
        }else{
            return false;
        }
    }
    
    public static String getHora(Component c){
        String hora = getText( ((javax.swing.JSpinner) c).getEditor().getComponent(0) );
        return hora;
    }
    
    public static String getText(Component c){
        return ((javax.swing.JTextField) c).getText();
    }
    public static void setText(Component c, String texto){
        ( (JTextField)  ((javax.swing.JSpinner) c).getEditor().getComponent(0)  ).setText(texto);
    }
    public static boolean validarFormatoFecha(String fecha){
        if(isNull(fecha)){
            return false;
        }
        String regex = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)\\d{2})$|"
                + "^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|"
                + "^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)\\d{2})$";
        return matchPattern(regex, fecha);
    }
    
    public static String flipDate(String fecha) {
        String[] parts = fecha.split("-");
        return parts[2]+"-"+parts[1]+"-"+parts[0];
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static boolean matchPattern(String regex, String cadena){
        Pattern patron = Pattern.compile(regex);
        if(!patron.matcher(cadena).matches()){ 
            return false;
        }else{ 
            return true;
        }
    }
    
    public static void setTransparent(Component c){
        ((JTextField) c).setOpaque(false);
        ((JTextField) c).setHorizontalAlignment(javax.swing.JTextField.CENTER);
    }
//    public static void getEditor(JSpinner spinner){
//        Date date = new Date();
//        SpinnerDateModel sm = new SpinnerDateModel(date, null, null,Calendar.MINUTE);
//        JSpinner.DateEditor de = new JSpinner.DateEditor(spinner, "hh:mm a");
//        //de.getTextField().setEditable( false );
//        spinner.setEditor(de);
//    }


    
}
