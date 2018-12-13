/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.SimpleModifTrack;
import entity.SimpleRowTracker;
import entity.SimpleTracker;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nik
 */
public interface TrackerDAO extends Serializable
{
   /**
    * retourne toute les sheet du tracker (reellement utile?)
    * @return 
    */ 
   List<SimpleTracker> readAllTracker(); 
   
   
   
   /**
    * ajoute une sheet tracker au Tracker excel
    * @param tck
    * @return boolean proof
    */
   Boolean addTracker(SimpleTracker tck);
   
   /**
    * renvoie le tracker le plus recent du tracker (utile? les methodes prennent deja par defaut le plus recent)
    * @return 
    */
   SimpleTracker getLastTracker();
   
   /**
    * renvoie la date du tracker le plus recent (utile?)
    * @return 
    */
   String getLastTrackerDate();
   
   /**
    * renvoie le nom de la dernier sheet tracker
    * @return 
    */
  String getLastTrackerName();
  
  /**
   * creer un tracker des xls de la langue pointée dans le dossier du label
   * @param dir le nom du dossier sous form **_**
   * @return 
   */
  SimpleTracker createTrackerFromLabel(String dir);
  
  /**
   * transforme un modifTrack de label en ligne pour le tracker
   * @param smtk  le modifTrack a traiter
   * @return  le rowTracker pour le tracker
   */
  SimpleRowTracker modifTrackToRowTracker(SimpleModifTrack smtk);
  
  

  
  /**cree un tracker avec toutes les row donné en param
   * 
   * @param allsrtk
   * @return 
   */
  SimpleTracker createTracker(ArrayList<SimpleRowTracker> allsrtk);
    
  /**
   * creer le fichier xls correspondant au tracker donné
   * @param stk
   * @return true si reussi
   */
  boolean svgTracker(SimpleTracker stk);
}
