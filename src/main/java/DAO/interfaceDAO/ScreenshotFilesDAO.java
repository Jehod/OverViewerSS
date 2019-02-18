/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.interfaceDAO;

import java.io.Serializable;

/**
 *
 * @author nrochas
 */
public interface ScreenshotFilesDAO extends Serializable{
    
    /**
     * verifie si un fichier existe et en renvoie la date de la dernier modif
     * le path est une concatenation e langue formulaire et de pathLabel
     * @param langue
     * @param formulaire
     * @param version
     * @return 
     */
    String getDateLastModifPDF(String langue,String formulaire, String version); 
    
    /**
     * renvoie true si il trouve un fichier a l'adresse
     * @param langue
     * @param formulaire
     * @param version
     * @return 
     */
    boolean checkExistingPDF(String langue,String formulaire, String version);
    
}
