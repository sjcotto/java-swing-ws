/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Formularios;

import java.io.File;

/**
 *
 * @author gonzalo
 */
public class FiltroXLS extends javax.swing.filechooser.FileFilter {

    @Override
    public boolean accept(File f) {
        String fileName = f.getName();

        if (fileName.endsWith(".xls"))
            return true;
        if (f.isDirectory())
            return true;

        return false;
    }

    @Override
    public String getDescription() {
        return "*.xls";
    }
}
