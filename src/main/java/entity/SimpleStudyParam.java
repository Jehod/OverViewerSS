/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author nik
 */
public class SimpleStudyParam implements StudyParams
{
    //valeur par defaut pour eviter les champs vides
    private final String def = "Not Found";
    
    private ArrayList<String> listStudyPath = null;
    private String trad= def;
    private String pathLabels= def;
    private String pathScreens=def;
    private HashMap<String,String> map =null;
    private String tabModel=def;
    

    public SimpleStudyParam(ArrayList<String> studyPath, String trad, String pathLabels, String pathScreens, String tabModel, HashMap<String, String> map)
    {
        this.listStudyPath = studyPath;
        this.trad = trad;
        this.pathLabels = pathLabels;
        this.pathScreens = pathScreens;
        this.map = map;
        this.tabModel = tabModel;
        
    }
    
   

    @Override
    public String getTrad()
    {
        return trad;
    }

    @Override
    public HashMap<String, String> getMap()
    {
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

    
   
}
