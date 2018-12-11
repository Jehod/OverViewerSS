/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.SimpleTracker;
import java.io.Serializable;
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
   
    
}
