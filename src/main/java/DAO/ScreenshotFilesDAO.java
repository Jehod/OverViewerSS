/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;

/**
 *
 * @author nrochas
 */
public interface ScreenshotFilesDAO extends Serializable{
    
    boolean checkExisting(String langue,String formulaire, String version); 
    
}
