/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Outils.Check;
import com.JehodFactory.overviewerss.Params;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import model.ListMapModel;
import style.GraphicCharter;
import view.generikForms.ButtonCancel;
import view.generikForms.ButtonGenerik;
import view.generikForms.ButtonRefresh;
import view.generikForms.FenGenerik;
import view.generikForms.PanBackGenerik;

/**
 *
 * @author nrochas
 */
public class FenMapping extends FenGenerik {

    private final String inter = " ~~~~~ ";

    JFrame prec;
    HashMap map;
    String key;
    String val;
    Params params = Params.getInstance();

    /**
     * Creates new form FenMapping
     *
     * @param prec
     */
    public FenMapping(JFrame prec) {
        this.prec = prec;
        map = Params.getInstance().studyParam.getMap();

        Dimension dim = this.getToolkit().getScreenSize();

        initComponents();

        this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
        //this.setSize(450, 400);
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

        jPanel1 = new PanBackGenerik();
        labTitle = new javax.swing.JLabel();
        cle1 = new javax.swing.JTextField();
        value1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labInter = new javax.swing.JLabel();
        butMapp = new ButtonGenerik();
        butrefresh = new ButtonRefresh();
        butCancel = new ButtonCancel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listMapp = new javax.swing.JList<>();
        butREmove = new view.generikForms.ButtonGenerik();
        butAdd = new view.generikForms.ButtonGenerik();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labTitle.setBackground(GraphicCharter.colorBlue);
        labTitle.setFont(style.GraphicCharter.titre1);
        labTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labTitle.setText("Mapping");
        labTitle.setToolTipText("");
        labTitle.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        labTitle.setOpaque(true);

        cle1.setToolTipText("ex: EQ5D5L_Digital_Proxy");

        value1.setToolTipText("ex: EQ5D_5L_PROXY");

        jLabel1.setText("Label's name");

        jLabel2.setText("Screenshot's Name");

        labInter.setText(inter);

        butMapp.setText("Save n Go");
        butMapp.setToolTipText("save the map in Json config");
        butMapp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butMappActionPerformed(evt);
            }
        });

        butrefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/refresh1.png"))); // NOI18N
        butrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butrefreshActionPerformed(evt);
            }
        });

        butCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butCancelActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        listMapp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        listMapp.setModel(new model.ListMapModel(inter));
        listMapp.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listMapp.setToolTipText("select a line to remove or modify a couple");
        jScrollPane1.setViewportView(listMapp);

        butREmove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flecheB.png"))); // NOI18N
        butREmove.setToolTipText("remove or modifiy a couple");
        butREmove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butREmoveActionPerformed(evt);
            }
        });

        butAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flecheH.png"))); // NOI18N
        butAdd.setToolTipText("add couple filled in textFields");
        butAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(butCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(butrefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(butMapp))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(cle1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labInter, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(value1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(butREmove, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(butAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(360, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(butREmove, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(value1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labInter))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(butrefresh)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(butCancel, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(butMapp, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(135, 135, 135)
                    .addComponent(butAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(155, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butMappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butMappActionPerformed

        params.svgMapStudy(params.getStudyName(), map);
        JOptionPane.showConfirmDialog(this, "Map saved", "confirm", INFORMATION_MESSAGE);
        this.dispose();
        prec.setVisible(true);
    }//GEN-LAST:event_butMappActionPerformed

    private void butrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butrefreshActionPerformed

        FenMapping fc = new FenMapping(prec);
        this.dispose();
    }//GEN-LAST:event_butrefreshActionPerformed

    private void butCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCancelActionPerformed
        prec.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_butCancelActionPerformed

    private void butAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAddActionPerformed
        key = cle1.getText();
        val = value1.getText();
        if (Check.isGood(key) && Check.isGood(val) && !key.equals("empty") && !val.equals("empty")) {
            map.put(key, val);
            ((ListMapModel) listMapp.getModel()).add(key + inter + val);

        } else {
            cle1.setBackground(Color.RED);
            value1.setBackground(Color.red);
            cle1.repaint();
            value1.repaint();
        }
    }//GEN-LAST:event_butAddActionPerformed

    private void butREmoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butREmoveActionPerformed
        //recup de la valeur
        String sl = listMapp.getSelectedValue();
        //mise a jour de la list
        if (Check.isGood(sl)) {
            ((ListMapModel) listMapp.getModel()).remove(sl);
            //decoupe pour la map
            String[] t = sl.split(inter);
            key = t[0];
            val = t[1];
            //mise a jour de la map
            map.remove(key, val);
            //affichage dans les selections
            cle1.setText(key);
            value1.setText(val);
        } else {
            cle1.setText("empty");
            value1.setText("empty");
        }
    }//GEN-LAST:event_butREmoveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butAdd;
    private javax.swing.JButton butCancel;
    private javax.swing.JButton butMapp;
    private javax.swing.JButton butREmove;
    private javax.swing.JButton butrefresh;
    private javax.swing.JTextField cle1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labInter;
    private javax.swing.JLabel labTitle;
    private javax.swing.JList<String> listMapp;
    private javax.swing.JTextField value1;
    // End of variables declaration//GEN-END:variables
}
