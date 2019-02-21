/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.interfaceDAO.StudyParamsDAO;
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

    private boolean bob;
    Params params = Params.getInstance();

    @Override
    public boolean createStudy(String name, String trad, String tablet, boolean font, String pathSvnDel, String pathSvnDoc) {
        SimpleStudyParam ssp;
        String error = "Erronuous Data";

        if (Check.isGood(trad) && Check.isGood(tablet) && Check.isGood(pathSvnDoc) && Check.isGood(pathSvnDel) ) {
            ssp = new SimpleStudyParam(new ArrayList<>(), trad, new HashMap(), tablet, font, pathSvnDoc, pathSvnDel);
        } else {
            ssp = new SimpleStudyParam(new ArrayList<>(),error , new HashMap(), error, true, error, error);
        }

        return params.svgNewStudyParam(name, ssp);
    }

    @Override
    public boolean modifStudy(String name, String trad, String tablet, boolean font, String pathSvnDoc, String pathSvnDel) {

        SimpleStudyParam ssp = params.getStudyParam();
        if (Check.isGood(trad) && Check.isGood(tablet) && Check.isGood(pathSvnDoc) && Check.isGood(pathSvnDel) ) {
            ssp = new SimpleStudyParam(ssp.getListStudyPath(), trad, ssp.getMap(), tablet, font, pathSvnDoc, pathSvnDel);
            bob  = params.svgStudyParam(name, ssp);
        }
        return bob;
    }

    @Override
    public boolean addStudyPath(String studyName, String newPath) {

        ArrayList listPath = Params.getInstance().studyParam.getListStudyPath();
        System.out.println("list size:" + listPath.size() + " newPath " + newPath);

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
