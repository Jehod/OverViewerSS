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
    List<SimpleModifTrack> findAllModifTrack(); 
    SimpleModifTrack getLastModifTrack();
    String getLastVersion();
    String getLastDate();
    String getLastContributor();
    String getLastAction();
    
}
