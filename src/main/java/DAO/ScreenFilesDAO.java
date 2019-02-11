/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.JehodFactory.overviewerss.Params;
import Outils.DateManager;
import entity.SimpleStudyParam;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author nrochas
 */
public class ScreenFilesDAO implements ScreenshotFilesDAO {

    private String fileName;
    private String studyName;
    private HashMap map;
    private SimpleStudyParam ssp;
    
    private DateManager dateM = new DateManager();

    public ScreenFilesDAO(String fileName) {
        ssp = Params.getInstance().studyParam;
        this.fileName = Params.getInstance().studyPath+ssp.getPathScreens();
    }

    @Override
    public boolean checkExistingPDF(String langue, String formulaire, String version) {
        boolean bob = false;
        String date = "None";
        File file = new File(fileName + "/" + langue + "/" + formulaire + "_" + langue + "_v" + version + ".pdf");

        if (new File((fileName + "/" + langue + "/" + formulaire + "_" + langue + "_v" + version + ".pdf")).exists()) {
            bob = true;

            System.out.println("+++++++++ " + fileName + "/" + langue + "/" + formulaire + "_" + langue + "_v" + version + ".pdf" + " a ete trouve++++");
        } else if (bob == false) {
            bob = compareQuest(langue, formulaire, version);
        } else {
            System.out.println("+++++++++ " + fileName + "/" + langue + "/" + formulaire + "_" + langue + "_v" + version + ".pdf" + " InTROUVABLE++++");
        }

        return bob;

    }

    @Override
    public String getDateLastModifPDF(String langue, String formulaire, String version) {
        String date = "Done/NoDate";
        File file = new File(fileName + "/" + langue + "/" + formulaire + "_" + langue + "_v" + version + ".pdf");

        if (file.exists()) {

            date = dateM.getSimpleDate(new Date(file.lastModified()));

        }

        return date;
    }

    /**
     * methode un peu alambiqué pour trouver le formulaire de training et de ce
     * fichier en tirer la date de creation
     *
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

                if (str.contains(version)) {
                    file = new File(fileName + "/" + langue + "/" + str);
                }
            }
        }
        if (file != null) {
            date = dateM.getSimpleDate(new Date(file.lastModified()));
        }

        return date;
    }

    /**
     * cherche dans la liste des pdf du fichiers si peut contrnir ou etre contenu dans le
     * label excel les deux sont standardisé au mieux
     * @param langue
     * @param formulaire
     * @param version
     * @return 
     */
    private boolean compareQuest(String langue, String formulaire, String version) {
        boolean bob = false;
         String quest;
         String vers;
        String questCible = standardise(formulaire);
        List<String> list = new ArrayList();
        list = Outils.FilesWorker.ListerFilesByExt(fileName + "/" + langue, ".pdf");

        for (String str : list) {
            System.out.println("list ++++++++ : " + str);
           
            String[] tab = str.split(langue);
            quest = tab[0];
            
            //recuperation de la version du pdf
            vers = tab[1];
            vers = vers.replace("_", "");
            vers = vers.toLowerCase().replace(".pdf","");
            
            quest = standardise(quest);
            
            System.out.println("quest "+quest +" questcible "+questCible + " versioncible: "+version+" version: "+vers);
            
            if ( (quest.contains(questCible) || questCible.contains(quest)) && vers.equals(version) ) {
                bob = true;
            }

        }

        return bob;
    }
/**
 * Enleve les underscore , les tirets, les espaces et passe tout en minuscule
 * @param formulaire la sequence a standardiser
 * @return la sequence transformée
 */
    private String standardise(String formulaire) {
        String std;
        std = formulaire.replace("_", "");
        std = std.replace("-", "");
        std =std.replace(" ", "");
        std = std.toLowerCase();

        return std;
    }

}
