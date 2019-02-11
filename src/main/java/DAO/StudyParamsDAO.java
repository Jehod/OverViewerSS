/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author nrochas
 */
public interface StudyParamsDAO extends Serializable{
    
    /**
     * creer un objet studyParams et remplace celui qui fait reference dans les params
     * @param name
     * @param trad
     * @param tablet
     * @param font
     * @param pathLabel
     * @param pathScreens
     * @param certif
     * @return 
     */
    boolean createStudy (String name, String trad, String tablet, boolean font, String pathLabel, String pathScreens, String certif);
    /**
     * creer un objet studyParam et remplace celui de reference mais en commencant par une recuperation de l'existant
     * @param name
     * @param trad
     * @param tablet
     * @param font
     * @param pathLabel
     * @param pathScreens
     * @param certif
     * @return 
     */
    boolean modifStudy (String name, String trad, String tablet, boolean font, String pathLabel, String pathScreens, String certif);
    /**
     * ajoute un path a la liste des paths
     * @param studyName
     * @param listPath
     * @return 
     */
    boolean addStudyPath (String studyName, ArrayList<String> listPath);
    /**
     * initie la map qui fera le lien entre Fdef et excel
     * @param studyName
     * @param map
     * @return 
     */
    boolean createMap (String studyName, HashMap map);
    /**
     * ajoute une relation a la map
     * @param studyName
     * @param excel
     * @param fdef
     * @return 
     */
    boolean addlinkToMap (String studyName, String excel, String fdef);
  
    
    
    
}
