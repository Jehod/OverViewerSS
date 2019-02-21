/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.interfaceDAO.ScreenshotFilesDAO;
import Outils.Check;
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
public class SvnScreenFilesDAO extends ScreenFilesDAOExt {

    private final String pathScreens;
    private String studyName;
    private HashMap map;
    private final SimpleStudyParam ssp;
    private final String langue;
     private final Outils.SVNWorker svn = new SVNWorker();
    private final ArrayList listScreens;
    private boolean bob;

    private final DateManager dateM = new DateManager();

    public SvnScreenFilesDAO(String fileName, String langue) {
        ssp = Params.getInstance().studyParam;
        this.langue = langue;
        this.pathScreens = fileName + langue;
       
       // this.pathScreens = "svn://document.kayentis.fr:15000/kayentis/Documentation/Projets/Santé/Novartis/CAIN457M2301-M2302/3- Functional scope/2- Forms/2- Kayentis design/1 - Screenshots/"+this.langue+"/M2302/";
        
        
        listScreens = svn.listSVNByExt(this.pathScreens, ".pdf");
        
    }

  

    @Override
    public boolean checkExistingPDF(String langue, String formulaire, String version) {
        

        String cible = formulaire+"_"+this.langue+"_v"+version+".pdf";
        
        bob = Outils.Check.checkIsIn(cible, listScreens);
        
         if (bob) {
            System.out.println("+++++++++ " + pathScreens +  "/" + formulaire + "_" + langue + "_v"+version+".pdf" + " a ete trouve++++");
        } else {
            System.out.println("+++++++++ " + pathScreens +  "/" + formulaire + "_" + langue + "_v"+version +".pdf" + " InTROUVABLE++++");
        }
        
     

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
    @Override
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
        
        String quest;
        String vers;
        String questCible = Check.standardise(formulaire);
        List<String> list;
        list = Outils.FilesWorker.ListerFilesByExt(pathScreens + "/" + langue, ".pdf");

        for (String str : list) {
            System.out.println("list ++++++++ : " + str);

            String[] tab = str.split(langue);
            quest = tab[0];

            //recuperation de la version du pdf
            vers = tab[1];
            vers = vers.replace("_", "");
            vers = vers.toLowerCase().replace(".pdf", "");

            quest = Check.standardise(quest);

            System.out.println("quest " + quest + " questcible " + questCible + " versioncible: " + version + " version: " + vers);

            bob =((quest.contains(questCible) || questCible.contains(quest)) && vers.equals(version));
        }

        return bob;
    }

   

}
