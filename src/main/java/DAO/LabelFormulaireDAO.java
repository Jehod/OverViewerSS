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
public interface LabelFormulaireDAO 
{
    List<SimpleModifTrack> findAllModifTrack();
    String getLastVersion();
    String getLastContributor();
    String getLastDate();
    
   
}
