/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JFileChooser;

/**
 *
 * @author nrochas
 */
public class FenFileChooser extends javax.swing.JFrame {

    private final FenStudy prec;

    /**
     * Creates new form FenFileChooser
     *
     * @param prec
     */
    public FenFileChooser(FenStudy prec) {
        this.prec = prec;
        initComponents();
        this.setLocation(prec.getLocation());
        fileCho.setApproveButtonText("Select");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileCho = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fileCho.setBackground(style.GraphicCharter.colorYellow);
        fileCho.setCurrentDirectory(new java.io.File("D:\\Projets"));
        fileCho.setDialogTitle("");
        fileCho.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        fileCho.setFont(style.GraphicCharter.fontCorps);
        fileCho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fileCho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fileCho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileChoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChoActionPerformed
        int retour = fileCho.showOpenDialog(this);
        String select;
        if (retour == JFileChooser.APPROVE_OPTION) {
            select = fileCho.getSelectedFile().getAbsolutePath() + "/";
            System.out.println("select: " + select);
            prec.setFilechoice(select);
        }
        this.dispose();

    }//GEN-LAST:event_fileChoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fileCho;
    // End of variables declaration//GEN-END:variables
}