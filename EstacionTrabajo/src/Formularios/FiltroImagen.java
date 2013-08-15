/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Formularios;

/**
 *
 * @author santiago
 */
import java.io.File;

public class FiltroImagen extends javax.swing.filechooser.FileFilter {
        public boolean accept(File file) {
            String filename = file.getName();
            if (filename.endsWith(".jpg")){
                return true;
            }
            else if ((filename.endsWith(".png"))){
                return true;
            }
            if (file.isDirectory())
                return true;
            return false;
        }
        public String getDescription() {
            return "*.jpg *.png";
        }
}