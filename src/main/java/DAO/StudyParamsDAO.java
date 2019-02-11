/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author nrochas
 */
public interface StudyParamsDAO extends Serializable{
    
    boolean createStudy (String name, String trad, String tablet, boolean font, String pathLabel, String pathScreens, String certif);
    boolean modifStudy (String name, String trad, String tablet, boolean font, String pathLabel, String pathScreens, String certif);
    boolean addStudyPath (String studyName, ArrayList<String> listPath);
    boolean createMap (String studyName, HashMap map);
    boolean addlinkToMap (String studyName, String excel, String fdef);
    
    
    
}
