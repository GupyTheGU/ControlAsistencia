
package controlasistencia;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author BB_TLACUACHE
 */
public class Fonts {
    InputStream ttf_;
    public static Font OswaldLight,PTSans_Bold,PTSans_Bi,PTSans_I,PTSans_Regular,PTSans_Regular_12,PTSans_Regular_10;
    public static final ColorUIResource PINK  = new ColorUIResource(209,87,129);
    public static final ColorUIResource DARK  = new ColorUIResource(52,46,55);
    public static final ColorUIResource PURPLE = new ColorUIResource(119,47,91);
    public static final ColorUIResource GRAY = new ColorUIResource(224,224,226);
    public static final ColorUIResource GREEN = new ColorUIResource(77,175,115);
    public static final BorderUIResource GREEN_LINE_BORDER = new BorderUIResource(BorderFactory.createLineBorder(GREEN, 2));
    //FONT - ENCABEZADOS
    
    public Fonts(){
        try {
            this.ttf_= HOME.class.getClass().getResourceAsStream("/fonts/Oswald-Light.ttf");
            this.OswaldLight = Font.createFont(Font.TRUETYPE_FONT, ttf_);
            this.OswaldLight = OswaldLight.deriveFont(30f);
            
            //FONT - BOTONES
            this.ttf_= HOME.class.getClass().getResourceAsStream("/fonts/PTSans-Bold.ttf");
            this.PTSans_Bold = Font.createFont(Font.TRUETYPE_FONT, ttf_);
            this.PTSans_Bold = this.PTSans_Bold.deriveFont(18f);
            
            this.ttf_= HOME.class.getClass().getResourceAsStream("/fonts/PTSans-BoldItalic.ttf");
            this.PTSans_Bi = Font.createFont(Font.TRUETYPE_FONT, ttf_);
            this.PTSans_Bi= this.PTSans_Bi.deriveFont(12f);
            
            this.ttf_= HOME.class.getClass().getResourceAsStream("/fonts/PTSans-Italic.ttf");
            this.PTSans_I = Font.createFont(Font.TRUETYPE_FONT, ttf_);
            this.PTSans_I= this.PTSans_I.deriveFont(14f);
            
            //FONT - LABEL
            this.ttf_= HOME.class.getClass().getResourceAsStream("/fonts/PTSans-Regular.ttf");
            this.PTSans_Regular = Font.createFont(Font.TRUETYPE_FONT, ttf_);
            this.PTSans_Regular_10 = this.PTSans_Regular.deriveFont(12f);
            this.PTSans_Regular_12 = this.PTSans_Regular.deriveFont(14f);
            
            //FONT - LABEL - GRANDE
            this.PTSans_Regular = this.PTSans_Regular.deriveFont(18f);
            
        } catch (FontFormatException ex) {
            Logger.getLogger(Fonts.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Fonts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
