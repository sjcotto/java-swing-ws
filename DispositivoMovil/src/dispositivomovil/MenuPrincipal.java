/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MenuPrincipal.java
 *
 * Created on Nov 3, 2011, 3:23:10 PM
 */

package dispositivomovil;

import WebServices.DispMovilWS.DataUsuario;
import WebServices.DispMovilWS.DispMovilWS;
import WebServices.DispMovilWS.DispMovilWSService;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import dispositivomovil.Apuestas.Competiciones;
import dispositivomovil.Pencas.ParticiparEnPenca;
import java.io.FileInputStream;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author tprog082
 */
public class MenuPrincipal extends javax.swing.JPanel {

    /** Creates new form MenuPrincipal */
    public MenuPrincipal(JFrame a) {
        initComponents();

        this.dispMov = (DispositivoMovil)a;

        jLabel1.setText("");
        jLabel2.setText("");
        jLabel4.setText("");
        jLabel5.setText("");
        this.jLabelError.setText("");

        if (this.dispMov.nickUsuLogueado.equals("")) {
            ImageIcon im = new ImageIcon("images/login.png");
            jLabel5.setIcon(new ImageIcon(im.getImage().getScaledInstance(60, 60, 60)));
        } else {
            ImageIcon im = new ImageIcon("images/logout.png");
            jLabel5.setIcon(new ImageIcon(im.getImage().getScaledInstance(60, 60, 60)));
        }

        this.menu = this;

        seleccionar(1);
        deseleccionar(2);
        deseleccionar(3);
        deseleccionar(4);

        this.setFocusable(true);
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {

            }

            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                char l = evt.getKeyChar();

                if (evt.getKeyCode() == KeyEvent.VK_LEFT){
                    if (seleccion == 2) {
                        seleccion = 1;
                        deseleccionar(2);
                        seleccionar(1);
                    } else if (seleccion == 3) {
                        seleccion = 2;
                        deseleccionar(3);
                        seleccionar(2);
                    } if (seleccion == 4) {
                        seleccion = 3;
                        deseleccionar(4);
                        seleccionar(3);
                    }
                }else if (evt.getKeyCode() == KeyEvent.VK_RIGHT){
                    if (seleccion == 1) {
                        seleccion = 2;
                        deseleccionar(1);
                        seleccionar(2);
                    } else if (seleccion == 2) {
                        seleccion = 3;
                        deseleccionar(2);
                        seleccionar(3);
                    } else if (seleccion == 3) {
                        seleccion = 4;
                        deseleccionar(3);
                        seleccionar(4);
                    }
                }else if (evt.getKeyCode() == KeyEvent.VK_UP){
                    if (seleccion == 3) {
                        seleccion = 1;
                        deseleccionar(3);
                        seleccionar(1);
                    } else if (seleccion == 4) {
                        seleccion = 2;
                        deseleccionar(4);
                        seleccionar(2);
                    }
                } else if (evt.getKeyCode() == KeyEvent.VK_DOWN){
                    if (seleccion == 1) {
                        seleccion = 3;
                        deseleccionar(1);
                        seleccionar(3);
                    } else if (seleccion == 2) {
                        seleccion = 4;
                        deseleccionar(2);
                        seleccionar(4);
                    }
                } else if (evt.getKeyCode() == KeyEvent.VK_ENTER){
                    JPanel ap = null;
                    if (seleccion == 1)
                        ap = new ParticiparEnPenca(dispMov);
                    else if (seleccion == 2) 
                        ap = new Competiciones(dispMov);
                    //else if (seleccion == 3)

                    //else if (seleccion == 4)

                    ap.setSize(320,420);
                    dispMov.jDesktopPane1.add(ap);
                    ap.setVisible(true);
                    menu.removeAll();
                }
            }

        });
        
    }

    private void seleccionar(int i){
        ImageIcon ic = null;
        if (i==1) {
            ic = new ImageIcon("images/pencas.png");
            jLabel3.setText("Pencas");
            ImageIcon im = new ImageIcon(ic.getImage().getScaledInstance(60,60,60));
            jLabel2.setIcon(im);
            jLabel2.setSize(70, 70);
            jLabel2.setVerticalAlignment(JLabel.CENTER);
            jLabel2.setHorizontalAlignment(JLabel.CENTER);
            javax.swing.border.Border bor = new javax.swing.border.LineBorder(Color.white, 2, true);
            jLabel2.setBorder(bor);

        } else if (i==2) {
            ic = new ImageIcon("images/apuestas.png");
            jLabel3.setText("Apuestas");
            ImageIcon im = new ImageIcon(ic.getImage().getScaledInstance(60,60,60));
            jLabel1.setIcon(im);
            jLabel1.setSize(70, 70);
            jLabel1.setVerticalAlignment(JLabel.CENTER);
            jLabel1.setHorizontalAlignment(JLabel.CENTER);
            javax.swing.border.Border bor = new javax.swing.border.LineBorder(Color.white, 2, true);
            jLabel1.setBorder(bor);
        } else if (i==3) {
            ic = new ImageIcon("images/monedero.png");
            jLabel3.setText("Perfil");
            ImageIcon im = new ImageIcon(ic.getImage().getScaledInstance(60,60,60));
            jLabel4.setIcon(im);
            jLabel4.setSize(70, 70);
            jLabel4.setVerticalAlignment(JLabel.CENTER);
            jLabel4.setHorizontalAlignment(JLabel.CENTER);
            javax.swing.border.Border bor = new javax.swing.border.LineBorder(Color.white, 2, true);
            jLabel4.setBorder(bor);
        } else if (i==4) {
            if (this.dispMov.nickUsuLogueado.equals("")){
                ic = new ImageIcon("images/login.png");
                jLabel3.setText("Iniciar Sesion");
            } else {
                ic = new ImageIcon("images/logout.png");
                jLabel3.setText("Cerrar Sesion");
            }
            ImageIcon im = new ImageIcon(ic.getImage().getScaledInstance(60,60,60));
            jLabel5.setIcon(im);
            jLabel5.setSize(70, 70);
            jLabel5.setVerticalAlignment(JLabel.CENTER);
            jLabel5.setHorizontalAlignment(JLabel.CENTER);
            javax.swing.border.Border bor = new javax.swing.border.LineBorder(Color.white, 2, true);
            jLabel5.setBorder(bor);
        }

    }

    private void deseleccionar(int i){
        ImageIcon ic = null;
        if (i==1) {
            ic = new ImageIcon("images/pencas.png");
            ImageIcon im = new ImageIcon(ic.getImage().getScaledInstance(50,50,50));
            jLabel2.setIcon(im);
            jLabel2.setSize(70, 70);
            jLabel2.setVerticalAlignment(JLabel.CENTER);
            jLabel2.setHorizontalAlignment(JLabel.CENTER);
            jLabel2.setBorder(null);
        } else if (i==2) {
            ic = new ImageIcon("images/apuestas.png");
            ImageIcon im = new ImageIcon(ic.getImage().getScaledInstance(50,50,50));
            jLabel1.setIcon(im);
            jLabel1.setSize(70, 70);
            jLabel1.setVerticalAlignment(JLabel.CENTER);
            jLabel1.setHorizontalAlignment(JLabel.CENTER);
            jLabel1.setBorder(null);
        } else if (i==3) {
            ic = new ImageIcon("images/monedero.png");
            ImageIcon im = new ImageIcon(ic.getImage().getScaledInstance(50,50,50));
            jLabel4.setIcon(im);
            jLabel4.setSize(70, 70);
            jLabel4.setVerticalAlignment(JLabel.CENTER);
            jLabel4.setHorizontalAlignment(JLabel.CENTER);
            jLabel4.setBorder(null);
        } else if (i==4) {
            if (this.dispMov.nickUsuLogueado.equals("")){
                ic = new ImageIcon("images/login.png");
                jLabel3.setText("Iniciar Sesion");
            } else {
                ic = new ImageIcon("images/logout.png");
                jLabel3.setText("Cerrar Sesion");
            }
            ImageIcon im = new ImageIcon(ic.getImage().getScaledInstance(50,50,50));
            jLabel5.setIcon(im);
            jLabel5.setSize(70, 70);
            jLabel5.setVerticalAlignment(JLabel.CENTER);
            jLabel5.setHorizontalAlignment(JLabel.CENTER);
            jLabel5.setBorder(null);
        }
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelError = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(null);

        jLabel2.setSize(50,50);
        javax.swing.ImageIcon im = new javax.swing.ImageIcon("images/pencas.png");
        javax.swing.ImageIcon image = new javax.swing.ImageIcon(im.getImage().getScaledInstance(50,50,50));
        jLabel2.setIcon(image);
        jLabel2.setText("jLabel2");
        jLabel2.setPreferredSize(new java.awt.Dimension(70, 70));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });
        add(jLabel2);
        jLabel2.setBounds(60, 150, 70, 70);

        jLabel1.setSize(50,50);
        javax.swing.ImageIcon im2 = new javax.swing.ImageIcon("images/apuestas.png");
        javax.swing.ImageIcon image2 = new javax.swing.ImageIcon(im2.getImage().getScaledInstance(50,50,50));
        jLabel1.setIcon(image2);
        jLabel1.setText("jLabel1");
        jLabel1.setPreferredSize(new java.awt.Dimension(70, 70));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
        });
        add(jLabel1);
        jLabel1.setBounds(170, 150, 70, 70);

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("jLabel3");
        add(jLabel3);
        jLabel3.setBounds(90, 70, 110, 14);

        jLabel4.setText("jLabel4");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel4MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }
        });
        add(jLabel4);
        jLabel4.setBounds(60, 250, 70, 70);

        jLabel5.setText("jLabel5");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
        });
        add(jLabel5);
        jLabel5.setBounds(170, 250, 70, 70);

        jLabelError.setForeground(new java.awt.Color(255, 0, 0));
        jLabelError.setText("jLabel6");
        add(jLabelError);
        jLabelError.setBounds(20, 360, 270, 15);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        deseleccionar(2);
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // TODO add your handling code here:
        deseleccionar(2);
        deseleccionar(3);
        deseleccionar(4);
        seleccionar(1);
        seleccion=1;

    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // Deseleccionamos el primero
        deseleccionar(1);
        deseleccionar(3);
        deseleccionar(4);
        seleccionar(2);
        seleccion=2;
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        deseleccionar(1);
    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
        deseleccionar(1);
        deseleccionar(2);
        deseleccionar(4);
        seleccionar(3);
        seleccion=3;
    }//GEN-LAST:event_jLabel4MouseEntered

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        deseleccionar(1);
        deseleccionar(2);
        deseleccionar(3);
        seleccionar(4);
        seleccion=4;
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
        deseleccionar(3);
    }//GEN-LAST:event_jLabel4MouseExited

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        deseleccionar(4);
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

        Competiciones ap = new Competiciones(dispMov);
        ap.setSize(320,420);
        dispMov.jDesktopPane1.add(ap);
        ap.setVisible(true);
        this.removeAll();
        
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        if (!((DispositivoMovil)this.dispMov).nickUsuLogueado.equals("")) {
            ParticiparEnPenca ap = new ParticiparEnPenca(dispMov);
            ap.setSize(320,420);
            dispMov.jDesktopPane1.add(ap);
            ap.setVisible(true);
            this.removeAll();
        } else {
            this.jLabelError.setText("No inicio sesion");
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked

        if (jLabel3.getText().equals("Iniciar Sesion")) {
            InicioSesion ini = new InicioSesion(this.dispMov);
            ini.setSize(320,420);
            dispMov.jDesktopPane1.add(ini);
            ini.setVisible(true);
            this.removeAll();
            this.dispMov.jDesktopPane1.remove(this);
            
        } else {
            try{
                if (this.dispMov.online){
                    if (this.dispMov.nickUsuLogueado.equals("")) {
                        this.jLabelError.setText("Debe iniciar sesion");
                    } else{
                        //nos conectamos para obtener las cosas
                        String dir = null;
                        Properties prop = new Properties();
                        FileInputStream fis = new FileInputStream("web_services.xml");
                        prop.loadFromXML(fis);
                        dir = prop.getProperty("dir_publicar");

                        DispMovilWSService dms = new DispMovilWSService(new java.net.URL("http://"+dir+":8280/dispMovilWS?wsdl"),
                                     new javax.xml.namespace.QName("http://WebServices/", "DispMovilWSService"));

                        DispMovilWS disp = dms.getDispMovilWSPort();
                        disp.cerrarSesion(this.dispMov.nickUsuLogueado);

                        this.jLabelError.setText("Se ha cerrar la sesion con exito");

                        ImageIcon im = new ImageIcon("images/login.png");
                        jLabel5.setIcon(new ImageIcon(im.getImage().getScaledInstance(60, 60, 60)));
                        jLabel3.setText("Iniciar Sesion");

                        this.dispMov.nickUsuLogueado="";

                    }
                }else{
                    this.jLabelError.setText("Debe estar conectado");
                }
            }catch(Exception e){
                this.jLabelError.setText("Perdio conexion. No cerro sesion");
                dispMov.online = false;
                ImageIcon imIcon= new ImageIcon("images/desconectado.png");
                jLabel1.setIcon(new ImageIcon(imIcon.getImage().getScaledInstance(20,20,20)));
                dispMov.timer.start();
            }
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        try{
            if (this.dispMov.online){
                if (this.dispMov.nickUsuLogueado.equals("")) {
                    this.jLabelError.setText("Debe iniciar sesion");
                }
                else{
                    //nos conectamos para obtener las cosas
                    String dir = null;
                    Properties prop = new Properties();
                    FileInputStream fis = new FileInputStream("web_services.xml");
                    prop.loadFromXML(fis);
                    dir = prop.getProperty("dir_publicar");

                    DispMovilWSService dms = new DispMovilWSService(new java.net.URL("http://"+dir+":8280/dispMovilWS?wsdl"),
                                 new javax.xml.namespace.QName("http://WebServices/", "DispMovilWSService"));

                    DispMovilWS disp = dms.getDispMovilWSPort();
                    DataUsuario u = disp.verPerfilUsuarioLogueado(this.dispMov.nickUsuLogueado);

                    //creamos el jpanel del perfil
                    Perfil ini = new Perfil(this.dispMov,u);
                    ini.setSize(320,420);
                    dispMov.jDesktopPane1.add(ini);
                    ini.setVisible(true);
                    this.removeAll();
                    this.dispMov.jDesktopPane1.remove(this);

                }
            }else{
                this.jLabelError.setText("No existe conexion");
            }
        }catch(Exception e){
            if (e.getMessage().contains("WSDL")) {
                this.jLabelError.setText("Se perdio la conexion");
                dispMov.online = false;
                ImageIcon imIcon= new ImageIcon("images/desconectado.png");
                this.dispMov.jLabel1.setIcon(new ImageIcon(imIcon.getImage().getScaledInstance(20,20,20)));
                this.dispMov.timer.start();
            }else{
                this.jLabelError.setText(e.getMessage());
            }
        }


    }//GEN-LAST:event_jLabel4MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelError;
    // End of variables declaration//GEN-END:variables

    private int seleccion = 1;
    private DispositivoMovil dispMov;
    private MenuPrincipal menu;
}
