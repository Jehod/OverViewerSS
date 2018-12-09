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
import DAO.PoiModifTrackDAO;
import DAO.PoiRowTrackerDAO;
import java.io.IOException;
import java.util.Arrays;
import metier.Overviewer;

/**
 *
 * @author nrochas
 */
public class OverViewerSS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        Overviewer ov = new metier.Overviewer();
        
        
        PoiRowTrackerDAO prtk = new PoiRowTrackerDAO("HR_HR/Tracker_HR_HR.xlsx");
       
          System.out.println("liste recuperer par la DAO: \r"+ prtk.findAllRowTracker().get(0).toString()+ "\r"+prtk.findAllRowTracker().get(1).toString());
          
        PoiModifTrackDAO pmtk = new PoiModifTrackDAO("HR_HR/Label_LESIONCOUNT_HR_HR.xlsx");
        System.out.println("liste recuperer dans le label: \r"+pmtk.findAllModifTrack().get(0).toString());
    
        //lancer le tracker
        //ov.tracker("./");
       //Outils.XlsManager.createXLSTracker("EN_US",new DateManager().getSimpleCurrentDate());
       
    }
    
}