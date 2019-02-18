/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.interfaceDAO;

import entity.SimpleStudyTracker;
import entity.SimpleTracker;
import java.util.ArrayList;



/**
 *
 * @author nik
 */
public interface StudyTrackerDAO 
{
    /**
     * concatene tout les trackers de chaque fichier de langue 
     * creer un tracker global a partir d'une liste de tracker 'locaux'
     * @param allTracker
     * @return true si reussi
     */
    boolean fillStudyTracker(ArrayList<SimpleTracker> allTracker);
    
  /**
   * sauvegarde le studyTracker a l'emplacement prevu dans le format prevu
   * @param sst
   * @return 
   */
    boolean svgStudyTracker(SimpleStudyTracker sst);
    
    /**
     * ajoute les lignes du tracker au studyTracker en precisant la langue
     * @param smt  le tracker a ajouter, le code langue est tirée de son titre
     * @return true si reussi
     */
    boolean addTrackerToStudyTracker(SimpleTracker smt);
    
    
}
