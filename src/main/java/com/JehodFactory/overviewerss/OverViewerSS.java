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
import DAO.LabelFileDAO;
import DAO.PoiModifTrackDAO;
import DAO.PoiRowTrackerDAO;
import DAO.PoiTrackerDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nrochas
 */
public class OverViewerSS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        //Overviewer ov = new metier.Overviewer();
       
        
        String path ="D:\\project\\CAIN457M2302";
        String pathLabels ="\\Settings\\Labels\\";
       
    
        
        //lancer le tracker
        metier.OverViewerSS ov = new metier.OverViewerSS(path, pathLabels);
        ov.overview();
        //ov.tracker("./");
       //Outils.XlsManager.createXLSTracker("EN_US",new DateManager().getSimpleCurrentDate());
       
    }
    
}