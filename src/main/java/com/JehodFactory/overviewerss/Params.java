/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JehodFactory.overviewerss;

import Outils.Check;
import Outils.JsonWorker;
import entity.SimpleStudyParam;
import static java.awt.image.ImageObserver.ERROR;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author nik
 */
public class Params {

    //nom du fichier de settings 
    private final String settings = "OverViewer_Settings.json";
    //recup de la variable d'environnement
    private final String bin = System.getenv("bin") + "\\";

    private JsonWorker jw;

    //les données selectionnées
    public String studyName;
    public String studyPath;

    //les données du fichier de settings
    public ArrayList<String> listStudy;
    public SimpleStudyParam studyParam;

    Params() {
        System.out.println("bin:++++ " + bin);
        init();
    }

    public static Params getInstance() {
        return SettingsHolder.INSTANCE;
    }

    private static class SettingsHolder {

        private static final Params INSTANCE = new Params();
    }

    /**
     * methode d'initialisation pour travailler le fichier json
     */
    private void init() {

        jw = new JsonWorker(bin + settings);
        listStudy = jw.getJsonTableau("studies");

    }

    /**
     * une fois l'etude connue, va creer le param specific de celle ci elle
     * appelle le constructeur du studyparam en donnant les attributs pris de le
     * settings json
     *
     * @param studyName l'etude choisie
     */
    public void accedeStudy(String studyName) {
        this.studyName = studyName;

        // on recupere toutes les infos du Json pour les stocker dans le study Params
        ArrayList<String> listPath = jw.getListcibleOfStudy(studyName, "path");
        String trad = jw.getValueCibleOfStudy(studyName, "Trad");
        String tabModel = jw.getValueCibleOfStudy(studyName, "Tablet");
        Boolean font = jw.getBooleanCibleOfStudy(studyName, "Font");
        System.out.println("le svn DOc depuis le accede study"+jw.getValueCibleOfStudy(studyName, "pathSvnDoc"));
        String pathSvnDoc = jw.getValueCibleOfStudy(studyName, "pathSvnDoc").replace("Ã©", "é");
        String pathSvnDel = jw.getValueCibleOfStudy(studyName, "pathSvnDel").replace("Ã©", "é");
        

        System.out.println("pathSvnDoc: "+ pathSvnDoc);
        
        //this.studyParam = new SimpleStudyParam(listPath, trad, pathLabels, pathScreens, pathCertifs, tabModel, font, new HashMap<>());
        this.studyParam = new SimpleStudyParam(listPath, trad, new HashMap<>(), tabModel, font, pathSvnDoc, pathSvnDel);

      
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    public boolean svgStudyParam(String studyName, SimpleStudyParam ssp) {
        Boolean bob = false;

        if (!Check.checkIsIn(studyName, listStudy)) {

            bob = jw.setNewStudyName(studyName);
            if (bob) {
                bob = jw.fillStudy(studyName, ssp.getTrad(), ssp.getTabModel(), ssp.getFontSamsung(),
                        ssp.getListStudyPath(), ssp.getPathSvnDoc(), ssp.getPathSvnDel(), ssp.getMap());
            }
        } else {
            JOptionPane.showMessageDialog(null, "A study with same name already exist", "Error", ERROR);
        }

        return bob;

    }

    /**
     * envoie la list des path dans le json de la study
     *
     * @param studyName
     * @param listPath
     * @return 
     */
    public boolean svgListStudy(String studyName, ArrayList listPath) {
        boolean bob = false;

        if (studyName != null && listPath != null && !listPath.isEmpty()) {
            
            bob = jw.setListInStudy(studyName, listPath);
        }

        return bob;
    }

    public void setStudyPath(String studyPath) {
        this.studyPath = studyPath;
    }
    
    
}
