/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DetallesUsuario.java
 *
 * Created on 04-sep-2011, 20:46:48
 */

package Formularios;

import Interface.Fabrica;
import Interface.IAdminUsuarios;
import DataTypes.DataUsuario;
/**
 *
 * @author CAPablito
 */
public class DetallesUsuario extends javax.swing.JInternalFrame {

    /** Creates new form DetallesUsuario */
    public DetallesUsuario(String nick,Tarea1 parent, IAdminUsuarios iau) {
        this.parent=parent;
        parent.setModoActivado(false);//no hace falta

        java.awt.Component content = this.parent.getContentPane();
        int x = (content.getWidth() - this.getWidth()) / 2;
        int y = (content.getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);

        
        
        initComponents();

        //Fabrica f = new Fabrica();
        //IAdminUsuarios icu = f.getIAdminUsuarios();


        try {
            DataUsuario du = iau.seleccionarUsuario(nick);
            nombre.setText(nombre.getText().concat(du.getNombre()));
            nickl.setText(nickl.getText().concat(du.getNick()));
            email.setText(email.getText().concat(du.getEmail()));
            dir.setText(dir.getText().concat(du.getDir()));
            ci.setText(ci.getText().concat(du.getCI()));
            fecha.setText(fecha.getText().concat(du.getFechaNac().toString()));
            pais.setText(pais.getText().concat(du.getPais()));
            sexo.setText(sexo.getText().concat(du.getSexo().toString()));
            saldo.setText(saldo.getText().concat(Float.toString(du.getSaldo())));
        }
        catch (Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, "Error: "+ e.getMessage(),"Error",javax.swing.JOptionPane.ERROR_MESSAGE);
            parent.setModoActivado(true);
            

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

        jPanel1 = new javax.swing.JPanel();
        nombre = new javax.swing.JLabel();
        nickl = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        dir = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        ci = new javax.swing.JLabel();
        pais = new javax.swing.JLabel();
        sexo = new javax.swing.JLabel();
        saldo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setTitle("Detalles De Usuario ");
        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);
        setVisible(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles De Usuario"));

        nombre.setText("Nombre :");

        nickl.setText("Nick :");

        email.setText("Email :");

        dir.setText("Direccion :");

        fecha.setText("Fecha Nacimiento :");

        ci.setText("CI :");

        pais.setText("Pais :");

        sexo.setText("Sexo :");

        saldo.setText("Saldo :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombre)
                    .addComponent(nickl)
                    .addComponent(email)
                    .addComponent(dir)
                    .addComponent(fecha)
                    .addComponent(ci)
                    .addComponent(pais)
                    .addComponent(sexo)
                    .addComponent(saldo))
                .addContainerGap(152, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nickl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(email)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ci)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pais)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sexo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saldo))
        );

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(200, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(257, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(229, 229, 229))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton1)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here
        parent.setModoActivado(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ci;
    private javax.swing.JLabel dir;
    private javax.swing.JLabel email;
    private javax.swing.JLabel fecha;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nickl;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel pais;
    private javax.swing.JLabel saldo;
    private javax.swing.JLabel sexo;
    // End of variables declaration//GEN-END:variables

    private Tarea1 parent;
}
