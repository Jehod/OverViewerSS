/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JehodFactory.overviewerss;

import Outils.JsonWorker;
import entity.SimpleStudyParam;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author nik
 */
public class Params
{
    //nom du fichier de settings 
  private final String settings = "OverViewer_Settings.json";
  //recup de la variable d'environnement
  private final String bin = System.getenv("bin")+"\\";
    
  private JsonWorker jw; 
   
    
    //les données selectionnées
    public String studyName;
    public String studyPath;
    
    //les données du fichier de settings
    public ArrayList<String> listStudy;
    public SimpleStudyParam studyParam;
    
    
    
        
    
    Params()
    {
        System.out.println("bin:++++ "+bin);
        init();
    }
    
    public static Params getInstance()
    {
        return SettingsHolder.INSTANCE;
    }

    private static class SettingsHolder
    {

        private static final Params INSTANCE = new Params();
    }
    
    /**
     * methode d'initialisation pour travailler le fichier json
     */
      private void init()
    {   
      
        jw = new JsonWorker(bin+settings);
        listStudy = jw.getJsonTableau("studies");
        
    }
      
    /**
     * une fois l'etude connue, va creer le param specific de celle ci
     * elle appelle le constructeur du studyparam en donnant les attributs pris de le settings json
     * @param studyName l'etude choisie
     */  
    public void accedeStudy(String studyName)
    {
        this.studyName = studyName;
        
        studyPath = (String) jw.getListcibleOfStudy(studyName, "path").get(0);
        
        
        ArrayList<String> listPath = jw.getListcibleOfStudy(studyName, "path");
        
        String trad = jw.getValueCibleOfStudy(studyName, "Trad");
        String pathLabels = jw.getValueCibleOfStudy(studyName,"pathLabels" );
        String pathScreens = jw.getValueCibleOfStudy(studyName,"pathScreens" );
        String tabModel = jw.getValueCibleOfStudy(studyName, "Tablet");
        String pathCertifs = jw.getValueCibleOfStudy(studyName, "pathCertifs");
        Boolean font = jw.getBooleanCibleOfStudy(studyName, "Font");
        
        this.studyParam = new SimpleStudyParam(listPath, trad, pathLabels, pathScreens,pathCertifs, tabModel,font, new HashMap<>());
        
        System.out.println("test+++++++"+jw.getValueCibleOfStudy("CAIN457M2302","name"));
        System.out.println("test+++++++"+jw.getValueCibleOfStudy("test2","trad"));
        
        System.out.println("test+++"+jw.getListcibleOfStudy("CAIN457M2302", "path"));
    }

    public void setStudyName(String studyName)
    {
        this.studyName = studyName;
    }
    
    
}
