/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Outils.Check;
import com.JehodFactory.overviewerss.Params;
import entity.SimpleStudyParam;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author nrochas
 */
public class JsonStudyParamsDAO implements StudyParamsDAO {

    @Override
    public boolean createStudy(String name, String trad, String tablet, boolean font, String pathLabel, String pathScreens, String certif) {
        boolean bob = false;

        SimpleStudyParam ssp = new SimpleStudyParam(new ArrayList<>(), trad, pathLabel, pathScreens, certif, tablet, font, new HashMap<>());

        bob = Params.getInstance().svgStudyParam(name, ssp);

        return bob;
    }

    @Override
    public boolean modifStudy(String name, String trad, String tablet, boolean font, String pathLabel, String pathScreens, String certif) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addStudyPath(String studyName, String newPath) {
        boolean bob = false;
        ArrayList listPath = Params.getInstance().studyParam.getListStudyPath();
        System.out.println("list size:"+listPath.size()+" newPath "+newPath);

           bob = Params.getInstance().svgListStudy(studyName, listPath);
        
        return bob;
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
