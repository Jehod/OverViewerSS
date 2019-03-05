/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.interfaceEntity.StudyParams;
import Outils.Check;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author nik
 */
public class SimpleStudyParam implements StudyParams {

    //valeur par defaut pour eviter les champs vides
    private final String def = "Not Found";

    private ArrayList<String> listStudyPath;  // D:/project/<PROJET>/Trunk/)
    private String trad = def;
    private String pathLabels = "Settings/Labels/";
    private String pathScreens = "Scripts/Screenshots/";
    private HashMap<String, String> map;
    private String tabModel = def;
    private String pathCertifs = "2-Certifications/1-Language/";
    private boolean fontSamsung;
    private String pathSvnDoc = def;   //svn://document.kayentis.fr:15000/kayentis/Documentation/Projets/Santé/<CLIENT>/<PROJET>/3- Functional scope/2- Forms/2- Kayentis design/
    private String pathSvnDel = def;   ///svn://svn.kayentis.fr:14000/Kayentis/<CLIENT>/<PROJET>/Platforms/Trunk/ 
    private String pathFinalsScreens = "1-Final/2-Language/";

    public SimpleStudyParam(ArrayList<String> listStudyPath, String trad, HashMap<String, String> map,
            String tabModel, boolean fontSamsung, String pathSvnDoc, String pathSvnDel) {
        this.listStudyPath = listStudyPath;
        if (Check.isGood(trad)) {
            this.trad = trad;
        }
        this.map = map;
        if (Check.isGood(tabModel)) {
            this.tabModel = tabModel;
        }
        this.fontSamsung = fontSamsung;
        if (Check.isGood(pathSvnDoc)) {
            this.pathSvnDoc = pathSvnDoc;
        }
        if (Check.isGood(pathSvnDel)) {
            this.pathSvnDel = pathSvnDel;
        }
    }

    @Override
    public String getTrad() {
        return trad;
    }

    @Override
    public HashMap<String, String> getMap() {
        return map;
    }

    @Override
    public String getPathScreens() {
        return pathScreens;
    }

    @Override
    public String getPathLabels() {
        return pathLabels;
    }

    @Override
    public String getTabModel() {
        return tabModel;
    }

    @Override
    public ArrayList<String> getListStudyPath() {
        return listStudyPath;
    }

    public void setListStudyPath(ArrayList<String> listStudyPath) {
        this.listStudyPath = listStudyPath;
    }

    public void setTrad(String trad) {
        this.trad = trad;
    }

    public void setPathLabels(String pathLabels) {
        this.pathLabels = pathLabels;
    }

    public void setPathScreens(String pathScreens) {
        this.pathScreens = pathScreens;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }

    public void setTabModel(String tabModel) {
        this.tabModel = tabModel;
    }

    @Override
    public String getPathCertifs() {
        return pathCertifs;
    }

    @Override
    public boolean getFontSamsung() {
        return fontSamsung;
    }

    public void setPathCertifs(String pathCertifs) {
        this.pathCertifs = pathCertifs;
    }

    public void setFontSamsung(boolean fontSamsung) {
        this.fontSamsung = fontSamsung;
    }

    @Override
    public String getPathFinalsScreens() {
        return pathFinalsScreens;
    }

    @Override
    public String getPathSvnDoc() {
        return pathSvnDoc;
    }

    @Override
    public String getPathSvnDel() {
        return pathSvnDel;
    }

    public void setPathSvnDoc(String pathSvnDoc) {
        if (!pathSvnDoc.endsWith("/")) {
            pathSvnDoc += "/";
        }
        this.pathSvnDoc = pathSvnDoc;
    }

    public void setPathSvnDel(String pathSvnDel) {
        if (!pathSvnDel.endsWith("/")) {
            pathSvnDel += "/";
        }
        this.pathSvnDel = pathSvnDel;
    }

    public void setPathFinalsScreens(String pathFinalsScreens) {
        this.pathFinalsScreens = pathFinalsScreens;
    }

}
