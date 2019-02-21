/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.JsonStudyParamsDAO;
import Outils.Check;
import view.generikForms.FenGenerik;
import view.generikForms.ButtonGenerik;
import com.JehodFactory.overviewerss.Params;
import entity.SimpleStudyParam;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import model.ComboModel;
import style.GraphicCharter;
import view.generikForms.ButtonCancel;
import view.generikForms.ButtonRefresh;
import view.generikForms.LabelGenerik;
import view.generikForms.PanBackGenerik;

/**
 *
 * @author nrochas
 */
public class FenStudy extends FenGenerik {

   private final String studyName = Params.getInstance().studyName;
   private final SimpleStudyParam ssp = Params.getInstance().studyParam;
    ArrayList<String> list = ssp.getListStudyPath();

   private String studyPath;
   private final String pathSvnDel = ssp.getPathSvnDel();
   private final String pathSvnDoc = ssp.getPathSvnDoc();
    
   private String select;

    private final FenSelectStudy prec;

    /**
     * Creates new form FenStudy
     * @param fss fenetre precedente
     */
    public FenStudy(FenSelectStudy fss) {

        //on note la fenetre d'avant pour le retour arriere
        prec = fss;
        Dimension dim = this.getToolkit().getScreenSize();

        initComponents();

        this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
        //this.setSize(400, 400);
        this.setVisible(true);
        //on cache le browser
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel2 = new PanBackGenerik();
        jLabel1 = new LabelGenerik();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        butLaunchLocal = new ButtonGenerik();
        comboStudyPath = new javax.swing.JComboBox<>();
        butAddPath = new ButtonGenerik();
        jLabel3 = new LabelGenerik();
        panTrachSvn = new javax.swing.JPanel();
        butLaunchSVN = new ButtonGenerik();
        jLabel2 = new LabelGenerik();
        jPanel3 = new javax.swing.JPanel();
        butViewStudy = new ButtonGenerik();
        jButton1 = new ButtonRefresh();
        butCancel = new ButtonCancel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Study");
        setBackground(style.GraphicCharter.colorYellow);

        jLabel1.setBackground(GraphicCharter.colorBlue    );
        jLabel1.setFont(style.GraphicCharter.titre1);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(studyName);
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setOpaque(true);

        jPanel1.setBackground(style.GraphicCharter.colorBack);
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(GraphicCharter.titre3);
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Local Path:  ");
        jLabel6.setToolTipText("");

        butLaunchLocal.setText("Track Local");
        butLaunchLocal.setToolTipText("Analysis of local work. No contact with Svn");
        butLaunchLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butLaunchLocalActionPerformed(evt);
            }
        });

        comboStudyPath.setFont(GraphicCharter.titre3
        );
        comboStudyPath.setMaximumRowCount(10);
        comboStudyPath.setModel(new ComboModel(list));

        butAddPath.setText("...");
        butAddPath.setToolTipText("Browser");
        butAddPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAddPathActionPerformed(evt);
            }
        });

        jLabel3.setFont(style.GraphicCharter.titre2);
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tracker Local");
        jLabel3.setToolTipText("Track on working copy No onLine action");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboStudyPath, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(butAddPath, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(butLaunchLocal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(comboStudyPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butAddPath))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(butLaunchLocal)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panTrachSvn.setBackground(style.GraphicCharter.colorBack);
        panTrachSvn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        butLaunchSVN.setText("Track SVN");
        butLaunchSVN.setToolTipText("Use SVN1400 and SVN1500. Put no file in local depositoy");
        butLaunchSVN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butLaunchSVNActionPerformed(evt);
            }
        });

        jLabel2.setFont(style.GraphicCharter.titre2);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tracker OnLine");
        jLabel2.setToolTipText("Track on Svn. No local action");

        javax.swing.GroupLayout panTrachSvnLayout = new javax.swing.GroupLayout(panTrachSvn);
        panTrachSvn.setLayout(panTrachSvnLayout);
        panTrachSvnLayout.setHorizontalGroup(
            panTrachSvnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panTrachSvnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(butLaunchSVN)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panTrachSvnLayout.setVerticalGroup(
            panTrachSvnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panTrachSvnLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(butLaunchSVN)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel3.setBackground(GraphicCharter.colorYellow);

        butViewStudy.setText("View Study Details");
        butViewStudy.setToolTipText("Give some details and possibility of modification ");
        butViewStudy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butViewStudyActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/refresh1.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        butCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(butCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(butViewStudy)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(butCancel)
                    .addComponent(butViewStudy)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panTrachSvn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(panTrachSvn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butLaunchSVNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_butLaunchSVNActionPerformed
    {//GEN-HEADEREND:event_butLaunchSVNActionPerformed

        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Confirm complete path:" + pathSvnDel + " \r This method put nothing in local. Please update your working copy after.", "confirmation", dialogButton);

        if (dialogResult == JOptionPane.YES_OPTION) {
            Params.getInstance().setStudyPath(pathSvnDel);

            this.dispose();
            FenProgress fenp = new FenProgress();
            fenp.setVisible(true);
            metier.OverView ov = new metier.OverView(pathSvnDel,pathSvnDoc, false);
            ov.overview();

        }

    }//GEN-LAST:event_butLaunchSVNActionPerformed

    private void butAddPathActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_butAddPathActionPerformed
    {//GEN-HEADEREND:event_butAddPathActionPerformed
       
        FenFileChooser ff = new FenFileChooser(this);
        ff.setVisible(true);
      
    }//GEN-LAST:event_butAddPathActionPerformed

    private void butViewStudyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butViewStudyActionPerformed
        FenViewStudy fw = new FenViewStudy(this);
        this.setVisible(false);
    }//GEN-LAST:event_butViewStudyActionPerformed

    private void butCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCancelActionPerformed
        prec.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_butCancelActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        FenStudy fs = new FenStudy(prec);

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * methode qui recupere la selection du fileChooser et l'insert dans la comboBox
     * @param selection 
     */
    public void setFilechoice(String selection)
    {
        select = selection;

            if (!Check.checkIsIn(select, list)) {
                list.add(select);
            }
            comboStudyPath.setSelectedItem(select);
            comboStudyPath.repaint();
        
        
    }
    private void butLaunchLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butLaunchLocalActionPerformed

        studyPath = (String) comboStudyPath.getSelectedItem();

        if (!Check.isGood(studyPath)) {
            JOptionPane.showMessageDialog(null, "Please select a study ", "Error Filling conboBox", ERROR_MESSAGE);
            comboStudyPath.setBackground(Color.red);
            comboStudyPath.repaint();
        } else {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Confirm complete path:" + studyPath + " \r  This method has no contact with svn. Please commit after.", "confirmation", dialogButton);
           
            if (dialogResult == JOptionPane.YES_OPTION) {
                //on met a jour les path de la study
                Params.getInstance().setStudyPath(studyPath);
                new JsonStudyParamsDAO().addStudyPath(studyName, studyPath);                        
                
                //on ouvre et ferme les vues 
                this.dispose();
                FenProgress fenp = new FenProgress();
                fenp.setVisible(true);
                //et on lance le traitement proprement dit
                new metier.OverView(studyPath, true).overview();
                
            }
        }
    }//GEN-LAST:event_butLaunchLocalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butAddPath;
    private javax.swing.JButton butCancel;
    private javax.swing.JButton butLaunchLocal;
    private javax.swing.JButton butLaunchSVN;
    private javax.swing.JButton butViewStudy;
    private javax.swing.JComboBox<String> comboStudyPath;
    private javax.swing.JButton jButton1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel panTrachSvn;
    // End of variables declaration//GEN-END:variables
}
