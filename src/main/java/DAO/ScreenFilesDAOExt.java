/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.interfaceDAO.ScreenshotFilesDAO;

/**
 *
 * @author nrochas
 */
public class ScreenFilesDAOExt implements ScreenshotFilesDAO {




    @Override
    public boolean checkExistingPDF(String langue, String formulaire, String version) {
        System.out.println("probleme d'heritage screensFileDAO");
        return false;

    }

    @Override
    public String getDateLastModifPDF(String langue, String formulaire, String version) {
        System.out.println("probleme d'heritage screensFileDAO");
        return "Nodate";
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
       System.out.println("probleme d'heritage screensFileDAO");
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
       System.out.println("probleme d'heritage screensFileDAO");
        return false;
    }

    

}
