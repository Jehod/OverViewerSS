/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author nik
 */
public interface Tracker extends Serializable
{
    ArrayList<SimpleRowTracker> getAllRowTracker();
    /**
     * retourne le nom de la sheet qui contient le tracker
     * le nom est "Track_"+date
     * @return 
     */
    String getName();
    String getDate();
    
}
