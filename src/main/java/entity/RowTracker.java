/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**le rowTracker est une ligne dans le fichier de track. 
 * 
 *
 * @author nik
 */
public interface RowTracker extends Serializable
{
    /**
     * le formulaire concerné
     * @return 
     */
    String getFormulaire();
    
  
    
    /**
     * le numero de version concernée
     * @return 
     */
    String getVersion();
    
    /**
     * la date de la version concernée
     * @return 
     */
    String getDateVers();
    
  /**
   * trace si le screenshot est fait. daté si ok, none si pas fait
   * @return 
   */
    String getScreenDone();
    
  
    
    /**
     * trace si certifiée. daté si ok , none si pas fait
     * @return 
     */
    String getCertified();
    
    
    
    
}
