/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.JehodFactory.overviewerss.Params;
import Outils.DateManager;
import Outils.SVNWorker;
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

    private final String pathScreens;
    private String studyName;
    private HashMap map;
    private final SimpleStudyParam ssp;
    private final String langue;
     private final Outils.SVNWorker svn = new SVNWorker();
    private final ArrayList list;

    private final DateManager dateM = new DateManager();

    public ScreenFilesDAO(String fileName, String langue) {
        ssp = Params.getInstance().studyParam;
        //this.pathScreens = Params.getInstance().studyPath +ssp.getPathScreens();
        //this.pathScreens = pathScreens+ssp.getPathScreens();
        this.pathScreens = fileName + "/" + langue;
        this.langue = langue;
        list = svn.listSVNByExt(this.pathScreens, ".pdf");
        
    }

    @Override
    public boolean checkExistingPDF(String langue, String formulaire, String version) {
        boolean bob = false;

        String cible = formulaire+"_"+langue+"_v"+version+".pdf";
        
        bob = Outils.Check.checkIsIn(cible, list);
        
         if (bob) {
            System.out.println("+++++++++ " + pathScreens +  "/" + formulaire + "_" + langue + "_v"+version+".pdf" + " a ete trouve++++");
        } else {
            System.out.println("+++++++++ " + pathScreens +  "/" + formulaire + "_" + langue + "_v"+version +".pdf" + " InTROUVABLE++++");
        }
        
        /*
        if (new File((pathScreens + "/" + langue + "/" + formulaire + "_" + langue + "_v" + version + ".pdf")) != null) {
            bob = true;

            System.out.println("+++++++++ " + pathScreens + "/" + langue + "/" + formulaire + "_" + langue + "_v" + version + ".pdf" + " a ete trouve++++");
        } //partie qui sera remplacé par le mapping
        /*else if (bob == false) {
            bob = compareQuest(langue, formulaire, version);
        } */ 
        /*else {
            System.out.println("+++++++++ " + pathScreens + "/" + langue + "/" + formulaire + "_" + langue + "_v" + version + ".pdf" + " InTROUVABLE++++");
        }
        */

        return bob;

    }

    @Override
    public String getDateLastModifPDF(String langue, String formulaire, String version) {
        String date = "Done/NoDate";
        File file = new File(pathScreens + "/" + langue + "/" + formulaire + "_" + langue + "_v" + version + ".pdf");

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
        String date = "No";
        Boolean bob = false;
        File file = null;
        ArrayList<String> list = (ArrayList) Outils.FilesWorker.ListerFilesByContainsAndExt(pathScreens + "/" + langue, "train", ".pdf");

        if (!list.isEmpty()) {

            for (String str : list) {

                if (str.contains(version)) {
                    file = new File(pathScreens + "/" + langue + "/" + str);
                }
            }
        }
        if (file != null) {
            date = dateM.getSimpleDate(new Date(file.lastModified()));
        }

        return date;
    }

    /**
     * cherche dans la liste des pdf du fichiers si peut contrnir ou etre
     * contenu dans le label excel les deux sont standardisé au mieux
     *
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
        list = Outils.FilesWorker.ListerFilesByExt(pathScreens + "/" + langue, ".pdf");

        for (String str : list) {
            System.out.println("list ++++++++ : " + str);

            String[] tab = str.split(langue);
            quest = tab[0];

            //recuperation de la version du pdf
            vers = tab[1];
            vers = vers.replace("_", "");
            vers = vers.toLowerCase().replace(".pdf", "");

            quest = standardise(quest);

            System.out.println("quest " + quest + " questcible " + questCible + " versioncible: " + version + " version: " + vers);

            if ((quest.contains(questCible) || questCible.contains(quest)) && vers.equals(version)) {
                bob = true;
            }

        }

        return bob;
    }

    /**
     * Enleve les underscore , les tirets, les espaces et passe tout en
     * minuscule
     *
     * @param formulaire la sequence a standardiser
     * @return la sequence transformée
     */
    private String standardise(String formulaire) {
        String std;
        std = formulaire.replace("_", "");
        std = std.replace("-", "");
        std = std.replace(" ", "");
        std = std.toLowerCase();

        return std;
    }

}
