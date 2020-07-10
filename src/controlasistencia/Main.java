/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlasistencia;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.utils.MaterialColors;

/**
 *
 * @author BB_TLACUACHE
 */
public class Main {
    public static void main(String args[]) {
        
        try {
            UIManager.setLookAndFeel (new MaterialLookAndFeel( new ModifiedMaterial()));
        }
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace ();
        }
        new InicioSesion().setVisible(true);
        //new HOME().setVisible(true);
        //new RegistrarEmpleado().setVisible(true);
               
    }
}
