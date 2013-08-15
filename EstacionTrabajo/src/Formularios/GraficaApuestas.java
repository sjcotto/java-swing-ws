/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import org.jfree.chart.ChartFactory;
import org.jfree.data.category.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.CategoryPlot;
import Controladores.*;
import Objetos.Usuario;
import java.awt.Color;
import java.util.*;

public class GraficaApuestas extends  javax.swing.JFrame{
    public GraficaApuestas(String titulo, ManejadorUsuarios man){
        super(titulo);
        
        Map<String,Usuario> usuarios = man.getUsuariosNick();
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        this.setResizable(false);
        float gan = 0;
        float mont = 0;
        float max_gan = 0;
        float max_mont = 0;
        float max=0;
        
        for (Map.Entry<String,Usuario> k : usuarios.entrySet()){
            
            Usuario u = k.getValue();
            String key = k.getKey();
            
            mont = u.getTotalMontoApostado();
            gan = u.getGananciaTotalApuestas();
            
            if (mont > max_mont)
                max_mont = mont;
            if (max_gan > gan)
                max_gan = gan;
            if (!(mont==0 && gan==0)){
                data.addValue(-mont, "Monto", key);
                data.addValue(gan, "Ganancia", key);
            }
        }
        
        if (max_gan > max_mont)
            max = max_gan;
        else
            max = max_mont;
        JFreeChart chart = ChartFactory.createStackedBarChart("Estadisticas de los usuarios","Nicks","Valores",data,PlotOrientation.HORIZONTAL,
                                                        true,false,false);
        ChartPanel chpanel = new ChartPanel(chart);
        chpanel.setPreferredSize(new java.awt.Dimension(550,320));
        this.setContentPane(chpanel);
        
        CategoryPlot p = chart.getCategoryPlot();
        p.setBackgroundPaint(Color.BLACK);  

        ValueAxis a = chart.getCategoryPlot().getRangeAxis();
        a.setRange(new org.jfree.data.Range(-max-max/10,max+max/10));
        
        
        
    }
    
    
}
