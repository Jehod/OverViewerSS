/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.interfaceDAO;

import entity.SimpleRowTracker;
import java.util.List;

/**
 *
 * @author nik
 */
public interface RowTrackerDAO
{
    /**
     * renvoie une liste des rowtracker, soit une ligne par mofication rapporté dans le label
     * @return 
     */
    List<SimpleRowTracker> findAllRowTracker();
    
}
