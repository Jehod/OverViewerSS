/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.SimpleModifTrack;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author nik
 */
public interface ModifTrackDAO extends Serializable
{
    /**
     * renvoie une list des lignes du tracker du label
     * @return 
     */
    List<SimpleModifTrack> findAllModifTrack(); 
    
    /**
     * renvoie le dernier modifTrack , la derniere iteration du document
     * @return 
     */
    SimpleModifTrack getLastModifTrack();
    
    /**
     * renvoie la version la plus haute du label
     * @return 
     */
    String getLastVersion();
    
    /**
     * renvoie la derniere date de mofi rapporté dans le label
     * @return 
     */
    String getLastDate();
    
    /**
     * renvoie le dernier contributeur au label
     * @return 
     */
    String getLastContributor();
    
    /**
     * renvoie la derniere action comitée sur le label
     * @return 
     */
    String getLastAction();
    
}
