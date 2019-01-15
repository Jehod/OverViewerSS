/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Outils.DateManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nrochas
 */
public class ScreenFilesDAO implements ScreenshotFilesDAO {

    private String fileName;
    DateManager dateM = new DateManager();

    public ScreenFilesDAO(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean checkExistingPDF(String langue, String formulaire, String version) {
        boolean bob = false;
        String date = "None";
        File file = new File(fileName + "/" + langue + "/" + formulaire + "_" + langue + "_v" + version + ".pdf");

        if (new File((fileName + "/" + langue + "/" + formulaire + "_" + langue + "_v" + version + ".pdf")).exists()) {
            bob = true;

            System.out.println("+++++++++ " + fileName + "/" + langue + "/" + formulaire + "_" + langue + "_v" + version + ".pdf" + " a ete trouve++++");
        }

        return bob;

    }

    @Override
    public String getDateLastModifPDF(String langue, String formulaire, String version) {
        String date = "None";
        File file = new File(fileName + "/" + langue + "/" + formulaire + "_" + langue + "_v" + version + ".pdf");

        if (file.exists()) {

            date = dateM.getSimpleDate(new Date(file.lastModified()));

        }

        return date;
    }

    /**
     * methode un peu alambiqué pour trouver le formulaire de training et de ce fichier en tirer la date de creation
     * @param langue
     * @param formulaire
     * @param version
     * @return 
     */
    public String searchTrainingPDF(String langue, String formulaire, String version) {
        String date = "None";
        Boolean bob = false;
        File file = null;
        ArrayList<String> list = (ArrayList) Outils.FilesWorker.ListerFilesByContainsAndExt(fileName + "/" + langue, "train", ".pdf");

        if (!list.isEmpty()) {

            for (String str : list) {
                
                if( str.contains(version))
                {
                   file = new File(fileName+"/"+langue+"/"+str);
                }
            }
        }
        if (file != null) {
            date = dateM.getSimpleDate(new Date(file.lastModified()));
        }

        return date;
    }

}
