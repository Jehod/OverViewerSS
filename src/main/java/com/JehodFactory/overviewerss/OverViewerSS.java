/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JehodFactory.overviewerss;

/**
 *
 * @author nik
 */
import java.util.logging.Logger;
import style.GraphicCharter;
import view.FenSelectStudy;

/**
 *
 * @author nrochas
 */
public class OverViewerSS {

    private static final Logger LOG = Logger.getLogger(OverViewerSS.class.getName());
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        
        GraphicCharter instance = GraphicCharter.getInstance();

        //Params params = new Params();
        
        
        //lancer le tracker
        // metier.OverView ov = new metier.OverView(path, pathLabels);
        //ov.overview();
        //via la fenetre
        FenSelectStudy fenSelectStudy;
        fenSelectStudy = new FenSelectStudy();
                
        
       
    }
    
}
