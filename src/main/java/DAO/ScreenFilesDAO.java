/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Outils.DateManager;
import java.io.File;
import java.util.Date;

/**
 *
 * @author nrochas
 */
public class ScreenFilesDAO implements ScreenshotFilesDAO {

    private String fileName;

    public ScreenFilesDAO(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean checkExistingPDF(String langue,String formulaire, String version) {
        boolean bob = false;
        String date = "None";

        if (new File(fileName+"/"+langue+"/"+formulaire+"_"+langue+"_v"+version+".pdf").exists()) {
            bob = true;
            
            System.out.println("+++++++++ "+fileName+"/"+langue+"/"+formulaire+"_"+langue+"_v"+version+".pdf"+ " a ete trouve++++");
        }

        return bob;

    }

    @Override
    public String getDateLastModifPDF(String langue, String formulaire, String version) {
        String date = "None";
        File file = new File(fileName+"/"+langue+"/"+formulaire+"_"+langue+"_v"+version+".pdf");

        if (file.exists()) {
            
            date =  new DateManager().getSimpleDate(new Date(file.lastModified()));
            System.out.println("+++++++++ "+fileName+"/"+langue+"/"+formulaire+"_"+langue+"_v"+version+".pdf"+ " a ete trouve++++");
        }

        return date;
    }

}
