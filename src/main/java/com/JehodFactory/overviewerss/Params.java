/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JehodFactory.overviewerss;

import Outils.Check;
import Outils.JsonWorker;
import Outils.SVNWorker;
import entity.SimpleStudyParam;
import java.io.File;
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
    //le fichier temporaire pour les aller retour svn
    private final String pathTEMP = System.getenv("TEMP") + "\\ovTemp\\";

    private boolean bob;

    private JsonWorker jw;
    private SVNWorker svn;

    //les données selectionnées
    public String studyName;
    public String studyPath;

    //les données du fichier de settings
    public ArrayList<String> listStudy;
    public SimpleStudyParam studyParam;

    public File fileTemp;

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
        svn = new SVNWorker();

        //initie le chemin des fichier temp
        File t = new File(pathTEMP);
        t.mkdir();
        fileTemp = t;

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
        System.out.println("accede " + this.studyName);

        // on recupere toutes les infos du Json pour les stocker dans le study Params
        ArrayList<String> listPath = jw.getListcibleOfStudy(studyName, "path");
        String trad = jw.getValueCibleOfStudy(studyName, "Trad");
        String tabModel = jw.getValueCibleOfStudy(studyName, "Tablet");
        Boolean font = jw.getBooleanCibleOfStudy(studyName, "Font");
        HashMap map = jw.getMapCibleOfStudy(studyName, "map");
        System.out.println("la map est : " + map.toString());

        String pathSvnDoc = jw.getValueCibleOfStudy(studyName, "pathSvnDoc").replace("Ã©", "é");
        String pathSvnDel = jw.getValueCibleOfStudy(studyName, "pathSvnDel").replace("Ã©", "é");

        //this.studyParam = new SimpleStudyParam(listPath, trad, pathLabels, pathScreens, pathCertifs, tabModel, font, new HashMap<>());
        this.studyParam = new SimpleStudyParam(listPath, trad, map, tabModel, font, pathSvnDoc, pathSvnDel);

    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    /**
     * creer l'entrée au nom de l'etude puis le remplit avec les données
     *
     * @param studyName
     * @param ssp
     * @return
     */
    public boolean svgNewStudyParam(String studyName, SimpleStudyParam ssp) {

        if (ssp != null && Check.isGood(studyName)) {
            if (Check.checkIsIn(studyName, listStudy)) {
                JOptionPane.showMessageDialog(null, "A study with same name already exist", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                if (jw.setNewStudyName(studyName)) {
                    bob = jw.fillStudy(studyName, ssp.getTrad(), ssp.getTabModel(), ssp.getFontSamsung(),
                            ssp.getListStudyPath(), ssp.getPathSvnDoc(), ssp.getPathSvnDel(), ssp.getMap());
                }
            }
        } else {
            System.out.println("erreur de svg de new params");
        }
        if (bob) {
            svn.commitSVN("auto commit from bin", bin + settings);
        }

        return bob;

    }

    /**
     * svg une etude deja existante dans le setting
     *
     * @param studyName
     * @param ssp
     * @return
     */
    public boolean svgStudyParam(String studyName, SimpleStudyParam ssp) {

        if (ssp != null && Check.isGood(studyName)) {
            bob = jw.fillStudy(studyName, ssp.getTrad(), ssp.getTabModel(), ssp.getFontSamsung(),
                    ssp.getListStudyPath(), ssp.getPathSvnDoc(), ssp.getPathSvnDel(), ssp.getMap());
        } else {
            System.out.println("erreur de svg de new params");
        }
        if (bob) {
            svn.commitSVN("auto commit from bin", bin + settings);
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

        if (Check.isGood(studyName) && listPath != null && !listPath.isEmpty()) {

            bob = jw.setListInStudy(studyName, listPath);
        }
        if (bob) {
            svn.commitSVN("auto commit from bin", bin + settings);
        }
        return bob;
    }

    /**
     * envoie la map dans le json de la study
     *
     * @param studyName
     * @param map
     * @return bob
     */
    public boolean svgMapStudy(String studyName, HashMap map) {
        
        if (Check.isGood(studyName) && map != null && !map.isEmpty()) {

            bob = jw.setMapInStudy(studyName, map);
        }
        if (bob) {
            svn.commitSVN("auto commit from bin", bin + settings);
        }
        
        
        return bob;
    }

    public void setStudyPath(String studyPath) {
        if (Check.isGood(studyPath)) {
            this.studyPath = studyPath;

        }

    }

    public String getPathTEMP() {
        return pathTEMP;
    }

    public String getStudyName() {
        return studyName;
    }

    public String getStudyPath() {
        return studyPath;
    }

    public ArrayList<String> getListStudy() {
        return listStudy;
    }

    public SimpleStudyParam getStudyParam() {
        return studyParam;
    }

}
