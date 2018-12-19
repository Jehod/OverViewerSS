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
import java.io.IOException;
import style.GraphicCharter;
import view.FenMain;

/**
 *
 * @author nrochas
 */
public class OverViewerSS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        GraphicCharter gc=  GraphicCharter.getInstance();
       
        // donner le chemin absolu pour le test
        
        //path pour tester sur ordi perso
        String path ="F:\\Kayentis\\Projets\\CAIN457M2302";
        String pathLabels ="\\Settings\\Labels\\";
        
        //path pour tester sur ordi pro
        //String path ="D:\\project\\CAIN457M2302";
        //String pathLabels ="\\Settings\\Labels\\";
       
    
        
        //lancer le tracker
       // metier.OverView ov = new metier.OverView(path, pathLabels);
        //ov.overview();
        
        //via la fenetre
        FenMain ff = new FenMain();
       
        
       
    }
    
}