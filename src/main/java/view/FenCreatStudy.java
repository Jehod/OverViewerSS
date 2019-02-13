/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.JsonStudyParamsDAO;
import Outils.Check;
import com.JehodFactory.overviewerss.Params;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import style.GraphicCharter;
import view.generikForms.ButtonGenerik;
import view.generikForms.FenGenerik;
import view.generikForms.LabelGenerik;

/**
 *
 * @author nrochas
 */
public class FenCreatStudy extends FenGenerik {

    /**
     * Creates new form FenCreatStudy
     */
    public FenCreatStudy() {
        Dimension dim = this.getToolkit().getScreenSize();

        initComponents();

        this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
        this.setSize(478, 400);
        this.setVisible(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabTitre = new LabelGenerik();
        panLabel = new javax.swing.JPanel();
        labname = new LabelGenerik();
        labtrad = new LabelGenerik();
        labPalb = new LabelGenerik();
        labfont = new LabelGenerik();
        labPScre = new LabelGenerik();
        labCertif = new LabelGenerik();
        labTabler1 = new LabelGenerik();
        txtfstudyname = new javax.swing.JTextField();
        txtfTrad = new javax.swing.JTextField();
        txtfTAblet = new javax.swing.JTextField();
        cBxFont = new javax.swing.JCheckBox();
        txtfPLabels = new javax.swing.JTextField();
        txtfsPscreen = new javax.swing.JTextField();
        txtfsPcertif = new javax.swing.JTextField();
        butcreate = new ButtonGenerik();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LabTitre.setFont(style.GraphicCharter.titre1);
        LabTitre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabTitre.setText("Create Study");

        labname.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labname.setText("Study Name:   ");

        labtrad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labtrad.setText("Traductor:  ");

        labPalb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labPalb.setText("Path Labels:  ");

        labfont.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labfont.setText("Font:  ");

        labPScre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labPScre.setText("Path Screenshots:  ");

        labCertif.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labCertif.setText("Path Certificats:  ");

        labTabler1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labTabler1.setText("Tablet Model:  ");

        txtfstudyname.setFont(GraphicCharter.fontCorps);

        txtfTrad.setFont(GraphicCharter.fontCorps);

        txtfTAblet.setFont(GraphicCharter.fontCorps);

        cBxFont.setText("Samsung Sans");

        txtfPLabels.setFont(GraphicCharter.fontCorps);
        txtfPLabels.setText("/Settings/Labels/");

        txtfsPscreen.setFont(GraphicCharter.fontCorps);
        txtfsPscreen.setText("/Scripts/Screenshots/");

        txtfsPcertif.setFont(GraphicCharter.fontCorps);
        txtfsPcertif.setText("/");

        javax.swing.GroupLayout panLabelLayout = new javax.swing.GroupLayout(panLabel);
        panLabel.setLayout(panLabelLayout);
        panLabelLayout.setHorizontalGroup(
            panLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panLabelLayout.createSequentialGroup()
                .addGroup(panLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panLabelLayout.createSequentialGroup()
                            .addComponent(labfont, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(10, 10, 10)
                            .addComponent(cBxFont)
                            .addGap(129, 129, 129))
                        .addGroup(panLabelLayout.createSequentialGroup()
                            .addComponent(labPalb, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtfPLabels, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panLabelLayout.createSequentialGroup()
                            .addGroup(panLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(labPScre, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                .addComponent(labCertif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(panLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtfsPscreen, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                .addComponent(txtfsPcertif))))
                    .addGroup(panLabelLayout.createSequentialGroup()
                        .addGroup(panLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labtrad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labTabler1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtfTrad, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                            .addComponent(txtfstudyname)
                            .addComponent(txtfTAblet))))
                .addContainerGap())
        );
        panLabelLayout.setVerticalGroup(
            panLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panLabelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labname)
                    .addComponent(txtfstudyname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(panLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labtrad)
                    .addComponent(txtfTrad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labTabler1)
                    .addComponent(txtfTAblet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(panLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labfont)
                    .addComponent(cBxFont))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labPalb)
                    .addComponent(txtfPLabels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labPScre)
                    .addComponent(txtfsPscreen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labCertif)
                    .addComponent(txtfsPcertif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        butcreate.setText("Create Study");
        butcreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butcreateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabTitre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(butcreate)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LabTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(butcreate)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butcreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butcreateActionPerformed

        Boolean bob;

        String name = txtfstudyname.getText().trim().toUpperCase();
        String trad = txtfTrad.getText().trim().toUpperCase();
        String tablet = txtfTAblet.getText().trim().toUpperCase();
        String pathL = txtfPLabels.getText().trim();
        String pathS = txtfsPscreen.getText().trim();
        String pathC = txtfsPcertif.getText().trim();
        Boolean font = cBxFont.isSelected();

        if (Outils.Check.isGood(name) && Check.isGood(trad) && Check.isGood(tablet)
                && Check.isGood(pathL) && Check.isGood(pathS) && Check.isGood(pathC)) {
            JsonStudyParamsDAO jsp = new JsonStudyParamsDAO();
            bob = jsp.createStudy(name, trad, tablet, font, pathL, pathS, pathC);
            
            if (bob) {
                
                Params.getInstance().setStudyName(name);
                Params.getInstance().accedeStudy(name);
                FenStudy fen = new FenStudy();
                this.setVisible(false);
                
            } else {
                JOptionPane.showMessageDialog(null, "Probleme lors de l'enregistrement de la study", "Error", ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs", "Error", ERROR_MESSAGE);
        }

    }//GEN-LAST:event_butcreateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabTitre;
    private javax.swing.JButton butcreate;
    private javax.swing.JCheckBox cBxFont;
    private javax.swing.JLabel labCertif;
    private javax.swing.JLabel labPScre;
    private javax.swing.JLabel labPalb;
    private javax.swing.JLabel labTabler1;
    private javax.swing.JLabel labfont;
    private javax.swing.JLabel labname;
    private javax.swing.JLabel labtrad;
    private javax.swing.JPanel panLabel;
    private javax.swing.JTextField txtfPLabels;
    private javax.swing.JTextField txtfTAblet;
    private javax.swing.JTextField txtfTrad;
    private javax.swing.JTextField txtfsPcertif;
    private javax.swing.JTextField txtfsPscreen;
    private javax.swing.JTextField txtfstudyname;
    // End of variables declaration//GEN-END:variables
}
