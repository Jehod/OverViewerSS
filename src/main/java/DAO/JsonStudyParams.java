/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author nrochas
 */
public class JsonStudyParams implements StudyParamsDAO{

    @Override
    public boolean createStudy(String name, String trad, String tablet, boolean font, String pathLabel, String pathScreens, String certif) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modifStudy(String name, String trad, String tablet, boolean font, String pathLabel, String pathScreens, String certif) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addStudyPath(String studyName, ArrayList<String> listPath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createMap(String studyName, HashMap map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addlinkToMap(String studyName, String excel, String fdef) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
