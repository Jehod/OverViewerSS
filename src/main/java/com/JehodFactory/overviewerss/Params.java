/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JehodFactory.overviewerss;

import Outils.JsonWorker;
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
   
    
    //les données du fichier de settings
    public String studyName;
    public String pathLabels;
    public String pathScreens;
    public String trad;
    public String path;
    public ArrayList<String> listPath;
    public HashMap<String,String> map;
    
        
    
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
    
      private void init()
    {   
      
        jw = new JsonWorker(bin+settings);
        jw.getJsonTableau("studies");
        
        
 
    }
      
    
    
}
